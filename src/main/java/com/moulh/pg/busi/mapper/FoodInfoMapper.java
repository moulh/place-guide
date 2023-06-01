package com.moulh.pg.busi.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.moulh.pg.busi.entity.FoodInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.moulh.pg.busi.vo.FoodInfoListVo;
import com.moulh.pg.busi.vo.FoodInfoVo;
import com.moulh.pg.busi.vo.SpotObjectListVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author moulh
 * @since 2021-07-24
 */
public interface FoodInfoMapper extends BaseMapper<FoodInfo> {
    FoodInfoVo foodDetail(Integer foodId);

    List<FoodInfoListVo> queryList(Integer objectId);

    IPage<FoodInfoListVo> queryFoodInfoList(Page<FoodInfoListVo> page, @Param("searchMap") Map<String, Object> searchMap);

    IPage<FoodInfoVo> queryFoodInfoAndImagesList(Page<FoodInfoVo> page, @Param("searchMap") Map<String, Object> searchMap);

    Integer queryMaxOrderNo(Integer objectId);
}
