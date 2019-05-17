package io.sofastack.cloud.web.model;

/**
 * @author: guolei.sgl (guolei.sgl@antfin.com) 2019/3/11 6:44 PM
 * @since:
 **/
public class RequestInfo {
    private int    userId;
    private int    targetId;
    private double money;
    private String password;
    private String remark;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTargetId() {
        return targetId;
    }

    public void setTargetId(int targetId) {
        this.targetId = targetId;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRemark() {

        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "RequestInfo{" + "userId=" + userId + ", targetId=" + targetId + ", money=" + money
               + ", password='" + password + '\'' + ", remark='" + remark + '\'' + '}';
    }
}
