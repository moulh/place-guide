package com.moulh.pg.busi.controller;

import com.moulh.pg.busi.entity.User;
import com.moulh.pg.busi.input.UserInput;
import com.moulh.pg.busi.input.UserUpdatePwdInput;
import com.moulh.pg.busi.mapper.UserMapper;
import com.moulh.pg.busi.service.CommonService;
import com.moulh.pg.core.dto.Result;
import com.moulh.pg.core.util.ObjectConverter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName : UserController
 * @Author : moulh
 * @Date : 2021-07-26 17:10
 * @Version : V1.0
 * @Description :
 */
@RestController
@Api(tags = "web管理端用户管理接口")
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    private CommonService commonService;
    private UserMapper userMapper;

    @ApiOperation("新增用户信息")
    @PostMapping("/save")
    public Result<Integer> saveUser(UserInput userInput){
        User user = ObjectConverter.convert(userInput, User.class);
        userMapper.insert(user);
        return Result.ok();
    }

    @ApiOperation("修改用户密码")
    @PostMapping("/updatepsw")
    public Result<String> updatePwd(UserUpdatePwdInput userUpdatePwdInput){
        commonService.updateUserPsw(userUpdatePwdInput);
        return Result.ok();
    }
}
