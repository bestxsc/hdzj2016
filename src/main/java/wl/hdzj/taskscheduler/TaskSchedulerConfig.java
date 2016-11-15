package wl.hdzj.taskscheduler;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 计划任务配置类
 * @author lipengbiao
 */
@Configuration
@ComponentScan("wl.hdzj.taskscheduler")
@EnableScheduling
public class TaskSchedulerConfig {
    //TODO 出现 No TaskScheduler/ScheduledExecutorService bean found for scheduled processing
}
