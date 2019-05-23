# Samples of Spring Cloud & SOFAStack 

spring-cloud-sofastack-samples 是基于 SOFAStack 和 SpringCloud 构建的一套微服务系统，希望通过此案例工程，能够用于提供一个完整的基于 SOFA 和 SpringCloud 体系构建的基础的工程模型，帮助用于更好的理解和使用 SOFAStack 开源生态提供的一系列基础框架和组件。

本案例工程主要包括以下几个核心应用：

* sofastack-cloud-api-gateway：服务网关系统，所有的业务请求都会经过此系统，主要包括限流、鉴权功能
* sofastack-cloud-biz-web：核心业务系统，用于处理经过网关路由过来的请求，然后再由此系统进行业务功能处理
* sofastack-cloud-sys-auth：注册、登录、权限认证系统
* sofastack-cloud-sys-user：用户中心系统，用于提供用户相关服务
* sofastack-cloud-sys-accounting：用户账户系统，用于提供账户相关服务和定时生成账单
* sofastack-cloud-sys-trading：交易系统，用于处理用户交易逻辑、订单处理等
* sofastack-cloud-sys-mq-server：kafka 消息消费端应用，用于消费消息并且发送邮件
* sofastack-cloud-sys-static：静态资源系统

## 应用架构图

![image](https://gw.alipayobjects.com/mdn/rms_5597ae/afts/img/A*4amCQq0FOY4AAAAAAAAAAABjARQnAQ)

## 快速开始

```bash
git clone https://github.com/sofastack/spring-cloud-sofastack-samples.git
cd spring-cloud-sofastack-samples/script/bin && sh deploy.sh
```
运行示例参考：[操作手册](https://github.com/sofastack/spring-cloud-sofastack-samples/wiki/%E5%B7%A5%E7%A8%8B%E8%BF%90%E8%A1%8C#%E6%93%8D%E4%BD%9C%E7%A4%BA%E4%BE%8B)

## 工程共建

为了能够让更多对 SOFAStack 和 SpringCloud 体系有兴趣的同学参与，本案例工程希望通过内外部共建的方式进行开发。欢迎各路大神基于此工程，使用 SOFAStack 和 SpringCloud 大展身手。

* 如果您对此工程或者技术栈感兴趣，欢迎  Star & Watch
* 如果您希望参与此工程开发共建，欢迎 Fork & PR

