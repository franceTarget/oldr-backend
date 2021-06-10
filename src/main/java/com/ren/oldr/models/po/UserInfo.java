package com.ren.oldr.models.po;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: target
 * @date: 2020/6/12 10:41
 * @description:
 */
@Data
public class UserInfo {

    @ApiModelProperty(value = "用户id")
    private String id;

    @ApiModelProperty(value = "用户昵称")
    private String nickName;

    @ApiModelProperty(value ="头像",dataType = "String")
    private String avatarUrl;

    @ApiModelProperty(value ="性别",dataType = "Integer")
    private Integer gender;

    @ApiModelProperty(value = "凭证")
    private String token;

}
