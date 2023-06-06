package com.moulh.pg.busi.input;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

/**
 * @ClassName : ObjectInput
 * @Author : moulh
 * @Date : 2021-07-13 16:57
 * @Version : V1.0
 * @Description : 景点/住宿/餐饮基础信息修改参数
 */
@Getter
@Setter
@ToString
@ApiModel(value = "景点/住宿/餐饮基础信息修改参数")
public class ObjectUpdateInput {
    private Integer id;
    @ApiModelProperty(value = "1-景点，2-住宿，3-餐饮")
    private Integer objectType;
    @ApiModelProperty(value = "对象名称")
    private String objectName;
    @ApiModelProperty(value = "对象20字以内简介")
    private String objectShortDesc;
    @ApiModelProperty(value = "主体展示图片")
    private Integer objectMainImageid;
    @ApiModelProperty(value = "经度")
    private BigDecimal lat;
    @ApiModelProperty(value = "纬度")
    private BigDecimal lng;
    @ApiModelProperty(value = "联系电话")
    private String tel;
    @ApiModelProperty(value = "明细图片信息集合")
    private List<ObjectImagesInput> objectImagesInputs;
}
