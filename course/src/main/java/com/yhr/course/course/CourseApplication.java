package com.yhr.course.course;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;
import java.text.SimpleDateFormat;

@SpringBootApplication
public class CourseApplication {

    public static void main(String[] args) {
        SpringApplication.run(CourseApplication.class, args);
    }

    @Bean
    public HttpMessageConverters initJackson() {
        MappingJackson2HttpMessageConverter jacksonConverter = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        jacksonConverter.setObjectMapper(objectMapper);
        HttpMessageConverter<?> converter = jacksonConverter;
        return new HttpMessageConverters(converter);
    }

    /**
     *      * 文件上传配置
     *      * @return
     *      
     */
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //文件最大
        DataSize dataSize = DataSize.ofMegabytes(500L);//500MB
        factory.setMaxFileSize(dataSize); //KB,MB
        /// 设置总上传数据总大小
        factory.setMaxRequestSize(dataSize);
        return factory.createMultipartConfig();
    }
}
