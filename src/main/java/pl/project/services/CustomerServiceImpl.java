package pl.project.services;

import org.springframework.stereotype.Service;
import pl.project.dto.CustomerDTO;
import pl.project.entity.Customer;
import pl.project.mapper.CustomerMapper;
import pl.project.repository.CustomerRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CustomerServiceImpl implements CustomerService {



    private final CustomerRepository customerRepository;

    private final CustomerMapper customerMapper;


    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public List<CustomerDTO> findAllCustomers() {
        return customerMapper.mapToDto(customerRepository.findAll());
    }



    @Override
    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer findById(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new NoSuchElementException());
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}
