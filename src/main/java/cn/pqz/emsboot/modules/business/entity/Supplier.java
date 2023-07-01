package cn.pqz.emsboot.modules.business.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Supplier {

    private Long id;

    private String name;

    private String address;

    private String contactUser;

    private String contactPhone;

    private String bankName;

    private String fax;

    private String postcode;

    private String legalPerson;

    private String content;

    private Date date;
}
