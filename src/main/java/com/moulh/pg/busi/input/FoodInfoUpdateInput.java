package com.moulh.pg.busi.input;

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
@ApiModel(value = "修改推荐菜参数")
public class FoodInfoUpdateInput{

    @ApiModelProperty(value = "推荐菜品id")
    private Integer foodId;

    @ApiModelProperty(value = "菜品名称，20字以内")
    private String foodName;

    @ApiModelProperty(value = "菜品描述，50字以内")
    private String foodDesc;

    @ApiModelProperty(value = "消费金额")
    private BigDecimal price;

    @ApiModelProperty(value = "标签，10字以内")
    private String tag;

    @ApiModelProperty(value = "图片id")
    private Integer imagesId;
}
