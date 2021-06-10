package com.ren.oldr.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ren.oldr.dao.UserDao;
import com.ren.oldr.models.po.PageInfo;
import com.ren.oldr.models.request.copilot.CopilotReq;
import com.ren.oldr.models.response.copilot.CopilotResp;
import com.ren.oldr.service.CopilotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author canove
 * @date 2021/5/8 15:46
 * @description
 */
@Service
public class CopilotServiceImpl implements CopilotService {

    @Autowired
    private UserDao userDao;


    @Override
    public PageInfo<CopilotResp> findPage(CopilotReq req) {
        Page<CopilotResp> result = PageHelper.startPage(req.getPage(), req.getPageSize()).doSelectPage(() -> {
            userDao.getCopilotList(req);
        });
        return PageInfo.init(result);
    }
}
