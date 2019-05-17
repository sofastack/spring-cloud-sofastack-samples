package io.sofastack.cloud.auth.service;

import io.sofastack.cloud.common.model.UserDetails;
import io.sofastack.cloud.common.response.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 基于 Spring Cloud Feign 进行服务调用
 *
 * @author: guolei.sgl (guolei.sgl@antfin.com) 2019/3/5 5:44 PM
 * @since:
 **/
@FeignClient(value = "sys-user")
public interface UserService {

    /**
     * 验证用户详情
     *
     * @param userDetails
     * @return
     */
    @RequestMapping(value = "/validate", method = RequestMethod.POST)
    Result<UserDetails> validate(@RequestBody UserDetails userDetails);

    /**
     * 注册账户
     *
     * @param userDetails
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    Result<UserDetails> register(@RequestBody UserDetails userDetails);

    /**
     * 获取下一个userId
     *
     * @return
     */
    @RequestMapping(value = "/getNextUserId", method = RequestMethod.GET)
    Result<Integer> getNextUserId();
}
