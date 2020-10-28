package com.fb.xujimanage.common.task;


import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executors;

/**
 * @author xiawei
 * @version 1.0
 * @date 2020/9/1 15:37
 * @description:多线程执行定时任务
 */
@Configuration
public class ScheduleConfig implements SchedulingConfigurer {

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
            //设定一个长度10的定时任务线程池
            taskRegistrar.setScheduler(Executors.newScheduledThreadPool(20));
    }
}
