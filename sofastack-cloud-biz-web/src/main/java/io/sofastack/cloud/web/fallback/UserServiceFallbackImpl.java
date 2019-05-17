package io.sofastack.cloud.web.fallback;

import io.sofastack.cloud.common.model.UserDetails;
import io.sofastack.cloud.common.response.Result;
import io.sofastack.cloud.web.service.UserService;
import org.springframework.stereotype.Component;

/**
 * @author: guolei.sgl (guolei.sgl@antfin.com) 2019/3/5 7:36 PM
 * @since:
 **/
@Component
public class UserServiceFallbackImpl implements UserService {

    @Override
    public Result<UserDetails> queryUserDetail(int userId) {
        Result<UserDetails> result = new Result<>();
        result.setSuccess(false);
        result.setData(null);
        result.setErrorMsg("Query User Details Error, Please Try Later!");
        return result;
    }

    @Override
    public Result<UserDetails> register(UserDetails userDetails) {
        Result<UserDetails> result = new Result<>();
        result.setSuccess(false);
        result.setData(null);
        result.setErrorMsg("Register User Error, Please Try Later!");
        return result;
    }
}
