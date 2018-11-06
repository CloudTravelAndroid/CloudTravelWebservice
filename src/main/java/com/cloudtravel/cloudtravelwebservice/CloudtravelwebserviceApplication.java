package com.cloudtravel.cloudtravelwebservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@MapperScan("com.cloudtravel.cloudtravelwebservice.Mapper")
public class CloudtravelwebserviceApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(CloudtravelwebserviceApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(CloudtravelwebserviceApplication.class);
    }

}
