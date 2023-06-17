package cn.pqz.emsboot.modules.output.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class OutputLog implements Serializable {

    private Integer id;
    private String orderNum;
    private String orderName;
    private Date startTime;
    private Date endTime;
    private Integer oid;
    private Integer operator;
    @TableField(exist = false)
    private String username;
}
