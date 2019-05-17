package io.sofastack.cloud.mq.kafka.service;

import io.sofastack.cloud.common.model.UserDetails;
import io.sofastack.cloud.common.response.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author: guolei.sgl (guolei.sgl@antfin.com) 2019/3/12 7:44 PM
 * @since:
 **/
@FeignClient(value = "sys-user")
public interface UserService {
    /**
     * 查询用户详情
     *
     * @param userId
     * @return
     */
    @RequestMapping(value = "/queryUserDetail", method = RequestMethod.GET)
    Result<UserDetails> queryUserDetail(@RequestParam("userId") int userId);
}
