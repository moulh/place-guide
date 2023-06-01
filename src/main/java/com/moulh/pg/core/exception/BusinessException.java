package com.moulh.pg.core.exception;

/**
 * @ClassName : BusinessException
 * @Author : moulh@paxsz.com
 * @Date : 2021-07-19 11:24
 * @Version : V1.0
 * @Description :
 */
public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = 997104990644926037L;

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
