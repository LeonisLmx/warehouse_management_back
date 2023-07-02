package cn.pqz.emsboot.modules.warehouse.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class SupplierGoods {

    private Long id;

    private String code;

    private String goodsName;

    private Integer firstCategory;

    private Integer secondCategory;

    private String measurement;

    private Integer count;

    private BigDecimal originPrice;

    private String discount;

    private BigDecimal price;

    private String model;

    private Long supplierId;

    private String factoryName;

    private String shelfLife;

    private Boolean enabledReturn;

    private String content;

    private Date date;

    private Long goodsId;
}
