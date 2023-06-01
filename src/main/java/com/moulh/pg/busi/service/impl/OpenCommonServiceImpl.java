package com.moulh.pg.busi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.moulh.pg.busi.entity.*;
import com.moulh.pg.busi.input.CommentInput;
import com.moulh.pg.busi.input.TagsInput;
import com.moulh.pg.busi.mapper.*;
import com.moulh.pg.busi.service.OpenCommonService;
import com.moulh.pg.busi.vo.AreaVo;
import com.moulh.pg.busi.vo.CommentVo;
import com.moulh.pg.busi.vo.TagsVo;
import com.moulh.pg.core.dto.PageInfo;
import com.moulh.pg.core.dto.PageResultDTO;
import com.moulh.pg.core.util.ObjectConverter;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.Object;
import java.util.List;
import java.util.Map;

/**
 * @ClassName : OpenCommonServiceImpl
 * @Author : moulh@paxsz.com
 * @Date : 2021-07-12 18:31
 * @Version : V1.0
 * @Description : 对外公关接口实现类
 */
@Service
@AllArgsConstructor
public class OpenCommonServiceImpl implements OpenCommonService {
    private TagsMapper tagsMapper;
    private CommentMapper commentMapper;
    private ImagesMapper imagesMapper;
    private VisitorMapper visitorMapper;
    private AreaMapper areaMapper;

    /**
     * 保存标签
     * @param tagsInput
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveTags(TagsInput tagsInput) {
        Tags tags = ObjectConverter.convert(tagsInput, Tags.class);
        tagsMapper.insert(tags);
    }

    /**
     * 保存评论
     * @param commentInput
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveComment(CommentInput commentInput) {
        //先保存游客头像信息
        Images images = new Images();
        images.setImageName(commentInput.getVisitorName());
        images.setUrl(commentInput.getVisitorImageUrl());
        imagesMapper.insert(images);
        //保存游客信息
        Visitor visitor = new Visitor();
        visitor.setVisitorName(commentInput.getVisitorName());
        visitor.setVisitorImageid(images.getId());
        visitorMapper.insert(visitor);
        //保存评论信息
        Comment comment = ObjectConverter.convert(commentInput, Comment.class);
        comment.setVisitorId(visitor.getId());
        commentMapper.insert(comment);
        //记录选择的标签个数
        String tags = commentInput.getTagsList();
        if(StringUtils.isNotBlank(tags)){
            String[] tagsList = tags.split(",");
            for (String var1 : tagsList){
                //记录数据
                tagsMapper.updateCount(Integer.valueOf(var1));
            }
        }
    }

    /**
     * 根据objectType查询标签信息
     * @param objectType
     * @return
     */
    @Override
    public List<TagsVo> queryTagsList(Integer objectType, Integer objectId, Integer quantity) {
        return tagsMapper.queryTagsByObjectType(objectType, objectId, quantity);
    }

    @Override
    public List<AreaVo> queryAreaList() {
        return areaMapper.queryAll();
    }

    /**
     * 查询某个object的评论信息
     * @param pageInfo
     * @param searchMap
     * @return
     */
    @Override
    public PageResultDTO<CommentVo> querCommentList(PageInfo pageInfo, Map<String, Object> searchMap) {
        Page<CommentVo> queryPage = new Page<>(pageInfo.getCurrent(), pageInfo.getSize());
        IPage<CommentVo> iPage = commentMapper.queryCommentList(queryPage, searchMap);
        return PageResultDTO.of(iPage, CommentVo.class);
    }
}
