package io.sofastack.cloud.trade.boltservice;

import com.alipay.sofa.runtime.api.annotation.SofaService;
import com.alipay.sofa.runtime.api.annotation.SofaServiceBinding;
import io.sofastack.cloud.common.model.TradingContext;
import io.sofastack.cloud.common.model.TradingOrder;
import io.sofastack.cloud.common.response.Result;
import io.sofastack.cloud.common.utils.OrderUtils;
import io.sofastack.cloud.facade.trading.TradingService;
import io.sofastack.cloud.trade.dao.TradingOrderDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: guolei.sgl (guolei.sgl@antfin.com) 2019/3/6 4:42 PM
 * @since:
 **/
@Service
@SofaService(bindings = {@SofaServiceBinding(bindingType = "bolt")})
public class TradingServiceImpl implements TradingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TradingServiceImpl.class);
    @Autowired
    TradingOrderDao             tradingOrderDao;

    @Override
    public Result<TradingOrder> createTradingOrder(TradingContext tradingContext) {
        Result<TradingOrder> result = new Result<>();
        try {
            TradingOrder tradingOrder = buildTradingOrder(tradingContext);
            int saveResult = tradingOrderDao.save(tradingOrder);
            if (saveResult > 0) {
                result.setSuccess(true);
                result.setData(tradingOrder);
            } else {
                result.setSuccess(false);
                result.setErrorMsg("Failed to insert order to db.");
            }
        } catch (Throwable t) {
            LOGGER.error("Failed to insert order to db.", t);
            result.setSuccess(false);
            result.setErrorMsg(t.getMessage());
        }
        return result;
    }

    @Override
    public Result<TradingOrder> queryTradingOrder(String orderNo) {
        Result<TradingOrder> result = new Result<>();
        try {
            TradingOrder tradingOrder = tradingOrderDao.queryByOrderNo(orderNo);
            result.setSuccess(true);
            result.setData(tradingOrder);
        } catch (Throwable t) {
            LOGGER.error("Failed to query order.", t);
            result.setErrorMsg(t.getMessage());
            result.setSuccess(false);
        }
        return result;
    }

    private TradingOrder buildTradingOrder(TradingContext tradingContext) {
        TradingOrder tradingOrder = new TradingOrder();
        tradingOrder.setId(OrderUtils.getTradingNo(null));
        tradingOrder.setCreateTime(tradingContext.getCreateTime());
        tradingOrder.setOrderAmount(tradingContext.getOrderAmount());
        tradingOrder.setOrderGroup(tradingContext.getOrderGroup());
        tradingOrder.setOrderMark(tradingContext.getOrderMark());
        tradingOrder.setOrderState(tradingContext.getOrderState());
        tradingOrder.setOrderType(tradingContext.getOrderType());
        tradingOrder.setSerialNo(tradingContext.getSerialNo());
        tradingOrder.setPayChannel(tradingContext.getPayChannel());
        tradingOrder.setSourceAccount(tradingContext.getSourceAccount());
        tradingOrder.setTargetAccount(tradingContext.getTargetAccount());
        return tradingOrder;
    }
}
