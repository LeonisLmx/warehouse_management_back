package cn.pqz.emsboot.modules.warehouse.entity;

import cn.pqz.emsboot.modules.output.entity.Client;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Goods implements Serializable {
    private Integer id;
    private String num;
    private String orderNum;
    private String name;
    private String codeName;
    private Double count;
    private Double remainCount;
    /**
     * 入库类型
     */
    private Integer type;
    /**
     * 客户ID
     */
    private Integer clientId;
    private Date date;
    @TableField(exist = false)
    private Client client;
    private String operator;
    /**
     * 快递公司编号
     */
    private String expCode;
    /**
     * 快递单号
     */
    private String expNo;
    /**
     * 是否出库
     */
    private Integer ship;

    private Long substationId;

    private Long supplierId;
}
