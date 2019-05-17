package io.sofastack.cloud.accounting.restrpc;

import com.alipay.sofa.runtime.api.annotation.SofaService;
import com.alipay.sofa.runtime.api.annotation.SofaServiceBinding;
import io.sofastack.cloud.accounting.dao.AccountingMapper;
import io.sofastack.cloud.accounting.dao.SerialOrderMapper;
import io.sofastack.cloud.accounting.entity.AccountingEntity;
import io.sofastack.cloud.common.enums.SerialDataStatusEnums;
import io.sofastack.cloud.common.model.BalanceDetails;
import io.sofastack.cloud.common.model.SerialOrder;
import io.sofastack.cloud.common.model.TransferRequest;
import io.sofastack.cloud.common.response.Result;
import io.sofastack.cloud.common.utils.OrderUtils;
import io.sofastack.cloud.rest.facade.AccountingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * @author: guolei.sgl (guolei.sgl@antfin.com) 2019/3/5 8:09 PM
 * @since:
 **/
@Service
@SofaService(bindings = { @SofaServiceBinding(bindingType = "rest") })
public class AccountingServiceImpl implements AccountingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountingServiceImpl.class);

    @Autowired
    private AccountingMapper    accountingMapper;

    @Autowired
    private SerialOrderMapper   serialOrderMapper;

    @Override
    public Result<BalanceDetails> balanceDetail(Map<String, Object> params) {
        Result<BalanceDetails> result = new Result<>();
        BalanceDetails data = new BalanceDetails();
        int userId = (Integer) params.get("userId");
        data.setUserId(userId);
        try {
            AccountingEntity accountingEntity = accountingMapper.queryById(userId);
            data.setAvailableAssets(accountingEntity.getAvailableAssets());
            data.setTotalAssets(accountingEntity.getTotalAssets());
            data.setCreateTime(accountingEntity.getCreateTime());
            data.setFrozenAssets(accountingEntity.getFrozenAssets());
            data.setId(accountingEntity.getId());
            data.setLevel(accountingEntity.getLevel());
            data.setPayPassword(accountingEntity.getPayPassword());
            data.setPoints(accountingEntity.getPoints());
            result.setSuccess(true);
            result.setData(data);
        } catch (Throwable t) {
            LOGGER.error("Failed to get account balances.", t);
            result.setSuccess(false);
            result.setErrorMsg(t.getMessage());
        }
        return result;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public Result<SerialOrder> transfer(TransferRequest request) {
        Result<SerialOrder> result = new Result<>();
        // 生成流水记录
        SerialOrder serialSendOrder = createSendSerialOrder(request);
        AccountingEntity currentAccount = accountingMapper.queryById(request.getUserId());
        AccountingEntity targetAccount = accountingMapper.queryById(request.getTargetId());
        if (currentAccount.getAvailableAssets().compareTo(request.getAmountMoney()) > 0) {
            serialSendOrder.setDataFlag(SerialDataStatusEnums.ALLOW.getVal());
            serialSendOrder.setRemark(request.getRemark());
        } else {
            serialSendOrder.setDataFlag(SerialDataStatusEnums.FORBID.getVal());
            serialSendOrder.setRemark("账户余额不足");
        }
        // 流水单入库，如果 dataFlag 是 0 则结束，如果是1 则继续执行
        try {
            serialOrderMapper.save(serialSendOrder);
            if (serialSendOrder.getDataFlag() == SerialDataStatusEnums.FORBID.getVal()) {
                result.setSuccess(false);
                result.setErrorMsg("账户余额不足");
                result.setData(serialSendOrder);
                return result;
            }
            BigDecimal currentFrozen = currentAccount.getFrozenAssets().add(
                request.getAmountMoney());
            // 发起转账方账户冻结
            accountingMapper.frozonAssets(request.getUserId(), currentFrozen, currentAccount
                .getAvailableAssets().subtract(request.getAmountMoney()));
            // 发起接收方账户资金增加
            accountingMapper.updateAvailableAssets(request.getTargetId(), targetAccount
                .getAvailableAssets().add(request.getAmountMoney()), targetAccount.getTotalAssets()
                .add(request.getAmountMoney()));
            // 发起转账发账户资金扣除
            accountingMapper.deductAssets(request.getUserId(), currentFrozen.subtract(request
                .getAmountMoney()),
                currentAccount.getTotalAssets().subtract(request.getAmountMoney()));
            // 收款方流水入库
            SerialOrder serialReceiveOrder = createReceiveSerialOrder(request);
            int saveResult = serialOrderMapper.save(serialReceiveOrder);
            if (saveResult > 0) {
                serialReceiveOrder.setDataFlag(SerialDataStatusEnums.ALLOW.getVal());
                result.setSuccess(true);
            }
        } catch (Throwable t) {
            LOGGER.error("Failed to transfer.", t);
            result.setSuccess(false);
            result.setErrorMsg(t.getMessage());
        }
        // 这里只关心付款方的交易流水号
        result.setData(serialSendOrder);
        return result;
    }

    @Override
    public Result<Boolean> checkPayPassword(Map<String, Object> params) {
        Result<Boolean> result = new Result<>();
        try {
            int count = accountingMapper.queryByIdAndPayPassword((Integer) params.get("userId"),
                String.valueOf(params.get("payPassword")));
            if (count > 0) {
                result.setData(true);
                result.setSuccess(true);
            } else {
                result.setData(false);
                result.setSuccess(true);
            }
        } catch (Throwable t) {
            LOGGER.error("Failed to check payPassword.", t);
            result.setSuccess(false);
            result.setErrorMsg(t.getMessage());
        }
        return result;
    }

    @Override
    public Result<Boolean> createAccount(Map<String, Object> params) {
        Result<Boolean> result = new Result<>();
        try {
            AccountingEntity accountingEntity = new AccountingEntity();
            accountingEntity.setAvailableAssets(new BigDecimal(9900));
            accountingEntity.setFrozenAssets(new BigDecimal(99));
            accountingEntity.setTotalAssets(new BigDecimal(9999));
            accountingEntity.setCreateTime(new Date());
            accountingEntity.setLevel(1);
            accountingEntity.setState("1");
            accountingEntity.setPayPassword(String.valueOf(params.get("payPassword")));
            accountingEntity.setPoints(9999);
            accountingEntity.setType("normal");
            accountingEntity.setUserId((Integer) params.get("userId"));
            accountingMapper.insert(accountingEntity);
            result.setData(true);
            result.setSuccess(true);
        } catch (Throwable t) {
            LOGGER.error("Failed to check payPassword.", t);
            result.setSuccess(false);
            result.setErrorMsg(t.getMessage());
        }
        return result;
    }

    /**
     * 创建转账方流水订单
     * @param request
     * @return
     */
    private SerialOrder createSendSerialOrder(TransferRequest request) {
        SerialOrder serialOrder = new SerialOrder();
        serialOrder.setId(OrderUtils.getSerialNo(request));
        serialOrder.setCreateTime(new Date());
        serialOrder.setMoney(request.getAmountMoney());
        serialOrder.setSerialType("支出");
        serialOrder.setTargetType("用户");
        serialOrder.setSerialSrc("转账订单");
        serialOrder.setPayTerminal("pc");
        serialOrder.setUserId(request.getUserId());
        return serialOrder;
    }

    /**
     * 创建收款方流水订单
     * @param request
     * @return
     */
    private SerialOrder createReceiveSerialOrder(TransferRequest request) {
        SerialOrder serialOrder = new SerialOrder();
        serialOrder.setId(OrderUtils.getSerialNo(request));
        serialOrder.setCreateTime(new Date());
        serialOrder.setMoney(request.getAmountMoney());
        serialOrder.setSerialType("收入");
        serialOrder.setTargetType("用户");
        serialOrder.setSerialSrc("转账订单");
        serialOrder.setPayTerminal("pc");
        serialOrder.setUserId(request.getTargetId());
        return serialOrder;
    }

}
