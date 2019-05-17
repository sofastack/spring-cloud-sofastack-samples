package io.sofastack.cloud.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author: guolei.sgl (guolei.sgl@antfin.com) 19/1/26 下午4:03
 * @since:
 **/
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class SOFAAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(SOFAAuthApplication.class, args);
    }
}
