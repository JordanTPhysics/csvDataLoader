package esg.csvloads.demo.controllers;

import esg.csvloads.demo.models.Customer;
import esg.csvloads.demo.repositories.CustomerRepository;
import esg.csvloads.demo.services.CustomerService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import esg.csvloads.demo.ReadCSV;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MainController {

    @Autowired
    private ReadCSV csvReader;

    @Autowired
    public CustomerService customerService;


    @GetMapping(value = "/my-page", produces = MediaType.TEXT_HTML_VALUE)
    public Resource getStaticHtml() {
        // Load the HTML file from the classpath
        return new ClassPathResource("/templates/index.html");
    }

    @PostMapping("/upload-csv")
    public ResponseEntity<List<Customer>> createCustomers(@RequestBody String filePathToCSV) {

        List<JSONObject> customers = csvReader.getJSONFromCSV(filePathToCSV);
        List<Customer> savedCustomers = customerService.saveCustomersFromJson(customers);
        return new ResponseEntity<>(savedCustomers, HttpStatus.CREATED);
    }

    @GetMapping("/customers")
    public List<Customer> getAllCustomers(){

        return customerService.getAllCustomers();
    }
    @GetMapping("/customer")
    public List<Customer> getCustomerByRef(@RequestParam("refId") Long ref){
        return customerService.findCustomerByRef(ref);
    }
    @GetMapping("/countryRef")
    public List<Customer> getCustomersByCountry(@RequestParam("country") String country){
        return customerService.findCustomersByCountry(country);
    }

    @GetMapping("/postcode")
    public List<Customer> getCustomersByPostcode(@RequestParam("postcode") String postcode){
        return customerService.findCustomersByPostcode(postcode);
    }
}

