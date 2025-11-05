package com.aliyun.oss;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

public class AliOSSUtils {

    private AliOSS aliOSS;

    public AliOSS getAliOSS() {
        return aliOSS;
    }

    public void setAliOSS(AliOSS aliOSS) {
        this.aliOSS = aliOSS;
    }

    public String upload(MultipartFile file) throws IOException {
        String endpoint = aliOSS.getEndpoint();
        String bucketName = aliOSS.getBucketName();
        String accessKeyId = aliOSS.getAccessKeyId();
        String accessKeySecret = aliOSS.getAccessKeySecret();

        // 获取上传的文件的输入流
        InputStream inputStream = file.getInputStream();

        // 避免文件覆盖
        String originalFilename = file.getOriginalFilename();
        String fileName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));

        // OSS对象存储两行核心代码：上传文件到 OSS
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        ossClient.putObject(bucketName, fileName, inputStream);

        // 手动拼接为OSS存储对象中返回给我的URL地址，只有用OSS返回的地址才能访问
        String url = endpoint.split("//")[0] + "//" + bucketName + "." + endpoint.split("//")[1] + "/" + fileName;

        // 关闭ossClient
        ossClient.shutdown();

        return url;// 把上传到oss的路径返回
    }
}
