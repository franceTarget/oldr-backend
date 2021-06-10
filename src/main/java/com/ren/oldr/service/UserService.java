package com.ren.oldr.service;

import com.ren.oldr.models.request.LoginReq;
import com.ren.oldr.models.po.UserInfo;

/**
 * @author: target
 * @date: 2020/5/8 13:43
 * @description:
 */
public interface UserService {

    UserInfo firstLogin(LoginReq req);

    Boolean userProfile(LoginReq req);
}
