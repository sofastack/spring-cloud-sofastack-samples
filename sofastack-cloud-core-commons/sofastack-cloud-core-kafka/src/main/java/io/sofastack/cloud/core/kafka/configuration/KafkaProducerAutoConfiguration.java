package io.sofastack.cloud.core.kafka.configuration;

import io.sofastack.cloud.core.kafka.KafkaSender;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

/**
 * @author: guolei.sgl (guolei.sgl@antfin.com) 2019/3/6 1:28 PM
 * @since:
 **/
@Configuration
public class KafkaProducerAutoConfiguration {

    @Bean
    public KafkaSender kafkaSender(KafkaTemplate<String, String> kafkaTemplate) {
        return new KafkaSender(kafkaTemplate);
    }
}
