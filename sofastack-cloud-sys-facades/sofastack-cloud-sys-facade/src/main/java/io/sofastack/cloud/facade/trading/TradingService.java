package io.sofastack.cloud.facade.trading;

import io.sofastack.cloud.common.model.TradingContext;
import io.sofastack.cloud.common.model.TradingOrder;
import io.sofastack.cloud.common.response.Result;

/**
 * @author: guolei.sgl (guolei.sgl@antfin.com) 2019/3/6 4:28 PM
 * @since:
 **/
public interface TradingService {

    /**
     * 创建交易订单记录
     *
     * @param tradingContext 交易上下文
     * @return
     */
    Result<TradingOrder> createTradingOrder(TradingContext tradingContext);

    /**
     * 查询交易订单记录
     *
     * @param orderNo
     * @return
     */
    Result<TradingOrder> queryTradingOrder(String orderNo);

}
