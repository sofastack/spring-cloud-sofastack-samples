package io.sofastack.cloud.common.model;

/**
 * @author: guolei.sgl (guolei.sgl@antfin.com) 2019/3/21 5:30 PM
 * @since:
 **/
public class TransferResponse {

    private boolean success;
    private String  orderNo;
    private String  exception;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }
}
