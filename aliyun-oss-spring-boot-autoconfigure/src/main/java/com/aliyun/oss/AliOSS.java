package com.aliyun.oss;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "aliyun.oss") // yaml文件中配置的公共前缀
public class AliOSS {
    private String endpoint;
    private String bucketName;
    private String accessKeyId;
    private String accessKeySecret;
}
