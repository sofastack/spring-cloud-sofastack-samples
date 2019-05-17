package io.sofastack.cloud.common.response;

/**
 * @author: guolei.sgl (guolei.sgl@antfin.com) 2019/3/21 9:38 PM
 * @since:
 **/
public class Result<T> {

    private T       data;
    private boolean success = false;
    private String  errorMsg;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
