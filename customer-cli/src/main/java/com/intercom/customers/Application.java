package com.intercom.customers;

import com.intercom.customers.model.result.CustomerContract;
import com.intercom.customers.service.CustomerService;
import com.intercom.customers.type.GPSPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

@SpringBootApplication
@ComponentScan
public class Application implements CommandLineRunner {

    @Autowired
    CustomerService customerService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {

        List<CustomerContract> customers = customerService.getNearbyCustomers();
        if( customers.size() > 0) {
            lineBreak(50);
            System.out.println(String.format("%d customers found within 100KM radius.", customers.size()));
            lineBreak(50);
            System.out.println(String.format("%-10s|%-25s|%-15s", "user-id", "name", "distance"));
            lineBreak(50);
            customers.forEach(customerContract -> {
                System.out.println(
                        String.format("%-10d|%-25s|%-15s",
                                customerContract.getUserId(),
                                customerContract.getName(),
                                customerContract.getDistance()));
            });
            lineBreak(50);
        } else {
            lineBreak(50);
            System.out.println("No customer lives in 100KM radius.");
            lineBreak(50);
        }
    }

    private void lineBreak(int size) {
        for( int i=0; i < size; i++) System.out.print('-');
        System.out.println();
    }
}
