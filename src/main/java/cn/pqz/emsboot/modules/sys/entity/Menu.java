package cn.pqz.emsboot.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Menu implements Serializable {

    private Integer id;
    private String pattern;
    private String path;
    private Integer level;
    private String name;
    private String icon;
    private Integer parentId;
    private Boolean enable;
    @TableField(exist = false)
    private List<Role> roles;
    @TableField(exist = false)
    private List<Menu> children;
}
