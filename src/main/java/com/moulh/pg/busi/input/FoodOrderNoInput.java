package com.moulh.pg.busi.input;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @ClassName : FoodOrderNoInput
 * @Author : moulh
 * @Date : 2021-07-24 14:25
 * @Version : V1.0
 * @Description :
 */
@Getter
@Setter
@ToString
@ApiModel(value = "修改推荐菜排序参数")
public class FoodOrderNoInput {
    @ApiModelProperty(value = "菜品id")
    private Integer foodId;
    @ApiModelProperty(value = "排序序号")
    private Integer orderNo;
}
