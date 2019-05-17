package io.sofastack.cloud.accounting.configuration;

import io.sofastack.cloud.accounting.jobs.OrderJob;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 定时任务，Quartz Scheduler ; 生成每日账单
 *
 * @author: guolei.sgl (guolei.sgl@antfin.com) 2019/3/5 3:45 PM
 * @since:
 **/
@Configuration
public class DailyOrderSchedulerConfiguration {

    @Bean
    public JobDetail sampleJobDetail() {
        return JobBuilder.newJob(OrderJob.class).withIdentity("orderJob")
            .usingJobData("name", "dailyOrder").storeDurably().build();
    }

    @Bean
    public Trigger sampleJobTrigger() {
        // 每天凌晨2点开始生成每日账单
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0 0 2 * * ?");
        return TriggerBuilder.newTrigger().forJob(sampleJobDetail()).withIdentity("orderTrigger")
            .withSchedule(scheduleBuilder).build();
    }
}
