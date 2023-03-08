package com.jett;

import java.time.LocalDateTime;

public class MyClient {
    
    private final String accountName;
    private final String accessKeyId;
    private final String accessKeySecret;
    
    public MyClient(String accountName, String accessKeyId, String accessKeySecret) {
        this.accountName = accountName;
        this.accessKeyId = accessKeyId;
        this.accessKeySecret = accessKeySecret;
    }
    
    /**
     * 关闭客户端
     */
    public void shutdown() {
        System.out.println("shutdown：" + LocalDateTime.now());
    }
    
    /**
     * 做些什么
     */
    public void doSomething() {
        System.out.println(LocalDateTime.now());
    }
    
}
