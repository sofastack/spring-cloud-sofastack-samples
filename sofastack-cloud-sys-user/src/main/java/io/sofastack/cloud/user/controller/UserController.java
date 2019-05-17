package io.sofastack.cloud.user.controller;

import io.sofastack.cloud.common.model.UserDetails;
import io.sofastack.cloud.common.response.Result;
import io.sofastack.cloud.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: guolei.sgl (guolei.sgl@antfin.com) 2019/3/5 5:41 PM
 * @since:
 **/
@RestController
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService                 userService;

    @RequestMapping("/queryUserDetail")
    public Result<UserDetails> userDetails(@RequestParam("userId") int userId) {
        Result<UserDetails> result = new Result<>();
        try {
            UserDetails userDetails = userService.queryUserDetails(userId);
            result.setData(userDetails);
            result.setSuccess(true);
        } catch (Throwable t) {
            LOGGER.error("Failed to get userDetails by userId.", t);
            result.setData(null);
            result.setSuccess(false);
        }
        return result;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Result<UserDetails> register(@RequestBody UserDetails userDetails) {
        Result<UserDetails> result = new Result<>();
        try {
            if (!userService.isAlreadyExist(userDetails)) {
                UserDetails current = userService.register(userDetails);
                result.setSuccess(true);
                result.setData(current);
            } else {
                result.setSuccess(false);
                result.setErrorMsg("注册失败，当前 userName 已经存在。");
            }
        } catch (Throwable t) {
            LOGGER.error("Failed to register user.", t);
            result.setData(null);
            result.setSuccess(false);
            result.setErrorMsg("注册失败!");
        }
        return result;
    }

    @RequestMapping(value = "/validate", method = RequestMethod.POST)
    public Result<UserDetails> validate(@RequestBody UserDetails userDetails) {
        Result<UserDetails> result = new Result<>();
        try {
            String userName = userDetails.getUserName();
            String password = userDetails.getPassword();
            UserDetails validate = userService.validate(userName, password);
            result.setSuccess(true);
            result.setData(validate);
        } catch (Throwable t) {
            LOGGER.error("Failed to register user.", t);
            result.setData(null);
            result.setSuccess(false);
        }
        return result;
    }

}
