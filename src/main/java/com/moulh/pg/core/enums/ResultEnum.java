package com.moulh.pg.core.enums;

/**
 * @ClassName : ResultEnum
 * @Author : moulh
 * @Date : 2021-07-07 14:59
 * @Version : V1.0
 * @Description :
 */
public enum ResultEnum {
    /**
     * 请求成功
     */
    SUCCESS(20000, "请求成功"),

    SYSTEM_ERROR(30000, "系统错误，请重试"),

    SESSION_TIMEOUT(40000, "登录超时,请重新登录"),

    FORBIDDEN(40001, "未授权，拒绝访问"),

    BUSINESS_ERROR(50000, "业务处理异常"),

    INVALID_ARGUMENTS(50001, "非法参数"),

    INVALID_REQUEST_METHOD(30001, "非法的请求方式");


    private int code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
