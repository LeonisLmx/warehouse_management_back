package cn.pqz.emsboot.component.util;

/**
 * @author urey.liu
 * @description
 * @date 2023/6/21 6:23 下午
 */
public enum OrderTypeEnum {

    NEW_ORDER(1,"新订"),
    BACK_ORDER(2,"退订"),
    EXCHANGE_GOOD(3,"换货"),
    BACK_GOOD(4,"退货");

    private int code;
    private String description;

    OrderTypeEnum(int code, String description){
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static OrderTypeEnum valueOf(Integer code){
        for (OrderTypeEnum value : OrderTypeEnum.values()) {
            if (value.getCode() == code){
                return value;
            }
        }
        return null;
    }
}
