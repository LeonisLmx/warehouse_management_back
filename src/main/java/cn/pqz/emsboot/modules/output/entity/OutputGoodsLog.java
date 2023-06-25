package cn.pqz.emsboot.modules.output.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author urey.liu
 * @description
 * @date 2023/6/25 1:54 下午
 */
@Data
public class OutputGoodsLog implements Serializable {

    private Long id;
    private Long goodsId;
    private String orderNum;
    private Date createTime;
}
