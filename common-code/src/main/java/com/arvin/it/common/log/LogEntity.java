package com.arvin.it.common.log;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LogEntity {

    private Long id;

    // 日志类型
    private String type;

    // 操作人
    private String operator;

    // 日志内容
    private String content;

    private LocalDateTime createTime = LocalDateTime.now();

}
