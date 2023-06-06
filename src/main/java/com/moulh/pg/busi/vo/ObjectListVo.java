package com.moulh.pg.busi.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * @ClassName : ObjectListVo
 * @Author : moulh
 * @Date : 2021-07-15 17:26
 * @Version : V1.0
 * @Description : 景点/住宿/餐饮列表展示参数
 */
@Getter
@Setter
@ToString
@ApiModel(value = "景点/住宿/餐饮列表展示参数")
public class ObjectListVo {
    @ApiModelProperty(value = "逻辑主键")
    private Integer id;
    @ApiModelProperty(value = "城市名称")
    private String areaName;
    @ApiModelProperty(value = "1-景点，2-住宿，3-餐饮")
    private Integer objectType;
    @ApiModelProperty(value = "对象名称")
    private String objectName;
    @ApiModelProperty(value = "经度")
    private BigDecimal lat;
    @ApiModelProperty(value = "纬度")
    private BigDecimal lng;
    @ApiModelProperty(value = "联系电话")
    private String tel;
}
