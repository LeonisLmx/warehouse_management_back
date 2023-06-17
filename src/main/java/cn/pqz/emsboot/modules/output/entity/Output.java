package cn.pqz.emsboot.modules.output.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Output implements Serializable {
    private Integer id;
    private String name;
    private Integer total;
    private Double complete;
    private String note;
    private Boolean state;
    private Double percentage;
    private String orderNum;
    private String orderName;
}
