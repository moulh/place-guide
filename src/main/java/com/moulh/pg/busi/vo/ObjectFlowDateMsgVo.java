package com.moulh.pg.busi.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @ClassName : ObjectFlowDateMsgVo
 * @Author : moulh@paxsz.com
 * @Date : 2021-07-26 14:19
 * @Version : V1.0
 * @Description :
 */
@Getter
@Setter
@ToString
@ApiModel(value = "DashBoard每日明细展示参数")
public class ObjectFlowDateMsgVo {
    @ApiModelProperty(value = "日期")
    private String date;
    @ApiModelProperty(value = "星期几")
    private String weekNum;
    @ApiModelProperty(value = "浏览次数")
    private Integer visitNum;
}
