package com.ren.oldr.service;

import com.ren.oldr.models.po.PageInfo;
import com.ren.oldr.models.request.copilot.CopilotReq;
import com.ren.oldr.models.response.copilot.CopilotResp;

/**
 * @author canove
 * @date 2021/5/8 15:46
 * @description
 */
public interface CopilotService {

    PageInfo<CopilotResp> findPage(CopilotReq req);
}
