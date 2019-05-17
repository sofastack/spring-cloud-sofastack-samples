package io.sofastack.cloud.common.response;

/**
 * @author: guolei.sgl (guolei.sgl@antfin.com) 2019/3/5 4:33 PM
 * @since:
 **/
public class RestFacadeResp<T> {

    private T       data;

    private boolean success = false;

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
}
