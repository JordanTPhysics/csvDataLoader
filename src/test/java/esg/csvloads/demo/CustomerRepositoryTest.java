package esg.csvloads.demo;

import esg.csvloads.demo.models.Customer;
import esg.csvloads.demo.repositories.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void testCRUDOperations() {
        // Create
        Customer customer = new Customer();
        customer.setCustomerName("John Doe");
        customer.setAddress1("Whartle Brow");
        customer.setAddress2("Whartle Green");
        customer.setTown("Kenslington");
        customer.setCounty("Place");
        customer.setCountry("West Cornwall");
        customer.setPostcode("johndoe@example.com");
        customerRepository.save(customer);

        // Read
        Optional<Customer> retrievedCustomer = customerRepository.findById(customer.getCustomerRef());
        assertTrue(retrievedCustomer.isPresent());
        assertEquals("John Doe", retrievedCustomer.get().getCustomerName());

        // Update
        customer.setCustomerName("Jane Smith");
        customerRepository.save(customer);

        // Verify the update
        Optional<Customer> updatedCustomer = customerRepository.findById(customer.getCustomerRef());
        assertTrue(updatedCustomer.isPresent());
        assertEquals("Jane Smith", updatedCustomer.get().getCustomerName());

        // Delete
        customerRepository.delete(customer);

        // Verify deletion
        Optional<Customer> deletedCustomer = customerRepository.findById(customer.getCustomerRef());
        assertFalse(deletedCustomer.isPresent());
    }
    @Test
    public void testCsvUploadWorks(){

    }
}

