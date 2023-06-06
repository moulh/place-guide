package com.moulh.pg.core.util;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Iterator;
import java.util.List;

/**
 * @ClassName : PgValidateUtils
 * @Author : moulh
 * @Date : 2021-07-12 14:20
 * @Version : V1.0
 * @Description :
 */
public class PgValidateUtils{
    /**
     * 重写验证错误信息,满足国际化
     * @param bindingResult
     * @return
     */
    public static String getErrorMessages(BindingResult bindingResult) {
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        StringBuilder errorMessages = new StringBuilder();
        Iterator var3 = fieldErrors.iterator();

        while(var3.hasNext()) {
            FieldError fieldError = (FieldError)var3.next();
            errorMessages.append(",").append(fieldError.getDefaultMessage());
        }

        return errorMessages.substring(1);
    }
}
