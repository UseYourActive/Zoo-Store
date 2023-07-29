package com.example.zoostore.rest.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
    @Bean //vzima stoinostta ot return i otiva v konteinera na Spring
    public ObjectMapper getObjectMapper(){
        ObjectMapper objectMapper = new ObjectMapper();
        //objectMapper.findAndRegisterModules();
        return objectMapper;
    }

//    @Bean
//    public ModelMapper modelMapper(){
//        return new ModelMapper();
//    }

    @Bean
    @Qualifier(value = "myRestTemplateBean")
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
