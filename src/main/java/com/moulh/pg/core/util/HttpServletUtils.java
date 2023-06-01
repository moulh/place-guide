package com.moulh.pg.core.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName : HttpServletUtils
 * @Author : moulh@paxsz.com
 * @Date : 2022-06-07 11:22
 * @Version : V1.0
 * @Description :
 */
public class HttpServletUtils {

    public static HttpServletRequest getHttpRequest() {
        ServletRequestAttributes servletRequestAttributes = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());
        if (servletRequestAttributes != null) {
            return servletRequestAttributes.getRequest();
        }
        return null;
    }

    public static HttpServletResponse getHttpResponse() {
        ServletRequestAttributes servletRequestAttributes = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());
        if (servletRequestAttributes != null) {
            return servletRequestAttributes.getResponse();
        }
        return null;
    }

    public static String getRequestParamter(String parameterKey) {
        return getHttpRequest().getParameter(parameterKey);
    }


    public static String getUserAgent(){
        HttpServletRequest httpServletRequest = getHttpRequest();

        String httpUserAgent = "";
        if(null != (httpUserAgent = httpServletRequest.getHeader("user-agent"))
                || null != (httpUserAgent = httpServletRequest.getHeader("User-Agent"))
                || null != (httpUserAgent = httpServletRequest.getHeader("USER-AGENT"))){
            return httpUserAgent;
        }

        return "";
    }

    private static final Pattern mobilePattern = Pattern.compile("/Mobile|iPhone|Android|WAP|NetFront|JAVA|OperasMini|UCWEB|WindowssCE|Symbian|Series|webOS|SonyEricsson|Sony|BlackBerry|Cellphone|dopod|Nokia|samsung|PalmSource|Xphone|Xda|Smartphone|PIEPlus|MEIZU|MIDP|CLDC/i");

    public static boolean isMobile() {
        HttpServletRequest httpServletRequest = getHttpRequest();


        if(StringUtils.isNotBlank(httpServletRequest.getHeader("x-wap-profile"))
                || StringUtils.isNotBlank(httpServletRequest.getHeader("X-Wap-Profile"))
                || StringUtils.isNotBlank(httpServletRequest.getHeader("X-WAP-PROFILE"))){
            return true;
        }

        String httpVia = "";
        if(null != (httpVia = httpServletRequest.getHeader("via"))
                || null != (httpVia = httpServletRequest.getHeader("via"))
                || null != (httpVia = httpServletRequest.getHeader("via"))){
            if(StringUtils.isNotBlank(httpVia)
                    && StringUtils.containsIgnoreCase(httpVia, "wap")){
                return true;
            }
        }

        String httpUserAgent = getUserAgent();

        if(StringUtils.isNotBlank(httpUserAgent)){
            Matcher matcher = mobilePattern.matcher(httpUserAgent);
            if(matcher.find()){
                return true;
            }
        }

        return false;
    }

    public static boolean isIPhone() {
        String httpUserAgent = getUserAgent();
        return (StringUtils.isNotBlank(httpUserAgent) && StringUtils.containsIgnoreCase(httpUserAgent, "iPhone"));
    }

    public static boolean isIPad() {
        String httpUserAgent = getUserAgent();
        return (StringUtils.isNotBlank(httpUserAgent) && StringUtils.containsIgnoreCase(httpUserAgent, "'iPad'"));
    }

    public static boolean isIOS() {
        return isIPhone () || isIPad ();
    }

    public static boolean isAndroid() {
        String httpUserAgent = getUserAgent();
        return (StringUtils.isNotBlank(httpUserAgent) && StringUtils.containsIgnoreCase(httpUserAgent, "Android"));
    }

    public static boolean isWechat(){
        String httpUserAgent = getUserAgent();
        return (StringUtils.isNotBlank(httpUserAgent) && StringUtils.containsIgnoreCase(httpUserAgent, "MicroMessenger"));
    }

    public static boolean isAlipay(){
        String httpUserAgent = getUserAgent();
        return (StringUtils.isNotBlank(httpUserAgent) && StringUtils.containsIgnoreCase(httpUserAgent, "AlipayClient"));
    }
}
