package esg.csvloads.demo.repositories;

import esg.csvloads.demo.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    List<Customer> findByCustomerRef(Long customerRef);

    List<Customer> findByCountry(String country);

    List<Customer> findByPostcode(String postcode);

}
