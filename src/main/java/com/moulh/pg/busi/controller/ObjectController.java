package com.moulh.pg.busi.controller;

import com.moulh.pg.busi.entity.FoodInfo;
import com.moulh.pg.busi.input.*;
import com.moulh.pg.busi.mapper.FoodInfoMapper;
import com.moulh.pg.busi.service.FoodInfoService;
import com.moulh.pg.busi.service.ObjectService;
import com.moulh.pg.busi.vo.FoodInfoListVo;
import com.moulh.pg.busi.vo.FoodInfoVo;
import com.moulh.pg.busi.vo.ObjectListVo;
import com.moulh.pg.busi.vo.ObjectVo;
import com.moulh.pg.core.dto.PageInfo;
import com.moulh.pg.core.dto.PageResultDTO;
import com.moulh.pg.core.dto.Result;
import com.moulh.pg.core.util.ObjectConverter;
import com.moulh.pg.core.util.PgValidateUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName : ObjectController
 * @Author : moulh
 * @Date : 2021-07-13 16:32
 * @Version : V1.0
 * @Description : 景点/住宿/餐饮信息相关接口
 */
@RestController
@RequestMapping("/object")
@Api(tags = "web管理端景点-住宿-餐饮信息相关接口")
@AllArgsConstructor
public class ObjectController {
    private ObjectService objectService;
    private FoodInfoService foodInfoService;
    private FoodInfoMapper foodInfoMapper;
    @ApiOperation("新增景点-住宿-餐饮")
    @PostMapping("/save")
    public Result<String> saveObject(@Validated @RequestBody ObjectInput objectInput,
                                      BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            throw new IllegalArgumentException(PgValidateUtils.getErrorMessages(bindingResult));
        }
        objectService.saveObject(objectInput);
        return Result.ok();
    }

    @ApiOperation("修改景点-住宿-餐饮")
    @PatchMapping("/update")
    public Result<String> updteObject(@Validated @RequestBody ObjectUpdateInput objectUpdateInput,
                                     BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            throw new IllegalArgumentException(PgValidateUtils.getErrorMessages(bindingResult));
        }
        objectService.updateObject(objectUpdateInput);
        return Result.ok();
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "objectType", dataType = "string", value = "1-景点，2-住宿，3-餐饮", paramType = "query"),
            @ApiImplicitParam(name = "objectName", dataType = "string", value = "景点/住宿/餐饮名称", paramType = "query"),
    })
    @ApiOperation("分页查询景点-住宿-餐饮列表")
    @GetMapping("/list")
    public Result<PageResultDTO<ObjectListVo>> objectList(PageInfo pageInfo,
                                                         @RequestParam(value = "objectType", required = false) String objectType,
                                                         @RequestParam(value = "objectName", required = false) String objectName){
        Map<String, Object> searchMap = new HashMap<>(6);
        searchMap.put("objectType", objectType);
        searchMap.put("objectName", objectName);

        return Result.ok(objectService.queryObjectList(pageInfo, searchMap));
    }

    @ApiOperation("景点-住宿-餐饮信息详情")
    @ApiImplicitParam(name = "id", dataType = "integer", value = "逻辑主键id", required = true)
    @GetMapping("/detail/{id}")
    public Result<ObjectVo> objectDetail(@PathVariable(value = "id") Integer id){
        return Result.ok(objectService.queryObjectDetail(id));
    }

    @ApiOperation("删除景点-住宿-餐饮信息")
    @DeleteMapping
    public Result<String> objectDelete(@RequestBody List<Integer> ids){
        objectService.deleteObject(ids);
        return Result.ok();
    }

    @ApiOperation("新增推荐菜")
    @PostMapping("/foodinfo/save")
    public Result<String> saveFoodInfo(@Validated @RequestBody FoodInfoInput foodInfoInput,
                                     BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            throw new IllegalArgumentException(PgValidateUtils.getErrorMessages(bindingResult));
        }
        FoodInfo foodInfo = ObjectConverter.convert(foodInfoInput, FoodInfo.class);
        Integer maxOrderNo = foodInfoMapper.queryMaxOrderNo(foodInfo.getObjectId());
        foodInfo.setOrderNo(maxOrderNo + 1);
        foodInfoService.save(foodInfo);
        return Result.ok();
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "objectId", dataType = "string", value = "餐饮objectId", paramType = "query"),
            @ApiImplicitParam(name = "foodName", dataType = "string", value = "推荐菜品名称", paramType = "query"),
    })
    @ApiOperation("分页查询推荐菜列表")
    @GetMapping("/foodinfo/list")
    public Result<PageResultDTO<FoodInfoListVo>> foodInfoList(PageInfo pageInfo,
                                                              @RequestParam(value = "objectId") String objectId,
                                                              @RequestParam(value = "foodName", required = false) String foodName){
        Map<String, Object> searchMap = new HashMap<>(6);
        searchMap.put("objectId", objectId);
        searchMap.put("foodName", foodName);

        return Result.ok(foodInfoService.queryFoodInfoList(pageInfo, searchMap));
    }

    /**
     * 菜品信息详情
     * @param foodId 逻辑主键
     * @return
     */
    @ApiOperation("推荐菜品信息详情")
    @ApiImplicitParam(name = "id", dataType = "integer", value = "逻辑主键id", required = true)
    @GetMapping("/foodinfo/detail/{foodId}")
    public Result<FoodInfoVo> foodDetail(@PathVariable(value = "foodId") Integer foodId){
        return Result.ok(foodInfoMapper.foodDetail(foodId));
    }

    @ApiOperation("修改推荐菜")
    @PatchMapping("/foodinfo/update")
    public Result<String> updteFoodInfo(@Validated @RequestBody FoodInfoUpdateInput foodInfoUpdateInput,
                                      BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            throw new IllegalArgumentException(PgValidateUtils.getErrorMessages(bindingResult));
        }
        FoodInfo foodInfo = ObjectConverter.convert(foodInfoUpdateInput, FoodInfo.class);
        foodInfoService.updateById(foodInfo);
        return Result.ok();
    }

    @ApiOperation("删除推荐菜")
    @DeleteMapping("/foodinfo")
    public Result<String> foodInfoDelete(@RequestBody List<Integer> foodIds){
        foodIds.forEach(foodId -> {
            foodInfoService.removeById(foodId);
        });
        return Result.ok();
    }

    @ApiOperation("推荐菜排序列表数据")
    @ApiImplicitParam(name = "objectId", dataType = "integer", value = "所选餐饮门店的id", required = true)
    @GetMapping("/foodinfo/sort/list/{objectId}")
    public Result<List<FoodInfoListVo>> queryFoodListForOrderNo(@PathVariable(value = "objectId") Integer objectId){
        return Result.ok(foodInfoMapper.queryList(objectId));
    }
    /**
     * 菜品排序
     * @param foodOrderMsgInputs
     * @return
     */
    @ApiOperation("修改推荐菜品排序")
    @PatchMapping("/update/food/orderNo")
    public Result<String> updateFoodOrderNo(@RequestBody List<FoodOrderNoInput> foodOrderMsgInputs){
        foodOrderMsgInputs.forEach(foodOrderNoInput -> {
            FoodInfo foodInfo = ObjectConverter.convert(foodOrderNoInput, FoodInfo.class);
            foodInfoService.updateById(foodInfo);
        });
        return Result.ok();
    }

}
