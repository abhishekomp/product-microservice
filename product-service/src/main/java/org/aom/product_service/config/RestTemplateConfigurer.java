package org.aom.product_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author : Abhishek
 * @since : 2024-08-28, Wednesday
 **/
@Configuration
public class RestTemplateConfigurer {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
