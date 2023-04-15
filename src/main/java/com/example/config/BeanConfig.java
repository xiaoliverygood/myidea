package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class BeanConfig {

//    @Bean("multipartResolver")   //注意这里Bean的名称是固定的，必须是multipartResolver
//    public CommonsMultipartResolver commonsMultipartResolver(){
//        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
//        resolver.setMaxUploadSize(1024 * 1024 * 10);   //最大10MB大小
//        resolver.setDefaultEncoding("UTF-8");   //默认编码格式
//        return resolver;
//    }


}
