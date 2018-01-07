package com.intercom.customers.service;

import com.intercom.customers.model.Customer;
import com.intercom.customers.model.result.CustomerContract;
import com.intercom.customers.repository.CustomerRepository;
import com.intercom.customers.type.GPSPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.intercom.customers.model.mapper.CustomerMapper.customerToCustomerContract;

@Service
public class IntercomCustomerService implements CustomerService {

    Logger logger = LoggerFactory.getLogger(IntercomCustomerService.class);

    CustomerRepository customerRepository;
    GPSPoint intercomDublin;

    @Autowired
    public IntercomCustomerService(CustomerRepository customerRepository, GPSPoint intercomDublin) {
        this.customerRepository = customerRepository;
        this.intercomDublin = intercomDublin;
    }

    @Override
    public List<CustomerContract> getNearbyCustomers() throws Exception {
        logger.info(String.format("executing %s.getNearbyCustomers", this.getClass().getName()));
        List<Customer> customers = customerRepository.findAll();

        customers = customers
                .stream()
                .filter(customer -> intercomDublin.calculateDistance(customer.getLatitude(), customer.getLongitude()) <= 100)
                .collect(Collectors.toList());

        return customerToCustomerContract(customers, intercomDublin)
                .stream()
                .sorted(Comparator.comparing(CustomerContract::getUserId))
                .collect(Collectors.toList());
    }

    @Override
    public CustomerContract getCustomer(int id) throws Exception {
        logger.info(String.format("executing %s.getNearbyCustomers", this.getClass().getName()));
        Customer customer = customerRepository.findOne(id);
        return customerToCustomerContract(customer, intercomDublin);
    }
}
