package pl.project.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pl.project.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {


    Customer findByEmailAddress(String emailAddress);
}
