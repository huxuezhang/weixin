package com.hxz.weixin.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * 包含web组件的bean,如控制器、视图解析器以及映射处理器
 *
 * @author By zhuyq
 * @version V1.0.0
 */
@Configuration
//@ComponentScan(basePackages = {
//        "com.teleware.appbuilder.controller",
//        "com.teleware.appbuilder.service.impl",
//        "com.teleware.appbuilder.service"
//})

public class WebMVCConfiguration implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET", "HEAD", "POST", "PUT", "PATCH", "DELETE", "OPTIONS", "TRACE");
    }

    /**
     * String转换器
     */
    @Bean
    public StringHttpMessageConverter stringHttpMessageConverter() {
        StringHttpMessageConverter httpMessageConverter = new StringHttpMessageConverter();
        httpMessageConverter.setSupportedMediaTypes(Arrays.asList(
                new MediaType("text", "plain", Charset.forName("utf-8")),
                new MediaType("application", "json", Charset.forName("utf-8")),
                new MediaType("text", "html", Charset.forName("utf-8"))
        ));
        httpMessageConverter.setDefaultCharset(Charset.forName("utf-8"));
        return httpMessageConverter;
    }

    @Override
    public void configurePathMatch(PathMatchConfigurer matcher) {
        matcher.setUseRegisteredSuffixPatternMatch(true);
    }
}
