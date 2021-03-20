package com.wx.diarywar.config.datasource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.wx.diarywar.dao",sqlSessionTemplateRef = "diarySqlSessionTemplate")
public class DataSourceDiaryConfig {
    @Bean("diarySqlSessionFactory")
    @Primary
    public SqlSessionFactory diarySqlSessionFactory(@Qualifier ("diary") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("classpath:mapper/*.xml");
        bean.setMapperLocations(resources);
        return bean.getObject();
    }
    @Bean("diaryTransactionManager")
    @Primary
    public DataSourceTransactionManager diaryDataSourceTransactionManager(@Qualifier ("diary") DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }
    @Bean("diarySqlSessionTemplate")
    @Primary
    public SqlSessionTemplate diarySqlSessionTemplate(@Qualifier ("diarySqlSessionFactory") SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
