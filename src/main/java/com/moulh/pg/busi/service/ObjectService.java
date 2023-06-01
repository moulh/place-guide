package com.moulh.pg.busi.service;

import com.moulh.pg.busi.entity.Object;
import com.baomidou.mybatisplus.extension.service.IService;
import com.moulh.pg.busi.input.ObjectInput;
import com.moulh.pg.busi.input.ObjectUpdateInput;
import com.moulh.pg.busi.vo.*;
import com.moulh.pg.core.dto.PageInfo;
import com.moulh.pg.core.dto.PageResultDTO;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author moulh
 * @since 2021-07-11
 */
public interface ObjectService extends IService<Object> {
    void saveObject(ObjectInput objectInput);

    void updateObject(ObjectUpdateInput objectUpdateInput);

    PageResultDTO<ObjectListVo> queryObjectList(PageInfo pageInfo, Map<String, java.lang.Object> searchMap);

    ObjectVo queryObjectDetail(Integer id);

    void deleteObject(List<Integer> ids);

    PageResultDTO<SpotObjectListVo> querySpotList(PageInfo pageInfo, Map<String, java.lang.Object> searchMap);

    PageResultDTO<AccObjectListVo> queryAccList(PageInfo pageInfo, Map<String, java.lang.Object> searchMap);

    PageResultDTO<RestObjectListVo> queryRestList(PageInfo pageInfo, Map<String, java.lang.Object> searchMap);

    SpotObjectDetailVo querySpotDetail(Integer objectId);

    AccObjectDetailVo queryAccDetail(Integer objectId);

    RestObjectDetailVo queryRestDetail(Integer objectId);
}
