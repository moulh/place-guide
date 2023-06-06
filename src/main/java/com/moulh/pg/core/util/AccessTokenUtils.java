package com.moulh.pg.core.util;

import com.moulh.pg.core.constant.BasicConstant;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName : AccessTokenUtils
 * @Author : moulh
 * @Date : 2022-06-07 11:21
 * @Version : V1.0
 * @Description :
 */
public class AccessTokenUtils {

    public static String getAccessToken(){
        HttpServletRequest request = HttpServletUtils.getHttpRequest();
        if (request == null) {
            return "";
        }
        String bearerAccessToken = request.getHeader(BasicConstant.HEADER_AUTHORIZATION);

        if(StringUtils.isNotBlank(bearerAccessToken)){
            return StringUtils.replaceIgnoreCase(bearerAccessToken, BasicConstant.BEARER, "").trim();
        }else{ //如果在头部没有找到token，则检查参数中是否存在
            String accessToken = request.getParameter(BasicConstant.ACCESS_TOKEN);
            return accessToken == null?"":accessToken;
        }
    }
}
