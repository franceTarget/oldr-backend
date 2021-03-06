package com.ren.oldr.web;

import com.ren.oldr.models.po.UserInfo;


/**
 * @author: target
 * @date: 2020/5/8 13:39
 * @description:
 */
public class LoginUserHolder {

    private LoginUserHolder(){}

    private static ThreadLocal<UserInfo> loginUser = new ThreadLocal<>();

    /**
     * 获取当前登录用户
     */
    public static UserInfo getUser() {
        return loginUser.get();
    }

    public static void bind(UserInfo user) {
        loginUser.set(user);
    }

    public static void unbind() {
        loginUser.remove();
    }

    public static String getUserId() {
        if (getUser() == null) {
            return null;
        }
        return getUser().getId();
    }

    public static String getNickName() {
        if (getUser() == null) {
            return null;
        }
        return getUser().getNickName();
    }
}
