package com.intercom.customers.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.intercom.customers.repository.CustomerRepository;
import com.intercom.customers.repository.IntercomCustomerRepository;
import com.intercom.customers.service.CustomerService;
import com.intercom.customers.service.IntercomCustomerService;
import com.intercom.customers.type.GPSPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Configuration
public class TestConfig {
    @Bean
    public File customerFile() {
        return new File(getClass().getClassLoader().getResource("data/customers.json").getFile());
    }

    @Bean
    public GPSPoint intercomDublinLocation() {
        return new GPSPoint(53.339428, -6.257664);
    }

    @Bean ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public CustomerRepository customerRepository(File customerFile, ObjectMapper objectMapper) {
        return new IntercomCustomerRepository(customerFile, objectMapper);
    }

    @Bean
    public CustomerService customerService(CustomerRepository customerRepository, GPSPoint intercomDublin) {
        return new IntercomCustomerService(customerRepository, intercomDublin);
    }
}
