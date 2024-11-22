package com.trilight.ocr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();

        // // 获取默认的消息转换器
        // List<HttpMessageConverter<?>> messageConverters = restTemplate.getMessageConverters();
        //
        // // 配置 Jackson 转换器
        // MappingJackson2HttpMessageConverter jacksonConverter = new MappingJackson2HttpMessageConverter();
        // messageConverters.add(0, jacksonConverter); // 将 Jackson 转换器放在优先位置
        //
        // restTemplate.setMessageConverters(messageConverters);
        return restTemplate;
    }
}