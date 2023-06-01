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
 * @Description :
 */
@Getter
@Setter
@ToString
@ApiModel(value = "修改密码请求参数")
public class UserUpdatePwdInput {
    @ApiModelProperty(value = "用户名")
    private String username;
    @ApiModelProperty(value = "旧密码")
    private String oldPassword;
    @ApiModelProperty(value = "新密码")
    private String newPassword;
}
