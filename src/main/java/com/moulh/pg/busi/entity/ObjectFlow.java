package com.moulh.pg.busi.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author moulh
 * @since 2021-07-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_b_object_flow")
@ApiModel(value="ObjectFlow对象", description="")
public class ObjectFlow extends Model<ObjectFlow> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "对象id")
    private Integer objectId;

    @ApiModelProperty(value = "时间yyyymmdd")
    private String date;

    @ApiModelProperty(value = "浏览次数")
    private Integer visitNum;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;


    @Override
    protected Serializable pkVal() {
        return this.objectId;
    }

}
