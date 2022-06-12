package org.carlos.bluetree.customer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
class TestConfiguration {

    @Bean
    @Primary
    DataSource e2eTestDataSource(){
        return null;
    }
}
