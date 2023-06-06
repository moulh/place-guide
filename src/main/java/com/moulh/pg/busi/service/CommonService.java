package com.moulh.pg.busi.service;

import com.moulh.pg.busi.entity.SaveImageResult;
import com.moulh.pg.busi.input.UserUpdatePwdInput;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName : CommonService
 * @Author : moulh
 * @Date : 2021-07-15 17:35
 * @Version : V1.0
 * @Description :
 */
public interface CommonService {
    SaveImageResult saveImages(HttpServletRequest request);

    String updateUserPsw(UserUpdatePwdInput userUpdatePwdInput);
}
