package com.example.warehouse.config;

import com.example.warehouse.filter.LoginCheckFilter;
import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 原生Servlet配置类
 */
@Configuration
public class ServletConfig {

    /**
     * 配置FilterRegistrationBean的bean对象 -- 注册原生Servlet中的过滤器
     */
    @Bean
    public FilterRegistrationBean<Filter> filterRegistrationBean() {
        // 创建FilterRegistrationBean的bean对象
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        // 创建自定义过滤器
        LoginCheckFilter loginCheckFilter = new LoginCheckFilter();
        // 将自定义过滤器注册到FilterRegistrationBean中
        filterRegistrationBean.setFilter(loginCheckFilter);
        // 给过滤器指定拦截请求
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }
}
