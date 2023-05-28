package pl.project.mapper;


import org.springframework.stereotype.Component;
import pl.project.dto.CustomerDTO;
import pl.project.entity.Customer;
import pl.project.entity.Reservation;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerMapper {

    private final ReservationMapper reservationMapper;


    public CustomerMapper(ReservationMapper reservationMapper) {
        this.reservationMapper = reservationMapper;
    }

    public CustomerDTO mapToDto(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(customer.getId());
        customerDTO.setFirstName(customer.getFirstName());
        customerDTO.setLastName(customer.getLastName());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setReservations(reservationMapper.mapToDTO(customer.getReservations()));

        return customerDTO;
    }

    public List<CustomerDTO> mapToDto(Collection<Customer> customers) {
        return customers.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    public Customer mapFromDto(CustomerDTO customerDTO) {

        Customer customer = new Customer();

        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        customer.setEmail(customerDTO.getEmail());

        List<Reservation> reservations = customerDTO.getReservations().stream().map(reservationDTO -> reservationMapper.mapFromDTO(reservationDTO)).collect(Collectors.toList());
        customer.setReservations(reservations);
        return customer;
    }

    public Customer mapFromDto(Customer customer, CustomerDTO customerDTO) {


        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        customer.setEmail(customerDTO.getEmail());
        List<Reservation> reservations = customerDTO.getReservations().stream().map(reservationDTO -> reservationMapper.mapFromDTO(reservationDTO)).collect(Collectors.toList());
        customer.setReservations(reservations);

        return customer;
    }
}
