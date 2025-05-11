package com.arvin.it.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class LogTableInit {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogTableInit.class);

    @Value("${search.log.table.num:5}")
    private int num;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 每天凌晨初始化表
     */
    //@Scheduled(cron = "${search.log.table.create.cron:0 * * * * ?}")
    public void logTableInit() {
        // 创建表
        for (int i = 0; i < num; i++) {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            String date = LocalDate.now().format(dateTimeFormatter);
            String tableName = "t_log_" + date + "_" + i;
            String sql = "CREATE TABLE " + tableName + " LIKE `common-code`.t_log";
            try {
                jdbcTemplate.execute(sql);
            } catch (Exception exception) {
                LOGGER.error("create table init error", exception);
            }

            String logDetailTableName = "t_log_detail_" + date + "_" + i;
            String logDetailSql = "CREATE TABLE " + logDetailTableName + " LIKE `common-code`.t_log_detail";
            try {
                jdbcTemplate.execute(logDetailSql);
            } catch (Exception exception) {
                LOGGER.error("create table init error", exception);
            }
        }

        // 清理数据
        /*for (int i = 0; i < num; i++) {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            String date = LocalDate.now().format(dateTimeFormatter);
            String tableName = "t_log_" + date + "_" + i;
            String sql = "CREATE TABLE " + tableName + " LIKE `common-code`.t_log";
            try {
                jdbcTemplate.execute(sql);
            } catch (Exception exception) {
                LOGGER.error("create table init error", exception);
            }

            String logDetailTableName = "t_log_detail_" + date + "_" + i;
            String logDetailSql = "CREATE TABLE " + logDetailTableName + " LIKE `common-code`.t_log_detail";
            try {
                jdbcTemplate.execute(logDetailSql);
            } catch (Exception exception) {
                LOGGER.error("create table init error", exception);
            }
        }*/
    }
}
