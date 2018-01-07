package com.intercom.customers.repository;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.json.JsonReadContext;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.intercom.customers.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class IntercomCustomerRepository implements CustomerRepository {

    Logger logger = LoggerFactory.getLogger(IntercomCustomerRepository.class);

    File customerFile;
    ObjectMapper objectMapper;

    @Autowired
    public IntercomCustomerRepository (File customerFile, ObjectMapper objectMapper) {
        this.customerFile = customerFile;
        this.objectMapper = objectMapper;
    }

    @Override
    public List<Customer> findAll() throws Exception {
        logger.info(String.format("executing %s.findAll", this.getClass().getName()));
        return objectMapper.readValue(customerFile, new TypeReference<List<Customer>>(){});
    }

    @Override
    public Customer findOne(int customerId) throws Exception {
        logger.info(String.format("executing %s.findOne", this.getClass().getName()));
        return findAll()
                .stream()
                .filter(customer -> customer.getUserId() == customerId)
                .findFirst()
                .get();
    }
}
