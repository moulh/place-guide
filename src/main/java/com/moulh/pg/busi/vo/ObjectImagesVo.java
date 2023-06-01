package com.moulh.pg.busi.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author moulh
 * @since 2021-07-11
 */
@Getter
@Setter
@ToString
@ApiModel(value = "图片信息展示参数")
public class ObjectImagesVo{
    private Integer objectId;
    @ApiModelProperty(value = "图片id")
    private Integer imagesId;
    @ApiModelProperty(value = "图片标题 10字以内")
    private String imagesTitle;
    @ApiModelProperty(value = "图片描述 200字以内")
    private String imagesDesc;
    @ApiModelProperty(value = "图片url")
    private String imageUrl;
    @ApiModelProperty(value = "图片类型，1-主体图片，2-明细图片")
    private Integer imagesType;

}
