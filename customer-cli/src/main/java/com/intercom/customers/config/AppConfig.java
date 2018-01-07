package com.intercom.customers.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.intercom.customers.type.GPSPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Configuration
public class AppConfig {

    @Bean
    public File customerFile() {
        return new File(getClass().getClassLoader().getResource("data/customers.json").getFile());
    }

    @Bean
    public static GPSPoint intercomDublinLocation() {
        return new GPSPoint(53.339428, -6.257664);
    }

    @Bean
    public static ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
