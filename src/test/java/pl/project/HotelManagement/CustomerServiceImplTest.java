package pl.project.HotelManagement;
import pl.project.services.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.project.dto.CustomerDTO;
import pl.project.entity.Customer;
import pl.project.mapper.CustomerMapper;
import pl.project.repository.CustomerRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import static org.mockito.Mockito.*;

public class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    public CustomerMapper customerMapper;

    private CustomerServiceImpl customerService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        customerService = new CustomerServiceImpl(customerRepository, customerMapper);
    }

    @Test
    public void testFindAllCustomers() {
        // Given
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer());
        when(customerRepository.findAll()).thenReturn(customers);

        List<CustomerDTO> customerDTOs = new ArrayList<>();
        customerDTOs.add(new CustomerDTO());
        when(customerMapper.mapToDto(customers)).thenReturn(customerDTOs);

        // When
        List<CustomerDTO> result = customerService.findAllCustomers();

        // Then
        Assertions.assertEquals(1, result.size());
        verify(customerRepository, times(1)).findAll();
        verify(customerMapper, times(1)).mapToDto(customers);
    }

    @Test
    public void testUpdateCustomer() {
        // Given
        Long customerId = 1L;
        CustomerDTO customerDTO = new CustomerDTO();

        Customer existingCustomer = new Customer();
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(existingCustomer));

        when(customerMapper.mapFromDto(existingCustomer, customerDTO)).thenReturn(existingCustomer);
        when(customerRepository.save(existingCustomer)).thenReturn(existingCustomer);

        CustomerDTO mappedCustomerDTO = new CustomerDTO();
        when(customerMapper.mapToDto(existingCustomer)).thenReturn(mappedCustomerDTO);

        // When
        CustomerDTO result = customerService.updateCustomer(customerId, customerDTO);

        // Then
        Assertions.assertEquals(mappedCustomerDTO, result);
        verify(customerRepository, times(1)).findById(customerId);
        verify(customerRepository, times(1)).save(existingCustomer);
        verify(customerMapper, times(1)).mapFromDto(existingCustomer, customerDTO);
        verify(customerMapper, times(1)).mapToDto(existingCustomer);
    }

    @Test
    public void testAddCustomer() {
        // Given
        CustomerDTO customerDTO = new CustomerDTO();

        Customer customer = new Customer();
        when(customerMapper.mapFromDto(customerDTO)).thenReturn(customer);
        when(customerRepository.save(customer)).thenReturn(customer);

        CustomerDTO mappedCustomerDTO = new CustomerDTO();
        when(customerMapper.mapToDto(customer)).thenReturn(mappedCustomerDTO);

        // When
        CustomerDTO result = customerService.addCustomer(customerDTO);

        // Then
        Assertions.assertEquals(mappedCustomerDTO, result);
        verify(customerMapper, times(1)).mapFromDto(customerDTO);
        verify(customerRepository, times(1)).save(customer);
        verify(customerMapper, times(1)).mapToDto(customer);
    }

    @Test
    public void testFindById() {
        // Given
        Long id = 1L;

        Customer customer = new Customer();
        when(customerRepository.findById(id)).thenReturn(Optional.of(customer));

        CustomerDTO mappedCustomerDTO = new CustomerDTO();
        when(customerMapper.mapToDto(customer)).thenReturn(mappedCustomerDTO);

        // When
        CustomerDTO result = customerService.findById(id);

        // Then
        Assertions.assertEquals(mappedCustomerDTO, result);
        verify(customerRepository, times(1)).findById(id);
        verify(customerMapper, times(1)).mapToDto(customer);
    }

//    @Test
//    public void testFindCustomersByEmail() {
//        // Given
//        String email = "test@example.com";
//
//        List<Customer> allCustomers = new ArrayList<>();
//        Customer customer1 = new Customer();
//        customer1.setEmail(email);
//        Customer customer2 = new Customer();
//        customer2.setEmail("other@example.com");
//        allCustomers.add(customer1);
//        allCustomers.add(customer2);
//
//        when(customerRepository.findAll()).thenReturn(allCustomers);
//
//        // When
//        List<CustomerDTO> result = customerService.findCustomersByEmail(email);
//
//        // Then
//        Assertions.assertEquals(1, result.size());
//        Assertions.assertEquals(email, result.get(0).getEmail());
//        verify(customerRepository, times(1)).findAll();
//        verify(customerMapper, times(1)).mapToDto(allCustomers);
//    }


    @Test
    public void testFindByEmail() {
        // Given
        String email = "test@example.com";

        Customer customer = new Customer();
        when(customerRepository.findByEmail(email)).thenReturn(customer);

        CustomerDTO mappedCustomerDTO = new CustomerDTO();
        when(customerMapper.mapToDto(customer)).thenReturn(mappedCustomerDTO);

        // When
        CustomerDTO result = customerService.findByEmail(email);

        // Then
        Assertions.assertEquals(mappedCustomerDTO, result);
        verify(customerRepository, times(1)).findByEmail(email);
        verify(customerMapper, times(1)).mapToDto(customer);
    }

    @Test
    public void testDeleteCustomer() {
        // Given
        Long id = 1L;

        // When
        customerService.deleteCustomer(id);

        // Then
        verify(customerRepository, times(1)).deleteById(id);
    }
}
