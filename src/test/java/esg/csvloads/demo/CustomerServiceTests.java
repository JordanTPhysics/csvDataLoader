package esg.csvloads.demo;


import com.fasterxml.jackson.databind.ObjectMapper;
import esg.csvloads.demo.models.Customer;
import esg.csvloads.demo.services.CustomerService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CustomerServiceTests {
    @Autowired
    CustomerService service;

    @Test
    public void findCustomersByCountryTest(){
        List<Customer> customers = service.findCustomersByCountry("USA");
        for(Customer customer : customers){
            Assert.assertEquals(
                    "The customer: " + customer.getCustomerName() +"is from: " + customer.getCountry(),
                    "USA",
                    customer.getCountry());
        }
    }



}
