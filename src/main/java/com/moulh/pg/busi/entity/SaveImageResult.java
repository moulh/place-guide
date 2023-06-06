package com.moulh.pg.busi.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @ClassName : SaveImageResult
 * @Author : moulh
 * @Date : 2021-08-02 10:10
 * @Version : V1.0
 * @Description :
 */
@Getter
@Setter
@ToString
public class SaveImageResult {
    @ApiModelProperty(value = "图片逻辑主键")
    private Integer imageId;
    @ApiModelProperty(value = "图片下载地址")
    private String imageUrl;
}
