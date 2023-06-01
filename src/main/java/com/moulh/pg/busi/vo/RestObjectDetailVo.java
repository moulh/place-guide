package com.moulh.pg.busi.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

/**
 * @ClassName : SpotObjectDetailVo
 * @Author : moulh@paxsz.com
 * @Date : 2021-07-11 18:26
 * @Version : V1.0
 * @Description : 餐饮详情展示参数
 */
@Getter
@Setter
@ToString
@ApiModel(value = "餐饮详情展示参数")
public class RestObjectDetailVo {
    @ApiModelProperty(value = "餐饮名称")
    private String objectName;

    @ApiModelProperty(value = "餐饮20字以内简介")
    private String objectShortDesc;

    @ApiModelProperty(value = "经度")
    private BigDecimal lat;

    @ApiModelProperty(value = "纬度")
    private BigDecimal lng;

    @ApiModelProperty(value = "好评率，百分比")
    private BigDecimal perOfGood;

    @ApiModelProperty(value = "星级, 取的所有评论星级的平均值")
    private BigDecimal starMsg;

    @ApiModelProperty(value = "评论数")
    private BigDecimal commentCount;

    @ApiModelProperty(value = "联系电话")
    private String tel;

    @ApiModelProperty(value = "标签信息")
    private List<TagsVo> tagsVoList;

    @ApiModelProperty(value = "图片信息")
    private List<ObjectImagesVo> objectImagesVoList;
}
