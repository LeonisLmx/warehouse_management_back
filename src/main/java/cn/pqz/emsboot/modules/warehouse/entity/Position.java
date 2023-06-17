package cn.pqz.emsboot.modules.warehouse.entity;

import lombok.Data;

import java.util.List;

/**
 * 用于存储业务中的级联选择器
 */
@Data
public class Position {
    Integer id;
    String num;
    Double percentage;
    Integer value;
    String label;
    List<Position> children;
}
