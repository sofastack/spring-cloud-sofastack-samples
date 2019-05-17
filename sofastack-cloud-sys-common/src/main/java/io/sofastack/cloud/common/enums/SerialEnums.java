package io.sofastack.cloud.common.enums;

/**
 * @author: guolei.sgl (guolei.sgl@antfin.com) 2019/3/12 6:13 PM
 * @since:
 **/
public enum SerialEnums {
    /**
     * 支出交易类型
     */
    PAY("支出"),
    /**
     * 收入交易类型
     */
    INCOME("收入");
    private String type;

    SerialEnums(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
