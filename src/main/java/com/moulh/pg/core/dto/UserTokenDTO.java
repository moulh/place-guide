package com.moulh.pg.core.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;

/**
 * @ClassName : UserTokenDTO
 * @Author : moulh@paxsz.com
 * @Date : 2022-06-07 10:39
 * @Version : V1.0
 * @Description : 用户token信息对象
 */
@Data
@ApiModel(value = "UserTokenDTO", description = "用户token信息对象")
public class UserTokenDTO {
    private Integer id;
    private String userName;
    private Long gmtCreate;
}
