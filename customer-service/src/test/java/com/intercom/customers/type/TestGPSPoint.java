package com.intercom.customers.type;

import com.intercom.customers.config.TestConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.fail;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( classes = { TestConfig.class } )
public class TestGPSPoint {

    @Autowired
    GPSPoint intercomDublin;

    @Test
    public void returnsExpectedDistanceWhenPassingGPSPoint() {
        double distance = intercomDublin.calculateDistance(new GPSPoint(52.833502, -8.522366));
        Assert.assertEquals (161.362, distance, 0.005);
    }

    @Test
    public void returnsExpectedDistanceWhenPassingDoubleValues() {
        double distance = intercomDublin.calculateDistance(52.833502, -8.522366);
        Assert.assertEquals (161.362, distance, 0.005);
    }

    @Test
    public void throwsNullPointerExceptionWhenGPSPointIsNull() {
        try {
            intercomDublin.calculateDistance(null);
            fail("Must throw NullPointerException");
        } catch (Exception ex) {
            // Test Passed
        }
    }
}
