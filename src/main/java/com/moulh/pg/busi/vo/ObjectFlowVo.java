package com.moulh.pg.busi.vo;

import com.moulh.pg.core.util.DateUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.text.ParseException;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author moulh
 * @since 2021-07-24
 */
@Getter
@Setter
@ToString
@ApiModel(value = "DashBoard汇总展示参数")
public class ObjectFlowVo{

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "对象id")
    private Integer objectId;

    @ApiModelProperty(value = "对象名称")
    private String objectName;

    @ApiModelProperty(value = "浏览次数汇总")
    private Integer visitTotalNum;

    @ApiModelProperty(value = "每日浏览次数明细(当查询dateTimeStart = dateTimeEnd时该明细为空)")
    private List<ObjectFlowDateMsgVo> objectFlowDateMsgVoList;

}
