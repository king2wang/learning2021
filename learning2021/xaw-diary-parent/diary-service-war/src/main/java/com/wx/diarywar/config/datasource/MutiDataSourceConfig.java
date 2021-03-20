package com.wx.diarywar.config.datasource;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class MutiDataSourceConfig {
    @Primary
    @Bean("diary")
    @ConfigurationProperties("spring.datasource.druid.diary")
    public DataSource dataSourceDiary(){
        return DruidDataSourceBuilder.create().build();
    }
}
