package io.sofastack.cloud.auth.service;

import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.alipay.sofa.runtime.api.annotation.SofaReferenceBinding;
import io.sofastack.cloud.common.response.Result;
import io.sofastack.cloud.rest.facade.AccountingService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 基于 SOFARPC Rest 协议 进行服务调用
 *
 * @author: guolei.sgl (guolei.sgl@antfin.com) 2019/3/5 8:24 PM
 * @since:
 **/
@Service
public class AccountService {

    @SofaReference(binding = @SofaReferenceBinding(bindingType = "rest"))
    private AccountingService accountingService;

    /**
     * 创建资金账户
     *
     * @param params
     * @return
     */
    public Result<Boolean> createAccount(Map<String, Object> params) {
        return accountingService.createAccount(params);
    }
}
