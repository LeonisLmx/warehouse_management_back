package cn.pqz.emsboot.modules.warehouse.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class WarehouseTransition implements Serializable {
    private Integer id;
   private Integer lid;
    private Integer tid;
    private Double used;
}
