package esg.csvloads.demo.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import esg.csvloads.demo.models.Customer;
import esg.csvloads.demo.repositories.CustomerRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private final CustomerRepository repository;

    private final ObjectMapper objectMapper;

  public CustomerService(CustomerRepository repository, ObjectMapper objectMapper) {
      this.repository = repository;
      this.objectMapper = objectMapper;
  }

    public List<Customer> getAllCustomers() {
        return (List<Customer>) repository.findAll();
    }

    public Optional<Customer> getCustomerById(@PathVariable Long customerId) {
        Optional<Customer> customer = repository.findById(customerId);
        if (customer != null) {
            return new ResponseEntity<>(customer, HttpStatus.OK).getBody();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found with id " + customerId);
        }
    }

    public List<Customer> findCustomerByRef(Long ref){
      return repository.findByCustomerRef(ref);
    }
    public Customer createCustomer(Customer customer) {
        return repository.save(customer);
    }


    public List<Customer> findCustomersByPostcode(String postcode){
      return repository.findByPostcode(postcode);
    }
    public List<Customer> findCustomersByCountry(String country){
      return repository.findByCountry(country);
    }
    public List<Customer> saveCustomersFromJson(List<JSONObject> jsonList) {
      try {
          List<Customer> customers = new ArrayList<>();
          for(JSONObject jObj : jsonList){
                    Customer customer = objectMapper.readValue(jObj.toString(), Customer.class);
                    customers.add(customer);
                    System.out.println(customer.toString());
                }

                // Save the Customer entity using the repository
                return (List<Customer>) repository.saveAll(customers);
            } catch (Exception e) {
                e.printStackTrace();
                // Handle any exceptions appropriately
                return null;
            }
        }
    }


