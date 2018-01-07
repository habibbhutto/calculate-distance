package com.intercom.customers.service;

import com.intercom.customers.config.TestConfig;
import com.intercom.customers.model.result.CustomerContract;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.fail;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( classes = { TestConfig.class } )
public class TestIntercomCustomerService {

    @Autowired
    CustomerService customerService;

    @Test
    public void findsNearbyCustomersWithin100Km() throws Exception {
        List<CustomerContract> customerContracts = customerService.getNearbyCustomers();

        Assert.assertNotNull(customerContracts);
        Assert.assertEquals(1, customerContracts.size());

        CustomerContract customer = customerContracts.stream().findFirst().get();
        Assert.assertEquals(4, customer.getUserId());
        Assert.assertEquals("Ian Kehoe", customer.getName());
        Assert.assertEquals("10.567 km", customer.getDistance());
    }

    @Test
    public void findsOneCustomer() throws Exception {
        CustomerContract customer = customerService.getCustomer(25);

        Assert.assertNotNull(customer);
        Assert.assertEquals(25, customer.getUserId());
        Assert.assertEquals("David Behan", customer.getName());
        Assert.assertEquals("161.362 km", customer.getDistance());
    }

    @Test
    public void throwsExceptionIfDoesNotFindCustomer() {
        try {
            CustomerContract customer = customerService.getCustomer(255);
            fail("must throw exception if customer is not found");
        } catch (Exception ex) {
            // test passed
        }
    }
}
