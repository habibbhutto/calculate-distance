package com.intercom.customers.service;

import com.intercom.customers.model.result.CustomerContract;

import java.util.List;

public interface CustomerService {
    List<CustomerContract> getNearbyCustomers() throws Exception;
    CustomerContract getCustomer(int id) throws Exception;
}
