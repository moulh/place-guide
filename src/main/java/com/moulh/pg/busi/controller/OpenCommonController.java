package com.moulh.pg.busi.controller;

import com.moulh.pg.busi.entity.ObjectFlow;
import com.moulh.pg.busi.entity.SaveImageResult;
import com.moulh.pg.busi.input.CommentInput;
import com.moulh.pg.busi.input.TagsInput;
import com.moulh.pg.busi.mapper.ObjectFlowMapper;
import com.moulh.pg.busi.service.CommonService;
import com.moulh.pg.busi.service.OpenCommonService;
import com.moulh.pg.busi.vo.AreaVo;
import com.moulh.pg.busi.vo.CommentVo;
import com.moulh.pg.busi.vo.TagsVo;
import com.moulh.pg.core.dto.PageInfo;
import com.moulh.pg.core.dto.PageResultDTO;
import com.moulh.pg.core.dto.Result;
import com.moulh.pg.core.util.PgValidateUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName : OpenCommonController
 * @Author : moulh@paxsz.com
 * @Date : 2021-07-12 11:45
 * @Version : V1.0
 * @Description :
 */
@RestController
@RequestMapping("/open/common")
@Api(tags = "小程序公共接口")
@AllArgsConstructor
public class OpenCommonController {
    private OpenCommonService openCommonService;
    private CommonService commonService;
    private ObjectFlowMapper objectFlowMapper;

    @ApiOperation("新增标签")
    @PostMapping("/tags/save")
    public Result<String> saveTags(@Validated @RequestBody TagsInput tagsInput,
                                              BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            throw new IllegalArgumentException(PgValidateUtils.getErrorMessages(bindingResult));
        }
        openCommonService.saveTags(tagsInput);
        return Result.ok();
    }

    @ApiOperation("新增评论")
    @PostMapping("/comment/save")
    public Result<String> saveComment(@Validated @RequestBody CommentInput commentInput,
                                      BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            throw new IllegalArgumentException(PgValidateUtils.getErrorMessages(bindingResult));
        }
        openCommonService.saveComment(commentInput);
        return Result.ok();
    }

    @ApiOperation("新增图片信息")
    @PostMapping("/images/save")
    public Result<SaveImageResult> saveImages(HttpServletRequest request){
        return Result.ok(commonService.saveImages(request));
    }

    @ApiOperation("根据Type查询标签信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "objectType", dataType = "integer", value = "1-景点，2-住宿，3-餐饮(列表筛选条件时传该值)", paramType = "query"),
            @ApiImplicitParam(name = "objectId", dataType = "integer", value = "景点/住宿/餐饮对应的id(详情里面查询评论的标签时传该值)", paramType = "query"),
            @ApiImplicitParam(name = "quantity", dataType = "integer", value = "标签数量", paramType = "query"),
    })
    @GetMapping("/tags/query/criteria")
    public Result<List<TagsVo>> queryTags(@RequestParam(value = "objectType", required = false) Integer objectType,
                                          @RequestParam(value = "objectId", required = false) Integer objectId,
                                          @RequestParam(value = "quantity") Integer quantity) {
        return Result.ok(openCommonService.queryTagsList(objectType, objectId, quantity));
    }

    @ApiOperation("查询基础城市信息")
    @GetMapping("/query/area")
    public Result<List<AreaVo>> queryAreaMsg(){
        return Result.ok(openCommonService.queryAreaList());
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "objectId", dataType = "integer", value = "景点/住宿/餐饮objectId", paramType = "query"),
            @ApiImplicitParam(name = "tagId", dataType = "Integer", value = "标签id", paramType = "query"),
    })
    @ApiOperation("分页查询评论列表")
    @GetMapping("/comment/list")
    public Result<PageResultDTO<CommentVo>> commentList(PageInfo pageInfo,
                                                        @RequestParam(value = "objectId") Integer objectId,
                                                        @RequestParam(value = "tagId", required = false) Integer tagId){
        Map<String, Object> searchMap = new HashMap<>(6);
        searchMap.put("objectId", objectId);
        searchMap.put("tagId", tagId);

        return Result.ok(openCommonService.querCommentList(pageInfo, searchMap));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "date", dataType = "string", value = "浏览时间, 格式: yyyyMMdd, 例: 20210725", paramType = "query"),
            @ApiImplicitParam(name = "objectId", dataType = "integer", value = "景点/住宿/餐饮对应的objectId", paramType = "query"),
    })
    @ApiOperation("浏览次数记录")
    @PatchMapping("/add/objectflow")
    public Result<String> addObjectFlow(@RequestParam(value = "objectId") Integer objectId,
                                        @RequestParam(value = "date") String date){
        int result = objectFlowMapper.updateObjectFlow(objectId, date);
        if(result == 0){
            ObjectFlow objectFlow = new ObjectFlow();
            objectFlow.setObjectId(objectId);
            objectFlow.setDate(date);
            objectFlow.setVisitNum(1);
            objectFlowMapper.insert(objectFlow);
        }
        return Result.ok();
    }
}
