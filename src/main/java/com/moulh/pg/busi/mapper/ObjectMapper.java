package com.moulh.pg.busi.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.moulh.pg.busi.entity.Object;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.moulh.pg.busi.vo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author moulh
 * @since 2021-07-11
 */
public interface ObjectMapper extends BaseMapper<Object> {
    List<TagsVo> queryObjectTagsMsg(Integer objectId);

    IPage<SpotObjectListVo> querySpotList(Page<SpotObjectListVo> page, @Param("searchMap") Map<String, java.lang.Object> searchMap);

    IPage<AccObjectListVo> queryAccList(Page<AccObjectListVo> page, @Param("searchMap") Map<String, java.lang.Object> searchMap);

    IPage<RestObjectListVo> queryRestList(Page<RestObjectListVo> page, @Param("searchMap") Map<String, java.lang.Object> searchMap);

    SpotObjectDetailVo querySpotDetail(Integer objectId);

    AccObjectDetailVo queryAccDetail(Integer objectId);

    RestObjectDetailVo queryRestDetail(Integer objectId);

    ObjectVo queryObjectDetail(Integer objectId);

    IPage<ObjectListVo> queryObjectList(Page<ObjectListVo> page, @Param("searchMap") Map<String, java.lang.Object> searchMap);
}
