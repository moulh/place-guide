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
@ApiModel(value = "推荐菜详情")
public class FoodInfoVo {
    @ApiModelProperty(value = "菜品id")
    private Integer foodId;

    @ApiModelProperty(value = "对象id")
    private Integer objectId;

    @ApiModelProperty(value = "菜品名称，20字以内")
    private String foodName;

    @ApiModelProperty(value = "菜品描述，50字以内")
    private String foodDesc;

    @ApiModelProperty(value = "消费金额")
    private BigDecimal price;

    @ApiModelProperty(value = "标签，10字以内")
    private String tag;

    @ApiModelProperty(value = "图片id")
    private String imagesUrl;
}
