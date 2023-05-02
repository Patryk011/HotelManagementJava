package pl.project.services;

import pl.project.entity.Customer;

import java.util.List;

public interface CustomerService {


    List<Customer> findAllCustomers();

    Customer findByEmailAddress(String emailAddress);

    Customer addCustomer(Customer customer);
    Customer findById(Long id);

    void deleteCustomer(Long id);


}
