package com.moulh.pg.busi.mapper;

import com.moulh.pg.busi.entity.Area;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.moulh.pg.busi.vo.AreaVo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author moulh
 * @since 2021-07-11
 */
public interface AreaMapper extends BaseMapper<Area> {
    List<AreaVo> queryAll();
}
