package com.arvin.it.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("course") // 逻辑表
public class Course {

    @TableId
    private Long id;

    @TableField("req_time")
    private LocalDateTime reqTime;

}
