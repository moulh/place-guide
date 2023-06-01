package com.moulh.pg.busi.mapper;

import com.moulh.pg.busi.entity.Tags;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.moulh.pg.busi.vo.TagsVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author moulh
 * @since 2021-07-11
 */
public interface TagsMapper extends BaseMapper<Tags> {
    List<TagsVo> queryTagsByObjectType(@Param("objectType") Integer objectType,
                                       @Param("objectId") Integer objectId,
                                       @Param("quantity") Integer quantity);

    void updateCount(Integer tagId);
}
