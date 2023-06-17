package cn.pqz.emsboot.modules.communication.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Message {
    private Integer id;
    private String username;
    private String userface;
    private String text;
    private Date date;
}
