package com.moulh.pg.busi.controller;

import com.moulh.pg.busi.service.DashBoardService;
import com.moulh.pg.busi.vo.ObjectFlowVo;
import com.moulh.pg.core.dto.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName : DashBoardController
 * @Author : moulh
 * @Date : 2021-07-26 17:07
 * @Version : V1.0
 * @Description : web管理端DashBoard接口
 */
@RestController
@RequestMapping("/dashBoard")
@Api(tags = "web管理端DashBoard接口")
@AllArgsConstructor
public class DashBoardController {
    private DashBoardService dashBoardService;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "dateTimeStart", dataType = "string", value = "开始时间,日:20200701(start)20200701(end), 月: 20200701(start)20200731(end) 年: 20200101(start)20201231(end),必传", paramType = "query"),
            @ApiImplicitParam(name = "dateTimeEnd", dataType = "string", value = "结束时间,必传", paramType = "query"),
            @ApiImplicitParam(name = "objectType", dataType = "String", value = "1-景点，2-住宿，3-餐饮", paramType = "query"),
            @ApiImplicitParam(name = "objectName", dataType = "String", value = "景点/住宿/餐饮名称(筛选条件)", paramType = "query"),
    })
    @ApiOperation("DashBoard景点/住宿/餐饮浏览次数展示信息")
    @GetMapping("/objectFlow/list")
    public Result<List<ObjectFlowVo>> objectFlowList(@RequestParam(value = "dateTimeStart") String dateTimeStart,
                                                     @RequestParam(value = "dateTimeEnd") String dateTimeEnd,
                                                     @RequestParam(value = "objectType") String objectType,
                                                     @RequestParam(value = "objectName", required = false) String objectName){
        Map<String, Object> searchMap = new HashMap<>(6);
        searchMap.put("objectType", objectType);
        searchMap.put("objectName", objectName);
        searchMap.put("dateTimeStart", dateTimeStart);
        searchMap.put("dateTimeEnd", dateTimeEnd);
        return Result.ok(dashBoardService.queryObjectFlowMsg(searchMap));
    }
}
