package com.arvin.it.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties;

@Configuration
public class KafkaConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConfig.class);

//    @Bean
//    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> kafkaListenerContainerFactory() {
//        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(consumerFactory());
//        // 并发数 多个微服务实例会均分
//        factory.setConcurrency(1);
//        factory.setBatchListener(true);
//        ContainerProperties containerProperties = factory.getContainerProperties();
//        // 是否设置手动提交
//        containerProperties.setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);
//        return factory;
//    }

}
