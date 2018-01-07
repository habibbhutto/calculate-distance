package com.intercom.customers.repository;

import com.intercom.customers.config.TestConfig;
import com.intercom.customers.model.Customer;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.fail;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( classes = { TestConfig.class } )
public class IntercomCustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;

    @Test
    public void findsAll() throws Exception {
        List<Customer> customers = customerRepository.findAll();
        Assert.assertNotNull(customers);
        Assert.assertTrue(customers.size() > 0);
    }

    @Test
    public void findsOne() throws Exception {
        Customer customer = customerRepository.findOne(4);
        Assert.assertNotNull(customer);
        Assert.assertEquals(4, customer.getUserId());
        Assert.assertEquals("Ian Kehoe", customer.getName());

    }

    @Test
    public void throwsElementNotFoundExceptionIfCustomerNotFound() {
        try {
            customerRepository.findOne(444);
            fail("must throw exception if customer not found");
        } catch (Exception ex) {
            // test passed
        }
    }
}
