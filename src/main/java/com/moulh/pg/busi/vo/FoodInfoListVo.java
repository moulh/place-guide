package com.moulh.pg.busi.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * <p>
 * 
 * </p>
 *
 * @author moulh
 * @since 2021-07-24
 */
@Getter
@Setter
@ToString
@ApiModel(value = "推荐菜列表信息")
public class FoodInfoListVo {
    @ApiModelProperty(value = "菜品id")
    private Integer foodId;

    @ApiModelProperty(value = "对象名称")
    private String objectName;

    @ApiModelProperty(value = "菜品名称，20字以内")
    private String foodName;

    @ApiModelProperty(value = "消费金额")
    private BigDecimal price;
    @ApiModelProperty(value = "排序序号")
    private Integer orderNo;

    @ApiModelProperty(value = "标签，10字以内")
    private String tag;
}
