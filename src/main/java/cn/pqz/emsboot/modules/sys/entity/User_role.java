package cn.pqz.emsboot.modules.sys.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class User_role implements Serializable {

    private Integer id;
    private Integer uid;
    private Integer rid;
}
