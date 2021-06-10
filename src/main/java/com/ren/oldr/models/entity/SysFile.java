package com.ren.oldr.models.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;
import java.io.Serializable;

/**
 * <p>
 * 文件表
 * </p>
 *
 * @author xxx
 * @since 2021-05-08
 */
@Data
@ApiModel("文件表")
public class SysFile implements Serializable{

    private static final long serialVersionUID=1L;


    @ApiModelProperty(value ="主键",dataType = "Long")
    private String id;

    @ApiModelProperty(value ="业务id",dataType = "Long")
    private String bizId;

    @ApiModelProperty(value ="类型1-人员附照",dataType = "Integer")
    private Integer type;

    @ApiModelProperty(value ="文件名称",dataType = "String")
    private String fileName;

    @ApiModelProperty(value ="文件路径",dataType = "String")
    private String filePath;

    @ApiModelProperty(value ="文件后缀",dataType = "String")
    private String fileSuffix;

    @ApiModelProperty(value ="是否启用",dataType = "Integer")
    private Integer enableFlag;

    @ApiModelProperty(value ="创建人id",dataType = "Long")
    private String createById;

    @ApiModelProperty(value ="创建时间",dataType = "Date")
    private Date createTime;

    @ApiModelProperty(value ="修改人id",dataType = "Long")
    private String updateById;

    @ApiModelProperty(value ="修改时间",dataType = "Date")
    private Date updateTime;

}
