package com.arvin.it.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("t_log")
public class SearchLog {

    @TableId
    private long id;

    @TableField("trace_id")
    private String traceId;

    @TableField("message")
    private String message;

    @TableField("create_time")
    private LocalDateTime createTime;

}
