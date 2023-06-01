package com.moulh.pg.core.handler;

import com.moulh.pg.core.dto.Result;
import com.moulh.pg.core.enums.ResultEnum;
import com.moulh.pg.core.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.nio.file.AccessDeniedException;
import java.util.Set;
import java.util.StringJoiner;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ResponseBody
    @ExceptionHandler(Throwable.class)
    public Result<String> handleApiConstraintViolationException(Throwable ex) {
        if (ex instanceof ConstraintViolationException) {
            ConstraintViolationException cex = (ConstraintViolationException) ex;
            StringJoiner message = new StringJoiner(",");
            Set<ConstraintViolation<?>> violations = cex.getConstraintViolations();
            for (ConstraintViolation<?> violation : violations) {
                message.add(violation.getMessage());
            }
            return Result.error(ResultEnum.INVALID_ARGUMENTS.getCode(), message.toString());

        }else if (ex instanceof IllegalArgumentException) {
            return Result.error(ResultEnum.INVALID_ARGUMENTS.getCode(), ex.getMessage());
        } else if (ex instanceof BusinessException) {
            log.info(ex.getMessage());
            //业务异常直接返回
            return Result.error(ResultEnum.BUSINESS_ERROR.getCode(), ex.getMessage());
        } else if (ex instanceof HttpRequestMethodNotSupportedException) {
            return Result.error(ResultEnum.INVALID_REQUEST_METHOD.getCode(),ResultEnum.INVALID_REQUEST_METHOD.getMessage());
        } else if (ex instanceof AccessDeniedException) {
            return Result.error(ResultEnum.FORBIDDEN.getCode(),ResultEnum.FORBIDDEN.getMessage());
        } else {
            log.error(ex.getMessage(), ex);
            return Result.error(ResultEnum.SYSTEM_ERROR.getCode(),ResultEnum.SYSTEM_ERROR.getMessage());
        }
    }

}
