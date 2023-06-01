package com.moulh.pg.core.dto;

import com.moulh.pg.core.enums.ResultEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName : Result
 * @Author : moulh@paxsz.com
 * @Date : 2021-07-11 11:59
 * @Version : V1.0
 * @Description :
 */
@Data
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 8992436576262574064L;
    private Integer code;
    private String message;
    private T result;
    private Long timestamp;

    private Result() {
    }

    public static <T> Result<T> error() {
        return error(Integer.valueOf(ResultEnum.SYSTEM_ERROR.getCode()), ResultEnum.SYSTEM_ERROR.getMessage());
    }

    public static <T> Result<T> error(String message) {
        return error(Integer.valueOf(ResultEnum.BUSINESS_ERROR.getCode()), message);
    }

    public static <T> Result<T> error(Integer code, String message) {
        Result<T> msg = new Result();
        msg.message = message;
        msg.code = code;
        return msg.putTimeStamp();
    }

    public static <T> Result<T> error(ResultEnum resultEnum) {
        Result<T> msg = new Result();
        msg.message = resultEnum.getMessage();
        msg.code = Integer.valueOf(resultEnum.getCode());
        return msg.putTimeStamp();
    }

    public static <T> Result<T> ok(T result) {
        return (new Result()).result(result).putTimeStamp().code(Integer.valueOf(ResultEnum.SUCCESS.getCode())).msg(ResultEnum.SUCCESS.getMessage());
    }

    public static <T> Result<T> ok() {
        return (new Result()).putTimeStamp().code(Integer.valueOf(ResultEnum.SUCCESS.getCode())).msg(ResultEnum.SUCCESS.getMessage());
    }

    private Result<T> putTimeStamp() {
        this.timestamp = Long.valueOf(System.currentTimeMillis());
        return this;
    }

    public Result<T> result(T result) {
        this.result = result;
        return this;
    }

    public Result<T> code(Integer code) {
        this.code = code;
        return this;
    }

    public Result<T> msg(String msg) {
        this.message = msg;
        return this;
    }
}
