package cn.pqz.emsboot.component.util;

import java.util.Arrays;

/**
 * @author urey.liu
 * @description
 * @date 2023/6/21 6:16 下午
 */
public enum OrderStateEnum {

    LOSS_GOOD(0,"缺货"),
    NEW_ORDER(1,"订购"),
    ORDER_SCHEDULE(2,"待调度"),
    ORDER_WAREHOUSE(3,"订单出库"),
    ORDER_TRANSPORT(4,"订单运输"),
    GOOD_IN_STORAGE(5,"验货入库"),
    ORDER_WAIT_ALLOCATION(6,"待分配"),
    ALLOCATION_OUT_STORAGE(7,"配送出库"),
    DELIVERY_GOOD(8,"领货配送"),
    FINISH(9,"回执录入"),
    PORTION_FINISH(10,"部分完成"),
    FAIL(11,"失败");

    private int code;
    private String description;

    OrderStateEnum(int code, String description){
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

    public static OrderStateEnum parseState(Integer code){
        return Arrays.stream(OrderStateEnum.values()).filter(n -> n.getCode() == code).findAny().get();
    }
}
