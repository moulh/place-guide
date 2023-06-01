package com.moulh.pg.busi.mapper;

import com.moulh.pg.busi.entity.ObjectImages;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.moulh.pg.busi.vo.ObjectImagesVo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author moulh
 * @since 2021-07-11
 */
public interface ObjectImagesMapper extends BaseMapper<ObjectImages> {
    List<ObjectImagesVo> queryImagesMsgByObjectId(Integer objectId);
}
