<p align="center">
    <a href="" target="_blank">
      <img src="https://s21.ax1x.com/2024/11/26/pA4ZTJA.png" width="128" />
    </a>
</p>

<h1 align="center">icutool-icu工具</h1>
<p align="center">
<strong>一个支持多端消息同步的工具,并且支持接入微信公众号</strong>
<br>
<h6>您是否会遇到这样的场景?手机有文本或图片着急传输到电脑,或者电脑有文本或图片急需发送到手机?该项目可以纯网页传输文本也可以通过微信公众号来快速同步消息</h6>
<em>功能持续更新 ing～</em>
</p>

<div align="center">
    <a href="#公众号"><img src="https://img.shields.io/badge/公众号-筱涛博客-blue.svg?style=plasticr"></a>
    <a href="https://gitee.com/plusboy/icutool-web-vue"><img src="https://img.shields.io/badge/前端-项目地址-blueviolet.svg?style=plasticr"></a>
    <a href="https://icutool.cn"><img src="https://img.shields.io/badge/演示地址-icutool.cn-blueviolet.svg?style=plasticr"></a>
</div>

### icutool-swarm
### 功能介绍
支持多端消息同步，支持接入微信公众号,实现更加方便便捷的消息同步

### 架构介绍
微服务架构，采用nacos作为配置中心，netty用作异步websocket网络框架，使用minio进行对象存储。数据库使用mysql，缓存方面使用了多级缓存，采用caffeine作为一级缓存，redis作为二级缓存。采用Hibernate-validator接口校验框架，采用Hutool工具类库，采用lombok简化代码

### 演示
<img src="http://d.icutool.cn/icutool/icutool.gif" width="1080" />

### 后端技术

|         技术          | 说明                            | 官网                                                         |
|:-------------------:|-------------------------------| ------------------------------------------------------------ |
|     SpringBoot      | web开发必备框架                     | [https://spring.io/projects/spring-boot](https://spring.io/projects/spring-boot) |
|       MyBatis       | ORM框架                         | http://www.mybatis.org/mybatis-3/zh/index.html               |
|     MyBatisPlus     | 零sql，简化数据库操作，分页插件             | [https://baomidou.com/](https://baomidou.com/)               |
|        Redis        | 缓存加速，多数据结构支持业务功能              | [https://redis.io](https://redis.io)                         |
|      Caffeine       | 本地缓存                          | http://caffe.berkeleyvision.org/                             |
|        Nginx        | 负载均衡，https配置，websocket升级，ip频控 | [https://nginx.org](https://nginx.org)                       |
|       Docker        | 应用容器引擎                        | [https://www.docker.com](https://www.docker.com)             |
|         Jwt         | 用户登录，认证方案                     | [https://jwt.io](https://jwt.io)                             |
|       Lombok        | 简化代码                          | [https://projectlombok.org](https://projectlombok.org)       |
|       Hutool        | Java工具类库                      | https://github.com/looly/hutool                              |
|     Swagger-UI      | API文档生成工具                     | https://github.com/swagger-api/swagger-ui                    |
| Hibernate-validator | 接口校验框架                        | [hibernate.org/validator/](hibernate.org/validator/)         |
|        minio        | 自建对象存储                        | https://github.com/minio/minio                               |
|        nacos        | 注册中心                          | https://nacos.io/                               |
|        netty        | 网络应用程序框架                          | https://github.com/netty/netty                              |



