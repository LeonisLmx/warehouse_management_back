package cn.pqz.emsboot.modules.output.http;

import lombok.Data;

import java.util.Date;

/**
 * @author urey.liu
 * @description
 * @date 2023/6/21 11:37 上午
 */
@Data
public class OrderResponse {

    private Long id;

    private Long clientId;

    private String orderNum;

    private String clientName;

    private String phone;

    private String name;

    private Integer count;

    private Integer orderState;

    private Integer orderType;

    private String operateName;

    private Date date;

    private String substation;
}
