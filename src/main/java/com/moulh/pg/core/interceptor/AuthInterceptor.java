package com.moulh.pg.core.interceptor;

import com.moulh.pg.core.constant.BasicConstant;
import com.moulh.pg.core.dto.UserTokenDTO;
import com.moulh.pg.core.service.RedisService;
import com.moulh.pg.core.util.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName : AuthInterceptor
 * @Author : moulh@paxsz.com
 * @Date : 2021-07-11 11:14
 * @Version : V1.0
 * @Description :
 */
@Slf4j
public class AuthInterceptor implements HandlerInterceptor {
    @Autowired
    private RedisService redisService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authToken = request.getHeader("Authorization");
        if(StringUtils.isBlank(authToken)){
            return false;
        }
        String token = authToken.substring("Bearer".length()).trim();
        UserTokenDTO userTokenDTO = JWTUtil.parseToken(token);
        //1.判断请求是否有效
        if (redisService.get(BasicConstant.TOKEN_KEY + userTokenDTO.getId()) == null
                || !redisService.get(BasicConstant.TOKEN_KEY + userTokenDTO.getId()).equals(token)) {
            return false;
        }

        //2.判断是否需要续期
        if (redisService.getExpireTime(String.valueOf(userTokenDTO.getId())) < 1 * 60 * 30) {
            redisService.set(BasicConstant.TOKEN_KEY + userTokenDTO.getId(), token);
            log.error("update token info, id is:{}, user info is:{}", userTokenDTO.getId(), token);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
