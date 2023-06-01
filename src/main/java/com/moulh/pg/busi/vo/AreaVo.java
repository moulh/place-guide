package com.moulh.pg.busi.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @ClassName : AreaVo
 * @Author : moulh@paxsz.com
 * @Date : 2021-08-10 14:06
 * @Version : V1.0
 * @Description : 城市信息
 */
@Getter
@Setter
@ToString
@ApiModel(value = "城市信息")
public class AreaVo {
    @ApiModelProperty(value = "城市code值")
    private String areaCode;
    @ApiModelProperty(value = "城市")
    private String areaName;
}
