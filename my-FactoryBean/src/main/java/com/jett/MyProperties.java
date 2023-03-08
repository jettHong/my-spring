package com.jett;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 配置类
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "my")
public class MyProperties {
    
    /**
     * 账号名称，故意哒
     */
    private String accountName;
    
    /**
     * 用于标识用户
     */
    private String accessKeyId;
    
    /**
     * 用于验证用户的密钥。AccessKey Secret必须保密。
     */
    private String accessKeySecret;
}
