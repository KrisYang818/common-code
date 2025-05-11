package com.arvin.it.message;

import com.arvin.it.entity.Course;
import com.arvin.it.entity.SearchLog;
import com.arvin.it.mapper.CourseMapper;
import com.arvin.it.mapper.LogMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class KafkaConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

    @Autowired
    private LogMapper logMapper;

    @Autowired
    private CourseMapper courseMapper;

    @KafkaListener(
        topics = "test",
        groupId = "my-group",
        containerFactory = "kafkaListenerContainerFactory"
    )
    public void consume(String message) { //Acknowledgment ack) {
        LOGGER.info("收到消息：{}", message);
        // 手动提交偏移量
        // ack.acknowledge();
        SearchLog log = new SearchLog();
        log.setId(System.currentTimeMillis());
        log.setMessage(message);
        log.setTraceId("traceId" + log.getId());
        log.setCreateTime(LocalDateTime.now());
        int insert = logMapper.insert(log);
        Course course = new Course();
        //course.setReqTime(LocalDateTime.now());
        //course.setId(insert);
       // int insert1 = courseMapper.insert(course);
    }
}