package com.intercom.customers.api;

import com.intercom.customers.config.TestConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestCustomerController {

    private MockMvc mockMvc;

    @Autowired
    WebApplicationContext wac;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }


    @Test
    public void getsNearbyCustomers() throws Exception {
        this.mockMvc.perform(get("/customers")
                            .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                    .andExpect(jsonPath("[0].name").value("Ian Kehoe"));
    }

    @Test
    public void getsCustomerByUserId() throws Exception {
        this.mockMvc.perform(get("/customers/25")
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.name").value("David Behan"))
                .andExpect(jsonPath("$.distance").value("161.362 km"));
    }

    @Test
    public void returns404ErrorMessage() throws Exception {
        this.mockMvc.perform(get("/customers/444")
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.status").value("404"))
                .andExpect(jsonPath("$.message").value("No value present"));
    }

    @Test
    public void returnsErrorMessage() throws Exception {
        this.mockMvc.perform(get("/customers/blabla")
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.status").value("404"));
    }
}
