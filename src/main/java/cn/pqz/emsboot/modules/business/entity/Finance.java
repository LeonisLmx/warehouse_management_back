package cn.pqz.emsboot.modules.business.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author urey.liu
 * @description
 * @date 2023/6/25 4:19 下午
 */
@Data
public class Finance implements Serializable {

    private Long id;

    private Integer action;

    private Long amount;

    private Integer role;

    private Date createTime;
}
