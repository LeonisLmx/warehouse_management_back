package cn.pqz.emsboot.modules.output.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class Client implements Serializable {

    private Integer id;
    private String name;
    private String companyName;
    private String landlinePhone;
    private String phone;
    private String cardNumber;
    private String address;
    private String postcode;
    private String email;
    private String note;
    private Boolean enabled;
    private Date createTime;
    private Date updateTime;
    @TableField(exist = false)
    List<OrderList> orders;
}
