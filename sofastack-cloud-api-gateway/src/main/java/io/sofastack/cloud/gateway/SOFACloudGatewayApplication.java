package io.sofastack.cloud.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author: guolei.sgl (guolei.sgl@antfin.com) 19/1/26 下午3:01
 * @since:
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class SOFACloudGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(SOFACloudGatewayApplication.class, args);
    }
}
