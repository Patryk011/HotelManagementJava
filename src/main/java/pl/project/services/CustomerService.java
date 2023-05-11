package pl.project.services;

import org.springframework.stereotype.Service;
import pl.project.dto.CustomerDTO;
import pl.project.entity.Customer;

import java.util.List;


public interface CustomerService {


    List<CustomerDTO> findAllCustomers();


    Customer addCustomer(Customer customer);
    Customer findById(Long id);

    void deleteCustomer(Long id);


}
