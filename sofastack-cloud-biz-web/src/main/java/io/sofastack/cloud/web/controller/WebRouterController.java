package io.sofastack.cloud.web.controller;

import io.sofastack.cloud.common.model.BalanceDetails;
import io.sofastack.cloud.common.model.TradingOrder;
import io.sofastack.cloud.common.model.UserDetails;
import io.sofastack.cloud.common.response.Result;
import io.sofastack.cloud.web.service.AccountService;
import io.sofastack.cloud.web.service.TradeService;
import io.sofastack.cloud.web.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 视图 controller
 *
 * @author: guolei.sgl (guolei.sgl@antfin.com) 19/1/26 下午4:20
 * @since:
 **/
@Controller
public class WebRouterController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebRouterController.class);

    @Autowired
    private AccountService      accountService;
    @Autowired
    private TradeService        tradingService;
    @Autowired
    private UserService         userService;

    /**
     * 首页
     *
     * @param map
     * @param request
     * @return
     */
    @RequestMapping(value = "index")
    public ModelAndView index(ModelAndView map, HttpServletRequest request) {
        try {
            int userId = Integer.valueOf(request.getParameter("code"));
            Map<String, Object> params = new HashMap<>(2);
            params.put("userId", userId);
            Result<BalanceDetails> resultData = accountService.balanceDetails(params);
            if (resultData.isSuccess()) {
                map.addObject("accountId", resultData.getData().getUserId());
                map.addObject("balance", resultData.getData().getAvailableAssets());
                map.addObject("userId", resultData.getData().getUserId());
                map.setViewName("index");
            } else {
                map.setViewName("fail");
                map.addObject("errorMsg", resultData.getErrorMsg());
            }
        } catch (Throwable t) {
            LOGGER.error("Failed to init index data.", t);
            map.setViewName("fail");
            map.addObject("errorMsg", "cannot get your account info");
        }
        return map;
    }

    /**
     * success
     *
     * @return
     */
    @RequestMapping("success")
    public String success(HttpServletRequest request, ModelMap map) {
        String orderNo = request.getParameter("orderNo");
        boolean isSuccess;
        String errorMsg = "";
        Result<TradingOrder> tradingOrderData = tradingService.queryTradingOrder(orderNo);
        if (tradingOrderData.isSuccess()) {
            int account = tradingOrderData.getData().getTargetAccount();
            Result<UserDetails> userDetailsData = queryUserDetailByRetryTimes(3, account);
            if (userDetailsData.isSuccess()) {
                map.addAttribute("orderNo", orderNo);
                map.addAttribute("targetAccount", userDetailsData.getData().getUserName());
                map.addAttribute("amount", tradingOrderData.getData().getOrderAmount());
                map.addAttribute("dateTime", new Date());
                isSuccess = true;
            } else {
                isSuccess = false;
                errorMsg = userDetailsData.getErrorMsg();
            }
        } else {
            isSuccess = false;
            errorMsg = tradingOrderData.getErrorMsg();
        }

        if (isSuccess) {
            return "success";
        } else {
            map.addAttribute("errorMsg", errorMsg);
            return "fail";
        }
    }

    /**
     * 防止因网络影响失败走熔断逻辑，重试3次
     *
     * @param retryTimes
     * @param account
     * @return
     */
    private Result<UserDetails> queryUserDetailByRetryTimes(int retryTimes, int account) {
        Result<UserDetails> userDetailsData = userService.queryUserDetail(account);
        while (!userDetailsData.isSuccess() && (retryTimes--) > 0) {
            userDetailsData = userService.queryUserDetail(account);
        }
        return userDetailsData;
    }

    @RequestMapping("fail")
    public String fail(HttpServletRequest request, ModelMap map) {
        String data = request.getParameter("data");
        map.addAttribute("errorMsg", data);
        return "fail";
    }
}
