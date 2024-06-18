package com.qf.xmall.sso.demo.common.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class LoginResponse implements Serializable {

    private static final long serialVersionUID = 2L;

    private Long id;

    private String username;

    private String password;

    private String phone;

    private String email;

    private Date created;

    private Date updated;

    private String sex;

    private String address;

    private Integer state;

    private String file;

    private String description;

    private Integer points;

    private BigDecimal balance;

    private Date birthday;

    private String token;

}
