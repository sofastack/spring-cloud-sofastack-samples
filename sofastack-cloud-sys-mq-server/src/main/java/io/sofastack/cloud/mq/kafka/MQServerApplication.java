package io.sofastack.cloud.mq.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.PropertySource;

/**
 * @author: guolei.sgl (guolei.sgl@antfin.com) 2019/3/12 7:29 PM
 * @since:
 **/
@SpringBootApplication
@PropertySource("classpath:application.properties")
@EnableDiscoveryClient
@EnableFeignClients
public class MQServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(MQServerApplication.class, args);
    }
}
