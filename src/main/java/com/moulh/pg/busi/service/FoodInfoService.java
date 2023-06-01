package com.moulh.pg.busi.service;

import com.moulh.pg.busi.entity.FoodInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.moulh.pg.busi.vo.FoodInfoListVo;
import com.moulh.pg.busi.vo.FoodInfoVo;
import com.moulh.pg.busi.vo.SpotObjectListVo;
import com.moulh.pg.core.dto.PageInfo;
import com.moulh.pg.core.dto.PageResultDTO;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author moulh
 * @since 2021-07-24
 */
public interface FoodInfoService extends IService<FoodInfo> {
    PageResultDTO<FoodInfoListVo> queryFoodInfoList(PageInfo pageInfo, Map<String, Object> searchMap);

    PageResultDTO<FoodInfoVo> queryFoodInfoWithImagesList(PageInfo pageInfo, Map<String, Object> searchMap);
}
