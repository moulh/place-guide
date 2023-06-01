package com.moulh.pg.busi.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
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
 * @since 2021-07-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_b_object_detail")
@ApiModel(value="ObjectDetail对象", description="")
public class ObjectDetail extends Model<ObjectDetail> {

    private static final long serialVersionUID = 1L;

    private Integer objectId;

    @ApiModelProperty(value = "对象详细简介")
    private String objectDesc;

    @ApiModelProperty(value = "人均消费金额，取评论表中平均值")
    private BigDecimal priceOfPerson;

    @ApiModelProperty(value = "点赞数")
    private Integer numOfLikes;

    @ApiModelProperty(value = "游客数量")
    private Integer numOfVisitors;

    @ApiModelProperty(value = "热度，百分比")
    private BigDecimal perOfHeat;

    @ApiModelProperty(value = "好评率，百分比")
    private BigDecimal perOfGood;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;


    @Override
    protected Serializable pkVal() {
        return this.objectId;
    }

}
