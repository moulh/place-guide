package com.moulh.pg.busi.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @ClassName : ObjectCommentVo
 * @Author : moulh
 * @Date : 2021-07-12 11:47
 * @Version : V1.0
 * @Description : 住宿/景点/餐饮评论信息
 */
@Getter
@Setter
@ToString
@ApiModel(value = "住宿/景点/餐饮评论信息")
public class ObjectCommentVo {
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
}
