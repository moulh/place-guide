package com.moulh.pg.core.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName : PageInfo
 * @Author : moulh
 * @Date : 2021-07-11 18:14
 * @Version : V1.0
 * @Description :
 */
@Data
public class PageInfo {
    @ApiModelProperty(value = "当前页码",dataType = "int")
    private Integer current;
    @ApiModelProperty(value = "每页条数",dataType = "int")
    private Integer size;

    public PageInfo() {
        this.current = 1;
        this.size = 10;
    }

    public Integer getCurrent() {
        if (current == null || current < 0) {
            return 1;
        }
        return current;
    }

    public Integer getSize() {
        if (size == null) {
            return 10;
        }
        if (size > 100) {
            return 100;
        }
        return size;
    }
}
