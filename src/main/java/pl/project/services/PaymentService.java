package pl.project.services;

import pl.project.dto.PaymentDTO;

import java.util.List;

public interface PaymentService {



    PaymentDTO findById(Long id);

    PaymentDTO createPayment(PaymentDTO paymentDTO);

    PaymentDTO updatePayment(Long id, PaymentDTO paymentDTO);

    void deletePayment(Long id);

}
