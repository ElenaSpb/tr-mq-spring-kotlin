package com.example.collector.job

import com.example.collector.service.MetricsProducer
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class CollectScheduler (val metricsProducer: MetricsProducer) {

    @Scheduled(cron = "0/30 * * * * ?")
    fun collectBrokerStatRun() {
        metricsProducer.sendBrokerStatRequest()
    }
}