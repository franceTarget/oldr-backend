package com.ren.oldr.service.impl;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import com.ren.oldr.common.BizException;
import com.ren.oldr.models.response.file.OssFileResp;
import com.ren.oldr.service.FileService;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.sql.Date;

@Slf4j
@Service
public class FileServiceImpl implements FileService {

    @Override
    public OssFileResp getUploadSign() {
        // 请填写您的AccessKeyId。
        String accessId = "xxxxxxxxx";
        // 请填写您的AccessKeySecret。
        String accessKey = "xxxxxxx";
        // 请填写您的 endpoint。
        String endpoint = "localhost";
        // 请填写您的 bucketname 。
        String bucket = "xxxxx";
        // host的格式为 bucketname.endpoint
        String host = "http://" + bucket + "." + endpoint;
        // 用户上传文件时指定的前缀。
        String dir = "xxxxx/";
        OSSClient client = new OSSClient(endpoint, accessId, accessKey);

        long expireTime = 30;
        long expireEndTime = System.currentTimeMillis() + expireTime * 1000;
        Date expiration = new Date(expireEndTime);
        PolicyConditions policyConds = new PolicyConditions();
        policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
        policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);

        String postPolicy = client.generatePostPolicy(expiration, policyConds);
        byte[] binaryData = new byte[0];
        try {
            binaryData = postPolicy.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            throw new BizException("获取签名失败");
        }
        String encodedPolicy = BinaryUtil.toBase64String(binaryData);
        String postSignature = client.calculatePostSignature(postPolicy);

        OssFileResp ossFileResp = new OssFileResp();
        ossFileResp.setAccessId(accessId);
        ossFileResp.setPolicy(encodedPolicy);
        ossFileResp.setSignature(postSignature);
        ossFileResp.setDir(dir);
        ossFileResp.setHost(host);
        ossFileResp.setExpire(String.valueOf(expireEndTime / 1000));

        // callbackUrl为 上传回调服务器的URL，请将下面的IP和Port配置为您自己的真实信息。
//        String callbackUrl = "http://47.107.56.61:8080";
//        JSONObject jasonCallback = new JSONObject();
//        jasonCallback.put("callbackUrl", callbackUrl);
//        jasonCallback.put("callbackBody",
//                "filename=${object}&size=${size}&mimeType=${mimeType}&height=${imageInfo.height}&width=${imageInfo.width}");
//        jasonCallback.put("callbackBodyType", "application/x-www-form-urlencoded");
//        String base64CallbackBody = BinaryUtil.toBase64String(jasonCallback.toString().getBytes());
//        ossFileResp.setCallback(base64CallbackBody);
        return ossFileResp;

    }

    public static void main(String[] args) {
        // 请填写您的AccessKeyId。
        String accessId = "xxxxx";
        // 请填写您的AccessKeySecret。
        String accessKey = "xxxxx";
        // 请填写您的 endpoint。
        String endpoint = "localhost";
        OSSClient client = new OSSClient(endpoint, accessId, accessKey);
        Date expiration = new Date(System.currentTimeMillis() + 3600 * 1000);
        URL url = client.generatePresignedUrl("xxx", "xxx/test.png", expiration);
        System.out.println(url);
        System.out.println(url.toString());
    }
}
