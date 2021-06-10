package com.ren.oldr.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ren.oldr.common.Constants;
import com.ren.oldr.common.utils.IDUtil;
import com.ren.oldr.dao.UserDao;
import com.ren.oldr.models.entity.User;
import com.ren.oldr.models.po.UserInfo;
import com.ren.oldr.models.request.LoginReq;
import com.ren.oldr.models.response.WecatLoginResp;
import com.ren.oldr.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: target
 * @date: 2020/5/8 13:42
 * @description:
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final static ConcurrentHashMap<String, UserInfo> userCache = new ConcurrentHashMap<>(256);

    @Autowired
    private UserDao userDao;
    @Autowired
    private RestTemplate restTemplate;

    @Value("${wx.appid:wxd8e954dce20c1a51}")
    private String appid;
    @Value("${wx.secret:fba8753b463ff4f1046ef3bc55150404}")
    private String secret;

    @Override
    public UserInfo firstLogin(LoginReq req) {
        StringBuilder sb = new StringBuilder();
        sb.append(Constants.APPLET_LOGIN_URL)
                .append("?")
                .append("appid")
                .append("=")
                .append(appid)
                .append("&")
                .append("secret")
                .append("=")
                .append(secret)
                .append("&")
                .append("js_code")
                .append("=")
                .append(req.getCode())
                .append("&grant_type=authorization_code");
        String result = restTemplate.getForObject(sb.toString(), String.class);
        log.info("获取用户信息[{}]", result);
        WecatLoginResp wecatLoginResp = JSONObject.parseObject(result, WecatLoginResp.class);

        UserInfo userInfo = new UserInfo();
        //通过openid查询用户是否存在
        User user = userDao.selectByOperId(wecatLoginResp.getOpenid());
        if (null == user) {
            user = new User();
            user.setOpenid(wecatLoginResp.getOpenid());
            String id = IDUtil.nextStrId();
            user.setId(id);
            userDao.insertSelective(user);
            userInfo.setId(id);
        }
        BeanUtils.copyProperties(user, userInfo);
        return userInfo;
    }

    @Override
    public Boolean userProfile(LoginReq req) {
        User user = new User();
        user.setAvatarUrl(req.getAvatarUrl());
        user.setId(req.getId());
        user.setNickName(req.getNickName());
        user.setGender(req.getGender());
        userDao.updateByPrimaryKeySelective(user);
        return true;
    }
}
