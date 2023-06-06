package com.moulh.pg.core.dto;

import lombok.Data;

/**
 * @ClassName : SysUser
 * @Author : moulh
 * @Date : 2021-07-09 16:17
 * @Version : V1.0
 * @Description :
 */
@Data
public class SysUser{
    private Integer id;
    private String userName;
    private String pwd;
    private String areaCode;
    private String tel;
}
