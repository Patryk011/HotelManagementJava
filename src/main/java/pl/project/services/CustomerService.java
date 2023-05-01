package pl.project.services;

import pl.project.entity.Customer;

import java.util.List;

public interface CustomerService {


    List<Customer> findAllCustomers();


    Customer findById(Long id);

    void deleteCustomer(Long id);


}
