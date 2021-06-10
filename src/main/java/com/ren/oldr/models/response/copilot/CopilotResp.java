package com.ren.oldr.models.response.copilot;

import com.ren.oldr.common.utils.StringUtil;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author canove
 * @date 2021/5/8 15:34
 * @description
 */
@Data
public class CopilotResp {

    @ApiModelProperty(value = "昵称", dataType = "String")
    private String nickName;

    @ApiModelProperty(value = "美照")
    private String photograph;

    @ApiModelProperty(value = "省份", dataType = "String")
    private String province;

    @ApiModelProperty(value = "城市", dataType = "String")
    private String city;

    @ApiModelProperty(value = "区域", dataType = "String")
    private String area;

    @ApiModelProperty(value = "年龄", dataType = "Integer")
    private Integer age;

    @ApiModelProperty(value = "身高", dataType = "Integer")
    private Integer height;

    @ApiModelProperty(value = "体重", dataType = "Integer")
    private Integer weight;

    @ApiModelProperty(value = "简介", dataType = "String")
    private String resume;

    @ApiModelProperty(value = "标签")
    private String tag;

    private List<String> tags;

    public List<String> getTags() {
        return StringUtil.strSplitToList(tag, ",");
    }
}
