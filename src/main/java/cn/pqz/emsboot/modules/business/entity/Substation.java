package cn.pqz.emsboot.modules.business.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author urey.liu
 * @description
 * @date 2023/6/26 4:58 下午
 */
@Data
public class Substation implements Serializable {

    private Long id;

    private String name;

    private Long parentId;

    private Date date;
}
