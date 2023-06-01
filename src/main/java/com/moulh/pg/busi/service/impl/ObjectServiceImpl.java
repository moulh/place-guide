package com.moulh.pg.busi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.moulh.pg.busi.entity.Images;
import com.moulh.pg.busi.entity.Object;
import com.moulh.pg.busi.entity.ObjectImages;
import com.moulh.pg.busi.input.ObjectImagesInput;
import com.moulh.pg.busi.input.ObjectInput;
import com.moulh.pg.busi.input.ObjectUpdateInput;
import com.moulh.pg.busi.mapper.CommentMapper;
import com.moulh.pg.busi.mapper.ImagesMapper;
import com.moulh.pg.busi.mapper.ObjectImagesMapper;
import com.moulh.pg.busi.mapper.ObjectMapper;
import com.moulh.pg.busi.service.ObjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moulh.pg.busi.vo.*;
import com.moulh.pg.core.dto.PageInfo;
import com.moulh.pg.core.dto.PageResultDTO;
import com.moulh.pg.core.util.ObjectConverter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author moulh
 * @since 2021-07-11
 */
@Service
@AllArgsConstructor
public class ObjectServiceImpl extends ServiceImpl<ObjectMapper, Object> implements ObjectService {

    private ObjectMapper objectMapper;
    private ObjectImagesMapper objectImagesMapper;
    private CommentMapper commentMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveObject(ObjectInput objectInput) {
        Object object = ObjectConverter.convert(objectInput, Object.class);
        objectMapper.insert(object);
        if(object.getObjectMainImageid() != null){
            //新增主图片信息
            ObjectImages mainObjectImages = new ObjectImages();
            mainObjectImages.setObjectId(object.getId());
            mainObjectImages.setImagesId(objectInput.getObjectMainImageid());
            objectImagesMapper.insert(mainObjectImages);
        }
        if(objectInput.getObjectImagesInputs() != null && objectInput.getObjectImagesInputs().size() > 0){
            //新增其他图片信息
            objectInput.getObjectImagesInputs().forEach(objectImagesInput -> {
                ObjectImages objectImages = ObjectConverter.convert(objectImagesInput, ObjectImages.class);
                objectImages.setObjectId(object.getId());
                objectImages.setImagesType(2);
                objectImagesMapper.insert(objectImages);
            });
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateObject(ObjectUpdateInput objectUpdateInput) {
        Object oldObject = objectMapper.selectById(objectUpdateInput.getId());
        Object object = ObjectConverter.convert(objectUpdateInput, Object.class);
        objectMapper.updateById(object);
        if(!Objects.equals(oldObject.getObjectMainImageid(), object.getId())){
            //删除object对应的主体图片信息
            QueryWrapper<ObjectImages> objectImagesQueryWrapper = new QueryWrapper<ObjectImages>()
                    .eq("image_id", oldObject.getObjectMainImageid())
                    .eq("object_id", object.getId());
            objectImagesMapper.delete(objectImagesQueryWrapper);
        }
        //更新图片信息
        //获取旧的图片信息
        List<ObjectImagesVo> oldObjectImagesVoList = objectImagesMapper.queryImagesMsgByObjectId(object.getId());

        List<ObjectImagesInput> newObjectImagesInputList = objectUpdateInput.getObjectImagesInputs();
        //循环旧的图片信息, 如果跟新的集合里面有相同的, 则跳过, 如果没有, 则需要删除
        for (ObjectImagesVo oldObjectImagesVo : oldObjectImagesVoList){
            ObjectImagesInput oldObjectImagesInput = ObjectConverter.convert(oldObjectImagesVo, ObjectImagesInput.class);
            boolean deleteFlag = true;
            for2:
            for (ObjectImagesInput newObjectImagesInput : newObjectImagesInputList){
                if(oldObjectImagesInput.equals(newObjectImagesInput)){
                    deleteFlag = false;
                    break for2;
                }
            }
            if(deleteFlag){
                //删除该照片
                QueryWrapper<ObjectImages> objectImagesQueryWrapper = new QueryWrapper<ObjectImages>()
                        .eq("image_id", oldObjectImagesInput.getImagesId())
                        .eq("object_id", object.getId());
                objectImagesMapper.delete(objectImagesQueryWrapper);
            }
        }
        //循环新的图片信息, 如果跟旧的集合里面有相同的, 则跳过, 如果没有, 则需要新增
        for (ObjectImagesInput newObjectImagesInput : newObjectImagesInputList){
            boolean insertFlag = true;
            for2:
            for (ObjectImagesVo oldObjectImagesVo : oldObjectImagesVoList){
                ObjectImagesInput oldObjectImagesInput = ObjectConverter.convert(oldObjectImagesVo, ObjectImagesInput.class);
                if(newObjectImagesInput.equals(oldObjectImagesInput)){
                    insertFlag = false;
                    break for2;
                }
            }
            if(insertFlag){
                ObjectImages objectImages = ObjectConverter.convert(newObjectImagesInput, ObjectImages.class);
                objectImages.setObjectId(object.getId());
                objectImages.setImagesType(2);
                objectImagesMapper.insert(objectImages);
            }
        }
    }

    @Override
    public PageResultDTO<ObjectListVo> queryObjectList(PageInfo pageInfo, Map<String, java.lang.Object> searchMap) {
        Page<ObjectListVo> queryPage = new Page<>(pageInfo.getCurrent(), pageInfo.getSize());
        IPage<ObjectListVo> iPage = objectMapper.queryObjectList(queryPage, searchMap);
        return PageResultDTO.of(iPage, ObjectListVo.class);
    }

    @Override
    public ObjectVo queryObjectDetail(Integer id) {
        ObjectVo objectVo = objectMapper.queryObjectDetail(id);
        List<ObjectImagesVo> objectImagesVoList = objectImagesMapper.queryImagesMsgByObjectId(id);
        objectVo.setObjectImagesVoList(objectImagesVoList);
        return objectVo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteObject(List<Integer> ids) {
        ids.forEach(id -> {
            objectMapper.deleteById(id);
        });
    }

    /**
     * 查询景点列表
     * @param pageInfo
     * @param searchMap
     * @return
     */
    @Override
    public PageResultDTO<SpotObjectListVo> querySpotList(PageInfo pageInfo, Map<String, java.lang.Object> searchMap) {
        Page<SpotObjectListVo> queryPage = new Page<>(pageInfo.getCurrent(), pageInfo.getSize());
        IPage<SpotObjectListVo> iPage = objectMapper.querySpotList(queryPage, searchMap);
        return PageResultDTO.of(iPage, SpotObjectListVo.class);
    }

    /**
     * 查询住宿列表
     * @param pageInfo
     * @param searchMap
     * @return
     */
    @Override
    public PageResultDTO<AccObjectListVo> queryAccList(PageInfo pageInfo, Map<String, java.lang.Object> searchMap) {
        Page<AccObjectListVo> queryPage = new Page<>(pageInfo.getCurrent(), pageInfo.getSize());
        IPage<AccObjectListVo> iPage = objectMapper.queryAccList(queryPage, searchMap);
        return PageResultDTO.of(iPage, AccObjectListVo.class);
    }

    /**
     * 查询餐饮列表
     * @param pageInfo
     * @param searchMap
     * @return
     */
    @Override
    public PageResultDTO<RestObjectListVo> queryRestList(PageInfo pageInfo, Map<String, java.lang.Object> searchMap) {
        Page<RestObjectListVo> queryPage = new Page<>(pageInfo.getCurrent(), pageInfo.getSize());
        IPage<RestObjectListVo> iPage = objectMapper.queryRestList(queryPage, searchMap);
        return PageResultDTO.of(iPage, RestObjectListVo.class);
    }

    @Override
    public SpotObjectDetailVo querySpotDetail(Integer objectId) {
        SpotObjectDetailVo spotObjectDetailVo = objectMapper.querySpotDetail(objectId);
        List<ObjectImagesVo> objectImagesVoList = objectImagesMapper.queryImagesMsgByObjectId(objectId);
        //星级
        String star = commentMapper.queryAvgScore(objectId);
        //好评率
        String perOfGood = commentMapper.queryPerOfGood(objectId);
        spotObjectDetailVo.setPerOfGood(new BigDecimal(perOfGood));
        spotObjectDetailVo.setObjectImagesVoList(objectImagesVoList);
        spotObjectDetailVo.setStarMsg(new BigDecimal(star));
        return spotObjectDetailVo;
    }

    @Override
    public AccObjectDetailVo queryAccDetail(Integer objectId) {
        AccObjectDetailVo accObjectDetailVo = objectMapper.queryAccDetail(objectId);
        List<ObjectImagesVo> objectImagesVoList = objectImagesMapper.queryImagesMsgByObjectId(objectId);
        String star = commentMapper.queryAvgScore(objectId);
        //好评率
        String perOfGood = commentMapper.queryPerOfGood(objectId);
        accObjectDetailVo.setPerOfGood(new BigDecimal(perOfGood));
        accObjectDetailVo.setObjectImagesVoList(objectImagesVoList);
        accObjectDetailVo.setStarMsg(new BigDecimal(star));
        return accObjectDetailVo;
    }

    @Override
    public RestObjectDetailVo queryRestDetail(Integer objectId) {
        RestObjectDetailVo restObjectDetailVo = objectMapper.queryRestDetail(objectId);
        List<ObjectImagesVo> objectImagesVoList = objectImagesMapper.queryImagesMsgByObjectId(objectId);
        String star = commentMapper.queryAvgScore(objectId);
        //好评率
        String perOfGood = commentMapper.queryPerOfGood(objectId);
        restObjectDetailVo.setPerOfGood(new BigDecimal(perOfGood));
        restObjectDetailVo.setObjectImagesVoList(objectImagesVoList);
        restObjectDetailVo.setStarMsg(new BigDecimal(star));
        return restObjectDetailVo;
    }
}
