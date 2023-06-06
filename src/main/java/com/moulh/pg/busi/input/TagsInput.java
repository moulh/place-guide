package com.moulh.pg.busi.input;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @ClassName : TagsInput
 * @Author : moulh
 * @Date : 2021-07-12 11:59
 * @Version : V1.0
 * @Description : 标签新增参数
 */
@Getter
@Setter
@ToString
@ApiModel(value = "标签新增参数")
public class TagsInput {
    @ApiModelProperty(value = "该景点/住宿/餐饮对应的id")
    private Integer objectId;

    @ApiModelProperty(value = "标签内容，10个字以内")
    private String tagsContent;
}
