package io.sofastack.cloud.mq.kafka.consumer;

import io.sofastack.cloud.common.constants.KafkaConstants;
import io.sofastack.cloud.common.model.UserDetails;
import io.sofastack.cloud.common.response.Result;
import io.sofastack.cloud.mq.kafka.service.MailService;
import io.sofastack.cloud.mq.kafka.service.UserService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author: guolei.sgl (guolei.sgl@antfin.com) 2019/3/5 2:32 PM
 * @since:
 **/
@Component
public class KafkaReceiver {

    private static final Logger LOGGER  = LoggerFactory.getLogger(KafkaReceiver.class);
    private static final String SUCCESS = "true";
    private static final String COLON   = ":";

    @Autowired
    private MailService         mailService;
    @Autowired
    private UserService         userService;

    @KafkaListener(topics = {KafkaConstants.TRADE_TOPIC}, groupId = "test-consumer-group")
    public void listen(ConsumerRecord<?, ?> record) {
        LOGGER.info("Invoker kafka listen to consumer.topic {},{}", record.topic(), record.value());
        try {
            Optional<?> kafkaMessage = Optional.ofNullable(record.value());
            if (kafkaMessage.isPresent()) {
                String cmd = kafkaMessage.get().toString();
                if (SUCCESS.equals(cmd.split(COLON)[0])) {
                    Result<UserDetails> result = userService.queryUserDetail(Integer.valueOf(cmd
                        .split(COLON)[1]));
                    if (result.isSuccess()) {
                        mailService.sendTextMail(result.getData().getEmail(), "转账成功",
                            "samples 测试转账成功");
                    }
                    LOGGER.info("Success to consumer kafka message. cmd = {}", cmd);
                } else {
                    LOGGER.error("Failed to consumer kafka message. message type {}", cmd);
                }
            }
        } catch (Exception e) {
            LOGGER.error("Failed to execute consumer kafka message", e);
        }
    }
}
