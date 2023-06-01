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
 * @since 2021-07-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_bi_tags")
@ApiModel(value="Tags对象", description="")
public class Tags extends Model<Tags> {

    private static final long serialVersionUID = 1L;

    private Integer id;

    @ApiModelProperty(value = "对象表")
    private Integer objectId;

    @ApiModelProperty(value = "标签内容，10个字以内")
    private String tagsContent;

    @ApiModelProperty(value = "标签累计数量")
    private Integer tagsCount;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
