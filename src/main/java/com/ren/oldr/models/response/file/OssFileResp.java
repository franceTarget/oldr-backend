package com.ren.oldr.models.response.file;

import lombok.Data;

@Data
public class OssFileResp {

    private String accessId;

    private String policy;

    private String signature;

    private String dir;

    private String host;

    private String expire;

    private String callback;

}
