package com.boot.bucket;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@MapperScan("com.boot.bucket.mapper")
public class BucketApplication {

    public static void main(String[] args) {
        SpringApplication.run(BucketApplication.class, args);
    }
}
