package io.sofastack.cloud.common.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 转账请求信息
 *
 * @author: guolei.sgl (guolei.sgl@antfin.com) 2019/3/6 3:52 PM
 * @since:
 **/
public class TransferRequest {
    /** 用户ID */
    private int        userId;
    /** 用户账户ID */
    private int        accountId;
    /** 目标账户ID */
    private int        targetId;
    /** 转账金额 */
    private BigDecimal amountMoney;
    /** 转账备注信息 */
    private String     remark;
    /** 转账发起时间*/
    private Date       requestTime;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getTargetId() {
        return targetId;
    }

    public void setTargetId(int targetId) {
        this.targetId = targetId;
    }

    public BigDecimal getAmountMoney() {
        return amountMoney;
    }

    public void setAmountMoney(BigDecimal amountMoney) {
        this.amountMoney = amountMoney;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
    }
}
