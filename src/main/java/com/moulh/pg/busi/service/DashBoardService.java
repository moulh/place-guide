package com.moulh.pg.busi.service;

import com.moulh.pg.busi.vo.ObjectFlowVo;

import java.util.List;
import java.util.Map;

/**
 * @ClassName : DashBoardService
 * @Author : moulh
 * @Date : 2021-08-06 13:52
 * @Version : V1.0
 * @Description :
 */
public interface DashBoardService {
    List<ObjectFlowVo> queryObjectFlowMsg(Map<String, Object> searchMap);
}
