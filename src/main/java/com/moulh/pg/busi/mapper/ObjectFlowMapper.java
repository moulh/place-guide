package com.moulh.pg.busi.mapper;

import com.moulh.pg.busi.entity.ObjectFlow;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.moulh.pg.busi.vo.ObjectFlowDateMsgVo;
import com.moulh.pg.busi.vo.ObjectFlowVo;
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
public interface ObjectFlowMapper extends BaseMapper<ObjectFlow> {
    List<ObjectFlowVo> queryObjectFlowTotalMsg(@Param("searchMap") Map<String, Object> searchMap);

    List<ObjectFlowDateMsgVo> queryObjectFlowMsg(@Param("searchMap") Map<String, Object> searchMap);

    int updateObjectFlow(@Param("objectId") Integer objectId,
                          @Param("date") String date);
}
