package com.jett;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自动装配类
 */
@Configuration
@ConditionalOnClass({MyClient.class})
@EnableConfigurationProperties(MyProperties.class)
public class MyAutoConfiguration {
    
    private final MyProperties myProperties;
    
    public MyAutoConfiguration(final MyProperties myProperties) {
        this.myProperties = myProperties;
    }
    
    @Bean
    public MyClientFactoryBean myClientFactoryBean() {
        final MyClientFactoryBean factoryBean = new MyClientFactoryBean();
        factoryBean.setAccountName(this.myProperties.getAccountName());
        factoryBean.setAccessKeyId(this.myProperties.getAccessKeyId());
        factoryBean.setAccessKeySecret(this.myProperties.getAccessKeySecret());
        return factoryBean;
    }
}
