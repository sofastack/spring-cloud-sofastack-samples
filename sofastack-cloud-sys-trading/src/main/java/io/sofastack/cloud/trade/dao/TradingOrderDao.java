package io.sofastack.cloud.trade.dao;

import io.sofastack.cloud.common.model.TradingOrder;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author: guolei.sgl (guolei.sgl@antfin.com) 2019/3/12 10:31 AM
 * @since:
 **/
@Mapper
public interface TradingOrderDao {

    /**
     * 交易订单入库
     *
     * @param tradingOrder
     * @return
     */
    int save(TradingOrder tradingOrder);

    /**
     * 根据订单号查询订单详情
     *
     * @param orderNo
     * @return
     */
    TradingOrder queryByOrderNo(String orderNo);
}
