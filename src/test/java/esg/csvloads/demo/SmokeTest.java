package esg.csvloads.demo;

import static org.assertj.core.api.Assertions.assertThat;

import esg.csvloads.demo.controllers.MainController;
import esg.csvloads.demo.services.CustomerService;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SmokeTest {

    @Autowired
    private MainController controller;

    @Autowired
    private CustomerService customerService;



    @Test
    public void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }

    @Test
    public void serviceLoads(){
        assertThat(customerService).isNotNull();
    }
}
