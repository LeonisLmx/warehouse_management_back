package cn.pqz.emsboot.modules.output.entity;

import lombok.Data;

@Data
public class Transition {
    private Integer id;
    private String orderNum;
    private String name;
    private Integer plan;
    private Double complete;
    private Integer outputId;
    private Integer state;
}
