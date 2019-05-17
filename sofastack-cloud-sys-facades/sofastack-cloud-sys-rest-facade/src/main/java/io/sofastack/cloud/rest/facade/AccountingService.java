package io.sofastack.cloud.rest.facade;

import io.sofastack.cloud.common.model.BalanceDetails;
import io.sofastack.cloud.common.model.SerialOrder;
import io.sofastack.cloud.common.model.TransferRequest;
import io.sofastack.cloud.common.response.Result;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Map;

/**
 * @author: guolei.sgl (guolei.sgl@antfin.com) 2019/3/5 8:08 PM
 * @since:
 **/
@Path("restAccountingService")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface AccountingService {

    /**
     * 查询账户余额
     * @param params
     * @return
     */
    @POST
    @Path("balanceDetail")
    Result<BalanceDetails> balanceDetail(Map<String, Object> params);

    /**
     * 转账
     * @param request
     * @return
     */
    @POST
    @Path("transfer")
    Result<SerialOrder> transfer(TransferRequest request);

    /**
     * 校验账密
     * @param params
     * @return
     */
    @POST
    @Path("checkPayPassword")
    Result<Boolean> checkPayPassword(Map<String, Object> params);

    /**
     * 创建Account
     * @param params
     * @return
     */
    @POST
    @Path("createAccount")
    Result<Boolean> createAccount(Map<String, Object> params);
}
