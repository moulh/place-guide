package com.moulh.pg.busi.input;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @ClassName : UserUpdatePwdInput
 * @Author : moulh@paxsz.com
 * @Date : 2021-07-22 15:28
 * @Version : V1.0
 * @Description : 用户新增请求参数
 */
@Getter
@Setter
@ToString
@ApiModel(value = "用户新增请求参数")
public class UserInput {
    @ApiModelProperty(value = "用户名")
    private String username;
    @ApiModelProperty(value = "密码")
    private String pwd;
    @ApiModelProperty(value = "联系电话")
    private String tel;
}
