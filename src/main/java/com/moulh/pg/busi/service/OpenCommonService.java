package com.moulh.pg.busi.service;

import com.moulh.pg.busi.input.CommentInput;
import com.moulh.pg.busi.input.TagsInput;
import com.moulh.pg.busi.vo.AreaVo;
import com.moulh.pg.busi.vo.CommentVo;
import com.moulh.pg.busi.vo.TagsVo;
import com.moulh.pg.core.dto.PageInfo;
import com.moulh.pg.core.dto.PageResultDTO;

import java.util.List;
import java.util.Map;

/**
 * @ClassName : OpenCommonService
 * @Author : moulh@paxsz.com
 * @Date : 2021-07-12 18:30
 * @Version : V1.0
 * @Description : 对外公共接口
 */
public interface OpenCommonService {
    void saveTags(TagsInput tagsInput);

    void saveComment(CommentInput commentInput);

    List<TagsVo> queryTagsList(Integer objectType, Integer objectId, Integer quantity);

    List<AreaVo> queryAreaList();

    PageResultDTO<CommentVo> querCommentList(PageInfo pageInfo, Map<String, Object> searchMap);
}
