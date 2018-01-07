package com.intercom.customers.repository;

import com.intercom.customers.model.Customer;

import java.util.List;

public interface CustomerRepository {
    List<Customer> findAll() throws Exception;
    Customer findOne(int customerId) throws Exception;
}
