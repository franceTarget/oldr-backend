package com.ren.oldr.controller;

import com.ren.oldr.common.annotation.ApiMapping;
import com.ren.oldr.models.response.Response;
import com.ren.oldr.models.response.file.OssFileResp;
import com.ren.oldr.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

@ApiMapping
public class FileController {

    @Autowired
    private FileService fileService;

    @GetMapping("/file/upload/sign")
    public Response<OssFileResp> getSignature(){
        return Response.ok(fileService.getUploadSign());
    }
}
