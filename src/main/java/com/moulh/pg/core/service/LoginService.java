package com.moulh.pg.core.service;

import com.moulh.pg.busi.mapper.UserMapper;
import com.moulh.pg.core.constant.BasicConstant;
import com.moulh.pg.core.dto.SysUser;
import com.moulh.pg.core.dto.UserTokenDTO;
import com.moulh.pg.core.exception.BusinessException;
import com.moulh.pg.core.util.AccessTokenUtils;
import com.moulh.pg.core.util.JWTUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName : LoginService
 * @Author : moulh
 * @Date : 2021-07-11 11:18
 * @Version : V1.0
 * @Description :
 */
@Service
@SuppressWarnings("ALL")
public class LoginService {
    @Autowired
    private RedisService redisService;
    @Autowired
    private UserMapper userMapper;


    public String login(String username, String password) {
        SysUser sysUser = userMapper.findByUsername(username);
        if (sysUser != null && StringUtils.equals(sysUser.getPwd(), password)) {
            UserTokenDTO userTokenDTO = new UserTokenDTO();
            userTokenDTO.setId(sysUser.getId());
            userTokenDTO.setUserName(sysUser.getUserName());
            userTokenDTO.setGmtCreate(System.currentTimeMillis());
            String token = JWTUtil.generateToken(userTokenDTO);
            //3.存入token至redis
            redisService.set(BasicConstant.TOKEN_KEY + String.valueOf(sysUser.getId()), token);
            return token;
        } else {
            throw new BusinessException("用户名或密码错误");
        }
    }

    public String logout(HttpServletRequest request) {
        String token = AccessTokenUtils.getAccessToken();
        UserTokenDTO userTokenDTO = JWTUtil.parseToken(token);
        Boolean delete = redisService.delete(BasicConstant.TOKEN_KEY + String.valueOf(userTokenDTO.getId()));
        if (!delete) {
           throw new BusinessException("注销失败，请检查是否登录！");
        }
        return "注销成功！";
    }
}
