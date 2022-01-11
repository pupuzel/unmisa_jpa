package com.jock.unmisa.config;

import java.net.http.HttpClient;
import java.net.http.HttpClient.Redirect;
import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class RestConfig {

    @Bean
    public ObjectMapper objectMapper () {
    	var mapper = new ObjectMapper();
    	mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper;
    }


    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter (ObjectMapper objectMapper) {
        var converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(objectMapper);
        converter.setPrettyPrint(true);

        return converter;
    }
    
    @Bean
    public HttpClient httpClient() {
        ExecutorService executorService = new ThreadPoolExecutor(0, 20, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
        return HttpClient.newBuilder()
                .executor(executorService)
                .connectTimeout(Duration.ofSeconds(5))
                .build();
    }
}
