package com.moulh.pg.busi.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 对象评论表
 * </p>
 *
 * @author moulh
 * @since 2021-07-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_b_comment")
@ApiModel(value="Comment对象", description="对象评论表")
public class Comment extends Model<Comment> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer objectId;

    @ApiModelProperty(value = "游客id")
    private Integer visitorId;

    @ApiModelProperty(value = "评论分，范围0.5-5，小数只能是0.5，例如1.5，2.5")
    private BigDecimal commentScore;

    @ApiModelProperty(value = "评论内容最多400字")
    private String commentDesc;

    @ApiModelProperty(value = "标签列表，以逗号分割，存放标签id，最多只能有8个")
    private String tagsList;

    @ApiModelProperty(value = "人均消费金额")
    private BigDecimal priceOfPerson;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
