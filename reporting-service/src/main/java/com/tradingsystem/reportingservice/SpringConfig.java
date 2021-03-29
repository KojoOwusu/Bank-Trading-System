package com.tradingsystem.reportingservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
@EnableAsync
public class SpringConfig {

    @Autowired
    private ScheduledOrderStatus orderStatusChecker;

   @Scheduled(fixedRate = 60000)
   @Async
   public void run(){
          //  orderStatusChecker.run();
   }
}
