package cn.pqz.emsboot.modules.business.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author urey.liu
 * @description
 * @date 2023/7/3 4:05 下午
 */
@Data
public class InvoiceManage {

    private Long id;

    private String name;

    private BigDecimal price;

    private String description;

    private Date applyTime;

    private Integer status;

    private Date date;
}
