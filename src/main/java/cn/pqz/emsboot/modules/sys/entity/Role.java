package cn.pqz.emsboot.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Role implements Serializable {

    private Integer id;
    private String name;
    private String nameZh;
    private String remark;
    @TableField(exist = false)
    private List<Menu> menus;
}