package com.ren.oldr.models.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author xxx
 * @since 2021-05-07
 */
@Data
@ApiModel("用户表")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @ApiModelProperty(value = "主键", dataType = "Long")
    private String id;

    @ApiModelProperty(value = "应用id", dataType = "String")
    private String openid;

    @ApiModelProperty(value = "昵称", dataType = "String")
    private String nickName;

    @ApiModelProperty(value = "性别", dataType = "Integer")
    private Integer gender;

    @ApiModelProperty(value = "头像", dataType = "String")
    private String avatarUrl;

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

    @ApiModelProperty(value = "创建人id", dataType = "Long")
    private Long createById;

    @ApiModelProperty(value = "创建时间", dataType = "Date")
    private Date createTime;

    @ApiModelProperty(value = "修改人id", dataType = "Long")
    private Long updateById;

    @ApiModelProperty(value = "修改时间", dataType = "Date")
    private Date updateTime;

}