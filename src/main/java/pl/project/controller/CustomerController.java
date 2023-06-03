package pl.project.controller;

import pl.project.dto.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.project.dto.HotelDTO;
import pl.project.dto.ReservationDTO;
import pl.project.services.CustomerService;
import pl.project.services.ReservationService;

import java.util.List;



@RestController
@RequestMapping("/api/customers")
public class CustomerController {


    private final CustomerService customerService;

    private final ReservationService reservationService;




    @Autowired
    public CustomerController(CustomerService customerService, ReservationService reservationService) {
        this.customerService = customerService;
        this.reservationService = reservationService;
    }

    @GetMapping("/all")
    public List<CustomerDTO> getAllCustomers() {
        return customerService.findAllCustomers();
    }
    @GetMapping("/{id}/reservations")
    public List<ReservationDTO> findByCustomerId(@PathVariable Long id) {
        return reservationService.findByCustomerId(id);
    }


    @GetMapping("/{id}")
    public CustomerDTO getCustomerById(@PathVariable Long id) {
        return customerService.findById(id);
    }


    @GetMapping("/filter")
    public List<CustomerDTO> getCustomersByEmail(@RequestParam String email) {
        return customerService.findCustomersByEmail(email);
    }




    @PostMapping
    public CustomerDTO addCustomer(@RequestBody CustomerDTO customerDTO) {
        return customerService.addCustomer(customerDTO);
    }

    @PutMapping("/{id}")
    public CustomerDTO updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
        return customerService.updateCustomer(id, customerDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
    }
}
