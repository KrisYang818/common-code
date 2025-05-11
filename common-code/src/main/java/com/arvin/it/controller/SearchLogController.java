package com.arvin.it.controller;

import com.arvin.it.message.KafkaProducer;
import com.arvin.it.vo.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/log")
public class SearchLogController extends BaseController {

    @Autowired
    private KafkaProducer kafkaProducer;

    @PostMapping("/search/submit")
    public AjaxResult submit() {
        String topic = "test";
        String message = "message-" + new Random().nextInt();
        kafkaProducer.sendMessage(topic, message);
        return success();
    }
}
