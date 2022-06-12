package org.carlos.bluetree.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(
        scanBasePackages = {
                "org.carlos.bluetree.customer",
                "org.carlos.bluetree.amqp"
        }
)
@EnableEurekaClient
@EnableFeignClients(
        basePackages = "org.carlos.bluetree.client"
)
public class CustomerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerApplication.class, args);
    }

}
