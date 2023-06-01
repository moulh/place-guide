package com.moulh.pg.busi.service.impl;

import com.moulh.pg.busi.entity.Comment;
import com.moulh.pg.busi.mapper.CommentMapper;
import com.moulh.pg.busi.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 对象评论表 服务实现类
 * </p>
 *
 * @author moulh
 * @since 2021-07-11
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

}
