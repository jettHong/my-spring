package com.jett;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

/**
 * 实现了spring的FactoryBean
 * 作为Factory用于生产<MyClient>对象。
 * 请注意观察实现的接口方法
 */
public class MyClientFactoryBean implements FactoryBean<MyClient>, InitializingBean, DisposableBean {
    
    private MyClient myClient;
    private String accountName;
    private String accessKeyId;
    private String accessKeySecret;
    
    /**
     * 实现接口FactoryBean的取得对象方法
     *
     * @return
     * @throws Exception
     */
    @Override
    public MyClient getObject() throws Exception {
        return this.myClient;
    }
    
    /**
     * 实现接口FactoryBean的取得对象类型
     *
     * @return
     */
    @Override
    public Class<?> getObjectType() {
        return MyClient.class;
    }
    
    /**
     * 是否返回的 com.jett.OssClientFactoryBean#getObject() 是否单例
     * 更多请查看原API说明
     *
     * @return
     */
    @Override
    public boolean isSingleton() {
        return true;
    }
    
    /**
     * 销毁(回收)Bean时调用
     *
     * @throws Exception
     */
    @Override
    public void destroy() throws Exception {
        if (this.myClient != null) {
            this.myClient.shutdown();
        }
    }
    
    /**
     * 在bean的属性初始化后都会执行该方法
     * 一般用来验证该bean参数是否正确
     *
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(this.accountName, "'accountName' must be not null");
        Assert.notNull(this.accessKeyId, "'accessKeyId' must be not null");
        Assert.notNull(this.accessKeySecret, "'accessKeySecret' must be not null");
        this.myClient = new MyClient(this.accountName, this.accessKeyId, this.accessKeySecret);
    }
    
    public void setAccountName(final String accountName) {
        this.accountName = accountName;
    }
    
    public void setAccessKeyId(final String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }
    
    public void setAccessKeySecret(final String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }
}
