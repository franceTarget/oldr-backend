package com.ren.oldr.models.request.copilot;

import com.ren.oldr.models.request.PageReq;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author canove
 * @date 2021/5/8 15:34
 * @description
 */
@Data
public class CopilotReq extends PageReq {

    @ApiModelProperty(value = "性别")
    private Integer gender;

}
