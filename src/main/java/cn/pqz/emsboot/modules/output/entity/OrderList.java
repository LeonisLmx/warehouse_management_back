package cn.pqz.emsboot.modules.output.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class OrderList implements Serializable {

    private Integer id;
    private String name;
    private String orderNum;//订单号
    private Double price;//价格
    private Integer count;//数量
    private Boolean pay;//是否支付

    private Date date;//时间
    private Boolean transport;//是否发货
    private Integer orderState;//订单状态
}
