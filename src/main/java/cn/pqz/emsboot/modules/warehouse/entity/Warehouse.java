package cn.pqz.emsboot.modules.warehouse.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Warehouse implements Serializable {
    private Integer id;
    private String num;
    private String name;
    private String address;
    private Double area;
    private Double capacity;
    private Double used;
    private Double percentage;
    private Integer parentId;
    private Integer type;
    private Integer layer;
    private Boolean state;
    @TableField(exist = false)
    private List<Warehouse> shelf;
    @TableField(exist = false)
    private List<Warehouse> children;
}
