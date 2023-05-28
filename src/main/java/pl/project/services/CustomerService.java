package pl.project.services;

import org.springframework.stereotype.Service;
import pl.project.dto.CustomerDTO;
import pl.project.entity.Customer;

import java.util.List;


public interface CustomerService {


    List<CustomerDTO> findAllCustomers();


    CustomerDTO addCustomer(CustomerDTO customer);
    CustomerDTO findById(Long id);

    CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO);

    void deleteCustomer(Long id);


}
