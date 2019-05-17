package io.sofastack.cloud.web.service;

import io.sofastack.cloud.common.model.UserDetails;
import io.sofastack.cloud.common.response.Result;
import io.sofastack.cloud.web.fallback.UserServiceFallbackImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 基于 Spring Cloud Feign 进行服务调用
 *
 * @author: guolei.sgl (guolei.sgl@antfin.com) 2019/3/5 5:44 PM
 * @since:
 **/
@FeignClient(value = "sys-user", fallback = UserServiceFallbackImpl.class)
public interface UserService {
    /**
     * 查询用户详情
     *
     * @param userId
     * @return
     */
    @RequestMapping(value = "/queryUserDetail", method = RequestMethod.GET)
    Result<UserDetails> queryUserDetail(@RequestParam("userId") int userId);

    /**
     * 注册用户
     *
     * @param userDetails
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    Result<UserDetails> register(@RequestBody UserDetails userDetails);
}
