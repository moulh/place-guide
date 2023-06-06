package com.moulh.pg.busi.input;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * @ClassName : CommentInput
 * @Author : moulh
 * @Date : 2021-07-12 11:55
 * @Version : V1.0
 * @Description : 游客新增评论参数
 */
@Getter
@Setter
@ToString
@ApiModel(value = "游客新增评论参数")
public class CommentInput {
    @ApiModelProperty(value = "该景点/住宿/餐饮对应的id")
    private Integer objectId;

    @ApiModelProperty(value = "游客名称")
    private String visitorName;

    @ApiModelProperty(value = "游客头像url")
    private String visitorImageUrl;

    @ApiModelProperty(value = "评论分，范围0.5-5，小数只能是0.5，例如1.5，2.5")
    private BigDecimal commentScore;

    @ApiModelProperty(value = "评论内容最多400字")
    private String commentDesc;

    @ApiModelProperty(value = "标签列表，以逗号分割，存放标签id，最多只能有8个")
    private String tagsList;

    @ApiModelProperty(value = "人均消费金额")
    private BigDecimal priceOfPerson;
}
