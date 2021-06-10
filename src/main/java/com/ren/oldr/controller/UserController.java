package com.ren.oldr.controller;

import com.ren.oldr.common.annotation.ApiMapping;
import com.ren.oldr.common.annotation.IgnoreLoginCheck;
import com.ren.oldr.models.po.UserInfo;
import com.ren.oldr.models.request.LoginReq;
import com.ren.oldr.models.response.Response;
import com.ren.oldr.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author: target
 * @date: 2020/5/8 15:36
 * @description:
 */
@Slf4j
@Api(tags = "用户操作接口")
@ApiMapping
public class UserController {

    @Autowired
    private UserService userService;

    @IgnoreLoginCheck
    @ApiOperation(value = "小程序登陆", notes = "")
    @PostMapping(value = "/user/applet/login")
    public Response<UserInfo> appletLogin(@RequestBody LoginReq req) {
        return Response.ok("登陆成功", userService.firstLogin(req));
    }

    @ApiOperation(value = "小程序用户授权信息", notes = "")
    @PostMapping(value = "/user/profile")
    public Response<Boolean> userProfile(@RequestBody LoginReq req) {
        return Response.ok("授权成功", userService.userProfile(req));
    }

}
