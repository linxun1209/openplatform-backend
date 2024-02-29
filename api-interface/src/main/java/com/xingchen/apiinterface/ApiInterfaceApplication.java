package com.xingchen.apiinterface;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author xing'chen
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class ApiInterfaceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiInterfaceApplication.class, args);
    }

}
