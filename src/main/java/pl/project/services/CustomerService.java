package pl.project.services;

import pl.project.entity.Customer;

import java.util.List;

public interface CustomerService {


    List<Customer> findAllCustomers();

    Customer addCustomer(Customer customer);
    Customer findById(Long id);

    void deleteCustomer(Long id);


}
