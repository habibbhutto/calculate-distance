package com.intercom.customers.model.mapper;

import com.intercom.customers.model.Customer;
import com.intercom.customers.model.result.CustomerContract;
import com.intercom.customers.type.GPSPoint;

import java.util.List;
import java.util.stream.Collectors;

public class CustomerMapper {

    public static CustomerContract customerToCustomerContract(Customer customer, GPSPoint intercomDublin) {
        CustomerContract customerContract = new CustomerContract();
        customerContract.setUserId( customer.getUserId() );
        customerContract.setName( customer.getName() );
        double distance = intercomDublin.calculateDistance( customer.getLatitude(), customer.getLongitude() );
        customerContract.setDistance( String.format("%.3f km", distance) );
        return customerContract;
    }

    public static List<CustomerContract> customerToCustomerContract(List<Customer> customers, GPSPoint intercomDublin) {
        return customers.stream().map( customer -> {
            return customerToCustomerContract(customer, intercomDublin);
        }).collect(Collectors.toList());
    }
}
