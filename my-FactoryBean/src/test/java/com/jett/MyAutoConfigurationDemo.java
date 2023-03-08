package com.jett;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Ignore
@SpringBootTest(classes = MyAutoConfiguration.class)
public class MyAutoConfigurationDemo {
    @Autowired(required = false) // TODO why ? required = false
    private MyClient client;
    
    @Test
    public void getClientAndDoSomething() throws Exception {
        this.client.doSomething();
    }
}
