package com.arvin.it.utils;

import org.apache.shardingsphere.sharding.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.sharding.api.sharding.complex.ComplexKeysShardingValue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Collections;
import java.util.Properties;

public class TimeTraceComplexAlgorithm implements ComplexKeysShardingAlgorithm<Comparable<?>> {
    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, ComplexKeysShardingValue<Comparable<?>> shardingValue) {
        // 1. 提取分片键值
        // LocalDateTime createTime = (LocalDateTime) shardingValue.getColumnNameAndShardingValuesMap().get("create_time").iterator().next();
        String traceId = (String) shardingValue.getColumnNameAndShardingValuesMap().get("trace_id").iterator().next();
        
        // 2. 生成日期部分（当前系统日期）
        String datePart = LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE); // 输出如20250511
        
        // 3. 根据 traceId 哈希计算后缀（0-5）
        int suffix = Math.abs(traceId.hashCode() % 6);
        
        // 4. 组合完整表名
        String targetTable = "t_log_" + datePart + "_" + suffix;
        return Collections.singletonList(targetTable);
    }

    @Override
    public Properties getProps() {
        return null;
    }

    @Override
    public void init(Properties properties) {
    }
}