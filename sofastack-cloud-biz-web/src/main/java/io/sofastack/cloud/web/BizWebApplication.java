package io.sofastack.cloud.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author: guolei.sgl (guolei.sgl@antfin.com) 19/1/26 下午4:19
 * @since:
 **/
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class BizWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(BizWebApplication.class, args);
    }
}
