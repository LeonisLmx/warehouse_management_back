package cn.pqz.emsboot.modules.output.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Client_order implements Serializable {

    private Integer id;
    private Integer cid;
    private Integer oid;
}
