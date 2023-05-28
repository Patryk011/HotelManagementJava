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
    public CustomerDTO updateCustomer(Long customerId, CustomerDTO customerDTO) {
        Customer existingCustomer = customerRepository.findById(customerId)
                .orElseThrow(() -> new NoSuchElementException("Customer with ID " + customerId + " not found."));



        existingCustomer = customerMapper.mapFromDto(existingCustomer, customerDTO);
        customerRepository.save(existingCustomer);
        return customerMapper.mapToDto(existingCustomer);
    }
    @Override
    public CustomerDTO addCustomer(CustomerDTO customerDTO) {
        Customer customer = customerMapper.mapFromDto(customerDTO);
        customer = customerRepository.save(customer);

        return customerMapper.mapToDto(customer);
    }

    @Override
    public CustomerDTO findById(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new NoSuchElementException());
        return customerMapper.mapToDto(customer);
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}
