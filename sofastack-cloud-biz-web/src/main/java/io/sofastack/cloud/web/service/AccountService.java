package io.sofastack.cloud.web.service;

import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.alipay.sofa.runtime.api.annotation.SofaReferenceBinding;
import io.sofastack.cloud.common.model.BalanceDetails;
import io.sofastack.cloud.common.model.SerialOrder;
import io.sofastack.cloud.common.model.TransferRequest;
import io.sofastack.cloud.common.response.Result;
import io.sofastack.cloud.rest.facade.AccountingService;
import io.sofastack.cloud.web.model.RequestInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 基于 SOFARPC Rest 协议 进行服务调用
 *
 * @author: guolei.sgl (guolei.sgl@antfin.com) 2019/3/5 8:24 PM
 * @since:
 **/
@Service
public class AccountService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountService.class);

    @SofaReference(binding = @SofaReferenceBinding(bindingType = "rest"))
    private AccountingService   accountingService;

    /**
     * 账户余额服务
     *
     * @param params
     * @return
     */
    public Result<BalanceDetails> balanceDetails(Map<String, Object> params) {
        return accountingService.balanceDetail(params);
    }

    /**
     * 转账服务
     *
     * @param request
     * @return
     */
    public Result<SerialOrder> transfer(RequestInfo request) {
        Result<SerialOrder> result = new Result<>();
        SerialOrder serialOrder = new SerialOrder();
        try {
            String payPassword = request.getPassword();
            int userId = request.getUserId();
            // 校验密码正确性
            if (!checkPayPassword(userId, payPassword)) {
                // 标记流水账单状态，-1 为失败流水单
                serialOrder.setDataFlag(-1);
                result.setSuccess(false);
                result.setErrorMsg("password check failures");
            } else if (!checkBalance(request)) {
                serialOrder.setDataFlag(-1);
                result.setSuccess(false);
                result.setErrorMsg("非法金额或账户余额不足");
            } else {
                TransferRequest transferRequest = parseFromRequest(request);
                result = accountingService.transfer(transferRequest);
            }
        } catch (Throwable t) {
            LOGGER.error("Failed to transfer.", t);
            result.setSuccess(false);
            result.setErrorMsg(t.getMessage());
        }
        return result;
    }

    private boolean checkBalance(RequestInfo request) {
        double money = request.getMoney();
        if (money < 0) {
            return false;
        }
        Map params = new HashMap();
        params.put("userId", request.getUserId());
        Result result = accountingService.balanceDetail(params);
        if (result.isSuccess()) {
            BalanceDetails data = (BalanceDetails) result.getData();
            if (data.getAvailableAssets().doubleValue() > money) {
                return true;
            }
        }
        return false;
    }

    private boolean checkPayPassword(int userId, String payPassword) {
        Map<String, Object> params = new HashMap<>(4);
        params.put("userId", userId);
        params.put("payPassword", payPassword);
        Result<Boolean> result = accountingService.checkPayPassword(params);
        return result.isSuccess() && result.getData();
    }

    private TransferRequest parseFromRequest(RequestInfo request) {
        int myId = request.getUserId();
        int targetId = request.getTargetId();
        BigDecimal money = BigDecimal.valueOf(request.getMoney());
        TransferRequest transferRequest = new TransferRequest();
        transferRequest.setAccountId(targetId);
        transferRequest.setAmountMoney(money);
        transferRequest.setRequestTime(new Date());
        transferRequest.setTargetId(targetId);
        transferRequest.setUserId(myId);
        return transferRequest;
    }
}
