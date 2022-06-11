package com.example.customer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
class TestConfiguration {

    @Bean
    @Primary
    DataSource e2eTestDataSource(){
        return null;
    }
}
