package com.moulh.pg.busi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.moulh.pg.busi.entity.User;
import com.moulh.pg.core.dto.SysUser;

/**
 * @ClassName : UserMapper
 * @Author : moulh
 * @Date : 2021-07-09 16:20
 * @Version : V1.0
 * @Description :
 */
public interface UserMapper extends BaseMapper<User> {
    SysUser findByUsername(String username);
}
