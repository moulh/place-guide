package com.moulh.pg.busi.service.impl;

import com.moulh.pg.busi.mapper.ObjectFlowMapper;
import com.moulh.pg.busi.service.DashBoardService;
import com.moulh.pg.busi.vo.ObjectFlowDateMsgVo;
import com.moulh.pg.busi.vo.ObjectFlowVo;
import com.moulh.pg.core.dto.Result;
import com.moulh.pg.core.util.DateUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName : DashBoardServiceImpl
 * @Author : moulh
 * @Date : 2021-08-06 13:53
 * @Version : V1.0
 * @Description :
 */
@Service
@Slf4j
@AllArgsConstructor
public class DashBoardServiceImpl implements DashBoardService {
    private ObjectFlowMapper objectFlowMapper;
    @Override
    public List<ObjectFlowVo> queryObjectFlowMsg(Map<String, Object> searchMap) {
        String dateTimeStart = searchMap.get("dateTimeStart").toString();
        String dateTimeEnd = searchMap.get("dateTimeEnd").toString();
        List<ObjectFlowVo> objectFlowVoList = objectFlowMapper.queryObjectFlowTotalMsg(searchMap);
        if(StringUtils.equals(dateTimeStart, dateTimeEnd)){
            return objectFlowVoList;
        }
        objectFlowVoList.forEach(objectFlowVo -> {
            searchMap.put("objectId", objectFlowVo.getObjectId());
            List<ObjectFlowDateMsgVo> objectFlowDateMsgVoList = objectFlowMapper.queryObjectFlowMsg(searchMap);
            objectFlowDateMsgVoList = this.formatObjectFlowMsg(objectFlowDateMsgVoList, dateTimeStart, dateTimeEnd);
            objectFlowVo.setObjectFlowDateMsgVoList(objectFlowDateMsgVoList);
        });
        return objectFlowVoList;
    }

    private List<ObjectFlowDateMsgVo> formatObjectFlowMsg(List<ObjectFlowDateMsgVo> objectFlowDateMsgVoList,
                                                          String dateTimeStart,
                                                          String dateTimeEnd){
        List<String> dateList = new ArrayList<>();
        List<ObjectFlowDateMsgVo> result = new ArrayList<>();
        try {
            dateList = DateUtils.findDates(dateTimeStart, dateTimeEnd);
        } catch (ParseException e) {
            log.info("时间格式化错误");
            e.printStackTrace();
        }
        //循环日期 与 统计结果比较 没有该日期的 补足该日期
        for1:
        for (String date : dateList){
            boolean insertFlag = true;
            for2:
            for (ObjectFlowDateMsgVo objectFlowDateMsgVo : objectFlowDateMsgVoList){
                if(date.equals(objectFlowDateMsgVo.getDate())){
                    insertFlag = false;
                    result.add(objectFlowDateMsgVo);
                    break for2;
                }
            }
            if(insertFlag){
                ObjectFlowDateMsgVo objectFlowDateMsgVo = new ObjectFlowDateMsgVo();
                objectFlowDateMsgVo.setDate(date);
                try {
                    objectFlowDateMsgVo.setWeekNum(DateUtils.getWeekOfDate(date));
                } catch (ParseException e) {
                    log.info("周格式化错误");
                    e.printStackTrace();
                }
                objectFlowDateMsgVo.setVisitNum(0);
                result.add(objectFlowDateMsgVo);
            }
        }
        return result;
    }
}
