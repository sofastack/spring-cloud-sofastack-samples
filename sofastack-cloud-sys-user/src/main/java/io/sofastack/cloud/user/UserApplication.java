package io.sofastack.cloud.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author: guolei.sgl (guolei.sgl@antfin.com) 2019/3/5 5:42 PM
 * @since:
 **/
@SpringBootApplication
@EntityScan(basePackages = { "io.sofastack.cloud.common.model" })
@EnableJpaRepositories(basePackages = { "io.sofastack.cloud.user.dao" })
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}
