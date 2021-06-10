package com.ren.oldr.models.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author canove
 * @date 2021/5/7 14:22
 * @description
 */
@Data
public class LoginReq {

    private String code;

    private String avatarUrl;

    private String nickName;

    private String id;

    private Integer gender;

}
