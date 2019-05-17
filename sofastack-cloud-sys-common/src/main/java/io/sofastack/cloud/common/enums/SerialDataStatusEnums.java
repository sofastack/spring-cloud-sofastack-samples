package io.sofastack.cloud.common.enums;

/**
 * @author: guolei.sgl (guolei.sgl@antfin.com) 2019/3/21 9:44 PM
 * @since:
 **/
public enum SerialDataStatusEnums {
    /**
     * 交易出错
     */
    PASS_ERROR(-1),
    /**
     * 允许继续交易
     */
    ALLOW(1),
    /**
     * 禁止继续交易
     */
    FORBID(0);
    private int val;

    SerialDataStatusEnums(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }
}
