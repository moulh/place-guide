package com.moulh.pg.busi.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

/**
 * @ClassName : SpotObjectListVo
 * @Author : moulh@paxsz.com
 * @Date : 2021-07-11 18:16
 * @Version : V1.0
 * @Description : 餐饮列表参数
 */
@Getter
@Setter
@ToString
@ApiModel(value = "餐饮列表参数")
public class RestObjectListVo {
    @ApiModelProperty(value = "逻辑主键")
    private Integer id;

    @ApiModelProperty(value = "餐饮名称")
    private String objectName;

    @ApiModelProperty(value = "餐饮20字以内简介")
    private String objectShortDesc;

    @ApiModelProperty(value = "列表展示的图片url")
    private String objectMainImageUrl;

    @ApiModelProperty(value = "经度")
    private BigDecimal lat;

    @ApiModelProperty(value = "纬度")
    private BigDecimal lng;

    @ApiModelProperty(value = "距离")
    private BigDecimal distance;

    @ApiModelProperty(value = "人均消费金额，取评论表中平均值")
    private BigDecimal priceOfPerson;

    @ApiModelProperty(value = "热度，百分比")
    private BigDecimal perOfHeat;

    @ApiModelProperty(value = "好评率，百分比")
    private BigDecimal perOfGood;

    @ApiModelProperty(value = "评论数")
    private BigDecimal commentCount;

    @ApiModelProperty(value = "标签信息")
    private List<TagsVo> tagsVoList;
}
