package com.moulh.pg.core.dto;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.moulh.pg.core.util.ObjectConverter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName : PageResultDTO
 * @Author : moulh
 * @Date : 2021-07-11 18:13
 * @Version : V1.0
 * @Description :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "自定义分页返回对象")
public class PageResultDTO<T> implements Serializable {
    @ApiModelProperty("当前页码")
    private long current;
    @ApiModelProperty("每页条数")
    private long size;
    @ApiModelProperty("总记录数")
    private long total;
    @ApiModelProperty("分页数据")
    private List<T> records;

    public static <T> PageResultDTO<T> of(IPage<?> iPage, Class<T> targetClazzType) {
        return new PageResultDTO<>(iPage, targetClazzType);
    }

    private PageResultDTO(IPage<?> iPage, Class<T> targetClazzType) {
        this.current = iPage.getCurrent();
        this.size = iPage.getSize();
        this.total = iPage.getTotal();
        records =new ArrayList<>();
        if (!CollectionUtils.isEmpty(iPage.getRecords())) {
            iPage.getRecords().forEach(record -> {
                T data = ObjectConverter.convert(record, targetClazzType);
                records.add(data);
            });
        }
    }

    public static <T> PageResultDTO<T> of(IPage<T> iPage) {
        return new PageResultDTO<>(iPage.getCurrent(), iPage.getSize(), iPage.getTotal(), iPage.getRecords());
    }

    public static <T> PageResultDTO<T> of(PageInfo pageInfo, List<T> records, long total) {
        return new PageResultDTO<>(pageInfo.getCurrent(), pageInfo.getSize(), total, records);
    }
}
