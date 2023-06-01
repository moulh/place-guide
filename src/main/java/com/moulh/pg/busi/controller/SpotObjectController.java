package com.moulh.pg.busi.controller;


import com.moulh.pg.busi.constant.FlagConstant;
import com.moulh.pg.busi.service.ObjectService;
import com.moulh.pg.busi.vo.SpotObjectDetailVo;
import com.moulh.pg.busi.vo.SpotObjectListVo;
import com.moulh.pg.core.dto.PageInfo;
import com.moulh.pg.core.dto.PageResultDTO;
import com.moulh.pg.core.dto.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author moulh
 * @since 2021-07-11
 */
@RestController
@RequestMapping("/open/spot")
@Api(tags = "小程序景点展示信息相关接口")
@AllArgsConstructor
public class SpotObjectController {
    private ObjectService objectService;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "lat", dataType = "string", value = "经度", paramType = "query"),
            @ApiImplicitParam(name = "lng", dataType = "string", value = "纬度", paramType = "query"),
            @ApiImplicitParam(name = "distance", dataType = "string", value = "距离", paramType = "query"),
            @ApiImplicitParam(name = "flag", dataType = "string", value = "1:好评优先,2:游客最多,3:热度最高,4:离我最近", paramType = "query"),
            @ApiImplicitParam(name = "areaCode", dataType = "string", value = "选择的城市code", paramType = "query"),
            @ApiImplicitParam(name = "tagIds", dataType = "string", value = "标签id集合,用逗号分隔", paramType = "query"),
    })
    @ApiOperation("分页查询景点列表")
    @GetMapping("/list")
    public Result<PageResultDTO<SpotObjectListVo>> spotList(PageInfo pageInfo,
                                                             @RequestParam(value = "lat", required = false) String lat,
                                                             @RequestParam(value = "lng", required = false) String lng,
                                                             @RequestParam(value = "distance", required = false) String distance,
                                                             @RequestParam(value = "flag", required = false) String flag,
                                                             @RequestParam(value = "areaCode", required = false) String areaCode,
                                                             @RequestParam(value = "tagIds", required = false) String tagIds){
        Map<String, Object> searchMap = new HashMap<>(10);
        searchMap.put("objectType", FlagConstant.BaseObjectType.TYPE_SPOT);
        searchMap.put("lat", lat);
        searchMap.put("lng", lng);
        searchMap.put("distance", distance);
        searchMap.put("flag", flag);
        searchMap.put("areaCode", areaCode);
        List<String> tagList = new ArrayList<>();
        if(StringUtils.isNotBlank(tagIds)){
            String[] tags = tagIds.split(",");
            tagList = Stream.of(tags).collect(Collectors.toList());
        }
        searchMap.put("tagIds", tagList);

        return Result.ok(objectService.querySpotList(pageInfo, searchMap));
    }

    @ApiOperation("景点信息详情")
    @ApiImplicitParam(name = "id", dataType = "integer", value = "逻辑主键id", required = true)
    @GetMapping("/detail/{id}")
    public Result<SpotObjectDetailVo> objectDetail(@PathVariable(value = "id") Integer id){
        return Result.ok(objectService.querySpotDetail(id));
    }
}
