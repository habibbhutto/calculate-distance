package com.intercom.customers.api;

import com.intercom.customers.model.result.CustomerContract;
import com.intercom.customers.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping(path = "/customers")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<CustomerContract>> getNearbyCustomers( ) throws Exception {
        return new ResponseEntity<>(
                customerService.getNearbyCustomers(),
                HttpStatus.OK);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<CustomerContract> getCustomer(@PathVariable("id") int id) throws Exception {
        return new ResponseEntity<>(
                customerService.getCustomer(id),
                HttpStatus.OK);
    }
}
