# 工程简介

演示 org.springframework.beans.factory.FactoryBean 类的作用

定义：FactoryBean 是工厂类型的 bean。

分别有 总成（MyAutoConfiguration）、Factory（MyClientFactoryBean）、属性配置读取类（MyProperties）、目标Bean（MyClient）
MyClientFactoryBean 引用 MyProperties 生成 MyClientFactoryBean。
MyClientFactoryBean 生成 MyClient，并扩展实现了其它方法：destroy()、afterPropertiesSet()

# 延伸阅读

