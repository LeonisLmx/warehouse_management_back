package cn.pqz.emsboot.modules.output.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Client implements Serializable {

    private Integer id;
    private String name;
    private String phone;
    private String address;
    private String note;
    private Boolean enabled;
    @TableField(exist = false)
    List<OrderList> orders;
}
