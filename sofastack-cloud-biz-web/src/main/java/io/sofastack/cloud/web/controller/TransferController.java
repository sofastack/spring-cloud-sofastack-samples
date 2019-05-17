package io.sofastack.cloud.web.controller;

import io.sofastack.cloud.common.constants.KafkaConstants;
import io.sofastack.cloud.common.model.SerialOrder;
import io.sofastack.cloud.common.model.TradingContext;
import io.sofastack.cloud.common.model.TradingOrder;
import io.sofastack.cloud.common.response.RestFacadeResp;
import io.sofastack.cloud.common.response.Result;
import io.sofastack.cloud.core.kafka.KafkaSender;
import io.sofastack.cloud.web.model.RequestInfo;
import io.sofastack.cloud.web.service.AccountService;
import io.sofastack.cloud.web.service.TradeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author: guolei.sgl (guolei.sgl@antfin.com) 2019/3/13 11:24 AM
 * @since:
 **/
@RestController
public class TransferController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebRouterController.class);

    @Autowired
    private AccountService      accountService;
    @Autowired
    private TradeService        tradingService;

    @Autowired
    private KafkaSender         kafkaSender;

    @RequestMapping("transfer")
    public RestFacadeResp<String> transfer(RequestInfo request) {
        LOGGER.info("Receive transfer requestInfo:[{}]", request.toString());
        RestFacadeResp<String> result = new RestFacadeResp<>();
        try {
            // 触发转账
            Result<SerialOrder> resultData = accountService.transfer(request);
            if (resultData.isSuccess()) {
                // 创建订单
                Result<TradingOrder> tradingOrder = tradingService.createTradingOrder(parseRequest(
                    request, resultData.getData()));
                if (tradingOrder.isSuccess()) {
                    result.setData(tradingOrder.getData().getId());
                    result.setSuccess(true);
                } else {
                    result.setData(tradingOrder.getErrorMsg());
                    result.setSuccess(false);
                }
            } else {
                result.setData(resultData.getErrorMsg());
                result.setSuccess(false);
            }
        } catch (Throwable t) {
            LOGGER.error("Failed to transfer.", t);
            result.setSuccess(false);
            result.setData(t.getMessage());
        } finally {
            kafkaSender.sendChannelMessage(KafkaConstants.TRADE_TOPIC,
                String.valueOf(result.isSuccess()) + ":" + request.getUserId());
        }
        return result;
    }

    /**
     * 解析 RequestInfo ，组合成交易上下文信息
     *
     * @param request
     * @param serialOrder
     * @return
     */
    private TradingContext parseRequest(RequestInfo request, SerialOrder serialOrder) {
        TradingContext tradingContext = new TradingContext();
        tradingContext.setCreateTime(new Date());
        tradingContext.setOrderAmount(BigDecimal.valueOf(request.getMoney()));
        tradingContext.setOrderGroup("转账");
        tradingContext.setOrderMark(request.getRemark());
        tradingContext.setOrderState(serialOrder.getDataFlag() == 1 ? "交易成功" : "交易失败");
        tradingContext.setOrderType("转账");
        tradingContext.setSerialNo(serialOrder.getId());
        tradingContext.setPayChannel("支付宝");
        tradingContext.setSourceAccount(request.getUserId());
        tradingContext.setTargetAccount(request.getTargetId());
        return tradingContext;
    }
}
