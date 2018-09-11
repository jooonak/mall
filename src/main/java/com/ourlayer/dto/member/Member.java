package com.ourlayer.dto.member;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Member {

    private String username;
    private String name;
    private String password;
    private Integer phone;
    private String address;
    private String detailAddress;
    private Integer point;
    private Date updDt;
    private Date regDt;
    private List<Role> roles;

}
