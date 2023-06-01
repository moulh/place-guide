package com.moulh.pg.busi.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author moulh
 * @since 2021-07-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_b_food_info")
@ApiModel(value="FoodInfo对象", description="")
public class FoodInfo extends Model<FoodInfo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "food_id", type = IdType.AUTO)
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

    @ApiModelProperty(value = "排序序号")
    private Integer orderNo;

    @ApiModelProperty(value = "标签，10字以内")
    private String tag;

    @ApiModelProperty(value = "点赞数,评论和浏览累加")
    private Integer goodsNum;

    @ApiModelProperty(value = "图片id")
    private Integer imagesId;


    @Override
    protected Serializable pkVal() {
        return this.objectId;
    }

}
