package io.sofastack.cloud.core.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;

/**
 * @author: guolei.sgl (guolei.sgl@antfin.com) 2019/3/5 1:55 PM
 * @since:
 **/
public class KafkaSender {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaSender.class);

    private KafkaTemplate<String, String> kafkaTemplate;

    public KafkaSender(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    /**
     * send message
     */
    public void sendChannelMessage(String channel, String message) {
        LOGGER.info("kafka sender ready to send msg in topic of [" + channel + "], message = "
                + message);
        kafkaTemplate.send(channel, message);
    }
}
