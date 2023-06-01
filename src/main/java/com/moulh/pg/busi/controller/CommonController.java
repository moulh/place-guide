package com.moulh.pg.busi.controller;

import com.moulh.pg.busi.entity.SaveImageResult;
import com.moulh.pg.busi.service.CommonService;
import com.moulh.pg.busi.service.OpenCommonService;
import com.moulh.pg.busi.vo.AreaVo;
import com.moulh.pg.core.dto.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ClassName : OpenCommonController
 * @Author : moulh@paxsz.com
 * @Date : 2021-07-12 11:45
 * @Version : V1.0
 * @Description : web管理端公共接口
 */
@RestController
@RequestMapping("/common")
@Api(tags = "web管理端公共接口")
@AllArgsConstructor
public class CommonController {
    private CommonService commonService;
    private OpenCommonService openCommonService;

    @ApiOperation("新增图片信息")
    @PostMapping("/images/save")
    public Result<SaveImageResult> saveImages(HttpServletRequest request){
        return Result.ok(commonService.saveImages(request));
    }

    @ApiOperation("查询基础城市信息")
    @GetMapping("/query/area")
    public Result<List<AreaVo>> queryAreaMsg(){
        return Result.ok(openCommonService.queryAreaList());
    }
}
