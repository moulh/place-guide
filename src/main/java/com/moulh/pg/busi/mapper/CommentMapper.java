package com.moulh.pg.busi.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.moulh.pg.busi.entity.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.moulh.pg.busi.vo.CommentVo;
import com.moulh.pg.busi.vo.SpotObjectListVo;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.Map;

/**
 * <p>
 * 对象评论表 Mapper 接口
 * </p>
 *
 * @author moulh
 * @since 2021-07-11
 */
public interface CommentMapper extends BaseMapper<Comment> {
    IPage<CommentVo> queryCommentList(Page<CommentVo> page, @Param("searchMap") Map<String, Object> searchMap);

    String queryAvgScore(Integer objectId);

    String queryPerOfGood(Integer objectId);
}
