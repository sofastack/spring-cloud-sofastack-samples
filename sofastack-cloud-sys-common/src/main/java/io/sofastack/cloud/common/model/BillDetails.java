package io.sofastack.cloud.common.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author: guolei.sgl (guolei.sgl@antfin.com) 2019/3/12 6:18 PM
 * @since:
 **/
public class BillDetails {

    private int        id;

    private int        pay;

    private int        income;

    private BigDecimal payAmount;

    private BigDecimal incomeAmount;

    private int        userId;

    private Date       createTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPay() {
        return pay;
    }

    public void setPay(int pay) {
        this.pay = pay;
    }

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public BigDecimal getIncomeAmount() {
        return incomeAmount;
    }

    public void setIncomeAmount(BigDecimal incomeAmount) {
        this.incomeAmount = incomeAmount;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "BillDetails{" + "id=" + id + ", pay=" + pay + ", income=" + income + ", payAmount="
               + payAmount + ", incomeAmount=" + incomeAmount + ", userId=" + userId
               + ", createTime=" + createTime + '}';
    }
}
