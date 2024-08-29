package org.aom.discount_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author : Abhishek
 * @since : 2024-08-29, Thursday
 **/
@Configuration
public class RestTemplageConfigurer {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
