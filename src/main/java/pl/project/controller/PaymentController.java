package pl.project.controller;

import org.springframework.web.bind.annotation.*;
import pl.project.dto.PaymentDTO;
import pl.project.services.PaymentService;

import java.util.List;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {



    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }


    @GetMapping("/all")
    public List<PaymentDTO> getAllPayments() {
        return paymentService.getAllPayments();
    }


    @PutMapping("/{id}/markAsPaid")
    public PaymentDTO markPaymentAsPaid(@PathVariable Long id) {
        return paymentService.markPaymentAsPaid(id);
    }

    @PutMapping("/{id}/markAsUnpaid")
    public PaymentDTO markPaymentAsUnPaid(@PathVariable Long id) {
        return paymentService.markPaymentAsUnPaid(id);
    }





}

