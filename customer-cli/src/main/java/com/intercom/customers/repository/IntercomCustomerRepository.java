package com.intercom.customers.repository;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.json.JsonReadContext;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.intercom.customers.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class IntercomCustomerRepository implements CustomerRepository {

    File customerFile;
    ObjectMapper objectMapper;

    @Autowired
    public IntercomCustomerRepository (File customerFile, ObjectMapper objectMapper) {
        this.customerFile = customerFile;
        this.objectMapper = objectMapper;
    }

    @Override
    public List<Customer> findAll() throws Exception {
        return objectMapper.readValue(customerFile, new TypeReference<List<Customer>>(){});
    }

    @Override
    public Customer findOne(int customerId) throws Exception {
        return findAll()
                .stream()
                .filter(customer -> customer.getUserId() == customerId)
                .findFirst()
                .get();
    }
}
