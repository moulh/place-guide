package com.moulh.pg.busi.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
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
 * @since 2021-07-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_b_object_images")
@ApiModel(value="ObjectImages对象", description="")
public class ObjectImages extends Model<ObjectImages> {

    private static final long serialVersionUID = 1L;

    private Integer objectId;

    private Integer imagesId;

    @ApiModelProperty(value = "图片标题 10字以内")
    private String imagesTitle;

    @ApiModelProperty(value = "图片描述 200字以内")
    private String imagesDesc;

    @ApiModelProperty(value = "图片类型，1-主体图片，2-明细图片")
    private Integer imagesType;


    @Override
    protected Serializable pkVal() {
        return this.objectId;
    }

}
