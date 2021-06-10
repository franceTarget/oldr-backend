package com.ren.oldr.controller;

import com.ren.oldr.common.annotation.ApiMapping;
import com.ren.oldr.common.annotation.ParamToBody;
import com.ren.oldr.models.po.PageInfo;
import com.ren.oldr.models.request.copilot.CopilotReq;
import com.ren.oldr.models.response.Response;
import com.ren.oldr.models.response.copilot.CopilotResp;
import com.ren.oldr.service.CopilotService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author canove
 * @date 2021/5/8 15:30
 * @description
 */
@Slf4j
@Api(tags = "寻找副驾")
@ApiMapping
public class CopilotController {

    @Autowired
    private CopilotService copilotService;

    @ApiOperation(value = "根据token获取信息", response = CopilotResp.class)
    @GetMapping("/find/copilot/page")
    public Response<PageInfo<CopilotResp>> findCopilotPage(@ApiParam(value = "当前页") @RequestParam(name = "page", required = false) Integer page,
                                                           @ApiParam(value = "每页条数") @RequestParam(name = "pageSize", required = false) Integer pageSize,
                                                           @ApiParam(value = "性别") @RequestParam(name="gender",required = false,defaultValue = "0") Integer gender,
                                                           @ParamToBody CopilotReq req) {
        return Response.ok(copilotService.findPage(req));
    }

}
