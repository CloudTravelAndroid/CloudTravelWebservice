package com.cloudtravel.cloudtravelwebservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.cloudtravel.cloudtravelwebservice.Mapper")
public class CloudtravelwebserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudtravelwebserviceApplication.class, args);
    }
}
