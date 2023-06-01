package com.moulh.pg.busi.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.moulh.pg.busi.entity.FoodInfo;
import com.moulh.pg.busi.mapper.FoodInfoMapper;
import com.moulh.pg.busi.service.FoodInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moulh.pg.busi.vo.FoodInfoListVo;
import com.moulh.pg.busi.vo.FoodInfoVo;
import com.moulh.pg.busi.vo.SpotObjectListVo;
import com.moulh.pg.core.dto.PageInfo;
import com.moulh.pg.core.dto.PageResultDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author moulh
 * @since 2021-07-24
 */
@Service
@AllArgsConstructor
public class FoodInfoServiceImpl extends ServiceImpl<FoodInfoMapper, FoodInfo> implements FoodInfoService {

    private FoodInfoMapper foodInfoMapper;

    /**
     * 不带图片信息的推荐菜列表
     * @param pageInfo
     * @param searchMap
     * @return
     */
    @Override
    public PageResultDTO<FoodInfoListVo> queryFoodInfoList(PageInfo pageInfo, Map<String, Object> searchMap) {
        Page<FoodInfoListVo> queryPage = new Page<>(pageInfo.getCurrent(), pageInfo.getSize());
        IPage<FoodInfoListVo> iPage = foodInfoMapper.queryFoodInfoList(queryPage, searchMap);
        return PageResultDTO.of(iPage, FoodInfoListVo.class);
    }

    /**
     * 带有图片信息的推荐菜列表
     * @param pageInfo
     * @param searchMap
     * @return
     */
    @Override
    public PageResultDTO<FoodInfoVo> queryFoodInfoWithImagesList(PageInfo pageInfo, Map<String, Object> searchMap) {
        Page<FoodInfoVo> queryPage = new Page<>(pageInfo.getCurrent(), pageInfo.getSize());
        IPage<FoodInfoVo> iPage = foodInfoMapper.queryFoodInfoAndImagesList(queryPage, searchMap);
        return PageResultDTO.of(iPage, FoodInfoVo.class);
    }
}
