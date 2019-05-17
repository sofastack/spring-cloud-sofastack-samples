package io.sofastack.cloud.web.service;

import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.alipay.sofa.runtime.api.annotation.SofaReferenceBinding;
import io.sofastack.cloud.common.model.TradingContext;
import io.sofastack.cloud.common.model.TradingOrder;
import io.sofastack.cloud.common.response.Result;
import io.sofastack.cloud.facade.trading.TradingService;
import org.springframework.stereotype.Service;

/**
 * 基于 SOFARPC Bolt 协议 进行服务调用
 *
 * @author: guolei.sgl (guolei.sgl@antfin.com) 2019/3/6 4:48 PM
 * @since:
 **/
@Service
public class TradeService {

    @SofaReference(binding = @SofaReferenceBinding(bindingType = "bolt"))
    private TradingService tradingService;

    /**
     * 创建交易订单记录
     *
     * @param tradingContext 交易上下文
     * @return
     */
    public Result<TradingOrder> createTradingOrder(TradingContext tradingContext) {
        return tradingService.createTradingOrder(tradingContext);
    }

    /**
     * 查询交易订单记录
     *
     * @param orderNo
     * @return
     */
    public Result<TradingOrder> queryTradingOrder(String orderNo) {
        return tradingService.queryTradingOrder(orderNo);
    }
}
