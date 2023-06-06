package com.moulh.pg.busi.controller;

import com.moulh.pg.core.dto.Result;
import com.moulh.pg.core.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName : LoginController
 * @Author : moulh
 * @Date : 2021-07-11 11:56
 * @Version : V1.0
 * @Description : 登录登出接口
 */
@RestController
@Api(tags = "web管理端登录登出接口")
@RequestMapping("/login")
@AllArgsConstructor
public class LoginController {
    private LoginService loginService;

    @ApiOperation("登录")
    @GetMapping({"/", ""})
    public Result<String> login(String username, String password) {
        return Result.ok(loginService.login(username, password));
    }

    @ApiOperation("登出")
    @GetMapping("/logout")
    public Result<String> logout(HttpServletRequest request) {
        return Result.ok(loginService.logout(request));
    }
}
