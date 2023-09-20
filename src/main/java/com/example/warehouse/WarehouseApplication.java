package com.example.warehouse;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/*
 * mapper结构扫描器，指明mapper接口所在包，然后就会自动为mapper接口创建代理对象并加入到IOC容器
 */
@EnableCaching // 开启redis注解版缓存
@MapperScan(basePackages = "com.example.warehouse.mapper")
@SpringBootApplication
public class WarehouseApplication {
    public static void main(String[] args) {
        SpringApplication.run(WarehouseApplication.class, args);
    }
}
