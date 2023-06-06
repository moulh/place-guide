package com.moulh.pg.busi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @ClassName : User
 * @Author : moulh
 * @Date : 2021-07-22 15:32
 * @Version : V1.0
 * @Description :
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_si_user")
@ApiModel(value="user对象", description="用户信息")
public class User extends Model<User> {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户名称")
    private String userName;
    @ApiModelProperty(value = "用户密码")
    private String pwd;
    @ApiModelProperty(value = "城市code")
    private String areaCode;
    @ApiModelProperty(value = "联系电话")
    private String tel;
}
