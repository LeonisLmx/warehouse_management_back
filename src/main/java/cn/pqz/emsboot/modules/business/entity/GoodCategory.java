package cn.pqz.emsboot.modules.business.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author urey.liu
 * @description
 * @date 2023/6/30 4:40 下午
 */
@Data
public class GoodCategory implements Serializable {

    private Long id;

    private Long parentId;

    private String name;
}
