package io.sofastack.cloud.auth.controller;

import io.sofastack.cloud.auth.model.AuthRequest;
import io.sofastack.cloud.auth.model.RegisterRequest;
import io.sofastack.cloud.auth.service.AccountService;
import io.sofastack.cloud.auth.service.UserService;
import io.sofastack.cloud.common.model.UserDetails;
import io.sofastack.cloud.common.response.RestFacadeResp;
import io.sofastack.cloud.common.response.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: guolei.sgl (guolei.sgl@antfin.com) 2019/3/28 9:17 PM
 * @since:
 **/
@RestController
@RequestMapping("auth")
public class AuthController {
    private static final Logger LOGGER   = LoggerFactory.getLogger(AuthController.class);
    private static final String USER_KEY = "user";

    @Autowired
    private UserService         userService;

    @Autowired
    private AccountService      accountService;

    @RequestMapping("valid")
    public RestFacadeResp<Integer> valid(HttpServletRequest request, AuthRequest authRequest) {
        RestFacadeResp<Integer> result = new RestFacadeResp<>();
        try {
            UserDetails queryInfo = new UserDetails();
            if (authRequest != null && !StringUtils.isEmpty(authRequest.getUserName())) {
                queryInfo.setUserName(authRequest.getUserName());
                queryInfo.setPassword(authRequest.getPassword());
            }
            // from register
            else if (request.getSession().getAttribute(USER_KEY) != null) {
                UserDetails user = (UserDetails) request.getSession().getAttribute(USER_KEY);
                queryInfo.setUserName(user.getUserName());
                queryInfo.setPassword(user.getPassword());
            }
            Result<UserDetails> validate = userService.validate(queryInfo);
            if (validate.isSuccess()) {
                result.setSuccess(true);
                result.setData(validate.getData().getUserId());
            } else {
                result.setSuccess(false);
            }
        } catch (Exception e) {
            LOGGER.error("Failed to valid user.", e);
            result.setSuccess(false);
        }
        return result;
    }

    @RequestMapping("register")
    public RestFacadeResp<Integer> register(HttpServletRequest request, RegisterRequest body) {
        RestFacadeResp<Integer> result = new RestFacadeResp<>();
        try {
            UserDetails user = new UserDetails();
            user.setPassword(body.getPassword());
            user.setOldPassword(body.getPassword());
            user.setUserName(body.getUserName());
            user.setCreateTime(new Date());
            user.setNickName(body.getUserName());
            user.setEmail(body.getEmail());
            user.setRoleType("admin");
            Result<UserDetails> data = userService.register(user);
            if (data.isSuccess()) {
                Map<String, Object> params = new HashMap<>(4);
                params.put("userId", data.getData().getUserId());
                params.put("payPassword", body.getPayPassword());
                accountService.createAccount(params);
                result.setSuccess(true);
                request.getSession().setAttribute("user", user);
            } else {
                result.setSuccess(false);
                request.getSession().setAttribute("errorMsg", data.getErrorMsg());
            }
        } catch (Exception e) {
            LOGGER.error("Failed to register user;", e);
            request.getSession().setAttribute("errorMsg", "注册失败");
            result.setSuccess(false);
        }
        return result;
    }
}
