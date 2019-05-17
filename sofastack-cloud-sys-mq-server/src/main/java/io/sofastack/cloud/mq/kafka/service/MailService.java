package io.sofastack.cloud.mq.kafka.service;

/**
 * @author: guolei.sgl (glmapper_2018@163.com) 2019/4/15 7:51 PM
 * @since:
 **/
public interface MailService {
    /**
     * 发送纯文本邮件
     *
     * @param toAddr  发送给谁
     * @param title   标题
     * @param content 内容
     */
    void sendTextMail(String toAddr, String title, String content);
}
