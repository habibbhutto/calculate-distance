package com.intercom.customers.config;

import com.intercom.customers.type.GPSPoint;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestAppConfig {

    @Autowired
    File customerFile;

    @Autowired
    GPSPoint intercomDublin;

    @Test
    public void createsCustomerFileBean() {
        Assert.assertNotNull(customerFile);
    }

    @Test
    public void createsIntercomDublinGPSPointBean() {
        Assert.assertNotNull(intercomDublin);
    }
}
