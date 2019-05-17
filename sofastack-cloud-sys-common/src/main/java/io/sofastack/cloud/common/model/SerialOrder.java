package io.sofastack.cloud.common.model;

import io.sofastack.cloud.common.utils.OrderUtils;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 账户流水
 *
 * @author: guolei.sgl (guolei.sgl@antfin.com) 2019/3/11 11:09 PM
 * @since:
 **/
public class SerialOrder {

    public SerialOrder() {
        this.id = OrderUtils.getSerialNo(null);
    }

    private String     id;

    /**
     * 用户类型 0:用户 1:商家
     */
    private String     targetType;

    private Date       createTime;

    /**
     * 流水来源 1:交易订单
     */
    private String     serialSrc;

    /**
     * 流水类型 ：收入、支出
     */
    private String     serialType;

    private int        userId;

    private BigDecimal money;

    /**
     * 是否有效
     */
    private int        dataFlag;

    /**
     * 支付终端
     */
    private String     payTerminal;

    /**
     * 备注
     */
    private String     remark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTargetType() {
        return targetType;
    }

    public void setTargetType(String targetType) {
        this.targetType = targetType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getSerialSrc() {
        return serialSrc;
    }

    public void setSerialSrc(String serialSrc) {
        this.serialSrc = serialSrc;
    }

    public String getSerialType() {
        return serialType;
    }

    public void setSerialType(String serialType) {
        this.serialType = serialType;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public int getDataFlag() {
        return dataFlag;
    }

    public void setDataFlag(int dataFlag) {
        this.dataFlag = dataFlag;
    }

    public String getPayTerminal() {
        return payTerminal;
    }

    public void setPayTerminal(String payTerminal) {
        this.payTerminal = payTerminal;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
