package com.moulh.pg.busi.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @ClassName : CommentVo
 * @Author : moulh
 * @Date : 2021-07-19 15:21
 * @Version : V1.0
 * @Description : 评论展示参数
 */
@Getter
@Setter
@ToString
@ApiModel(value = "评论展示参数")
public class CommentVo {
    private Integer objectId;

    @ApiModelProperty(value = "游客id")
    private Integer visitorId;

    @ApiModelProperty(value = "游客名称")
    private String visitorName;

    @ApiModelProperty(value = "游客头像url")
    private String visitorImageUrl;

    @ApiModelProperty(value = "评论分，范围0.5-5，小数只能是0.5，例如1.5，2.5")
    private BigDecimal commentScore;

    @ApiModelProperty(value = "评论内容最多400字")
    private String commentDesc;

    @ApiModelProperty(value = "人均消费金额")
    private BigDecimal priceOfPerson;

    @ApiModelProperty(value = "创建时间")
    private String createTime;
}
