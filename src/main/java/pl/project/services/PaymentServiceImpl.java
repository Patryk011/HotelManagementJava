package pl.project.services;

import org.springframework.stereotype.Service;
import pl.project.dto.PaymentDTO;
import pl.project.entity.Payment;
import pl.project.mapper.PaymentMapper;
import pl.project.repository.PaymentRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    private final PaymentMapper paymentMapper;

    public PaymentServiceImpl(PaymentRepository paymentRepository, PaymentMapper paymentMapper) {
        this.paymentRepository = paymentRepository;

        this.paymentMapper = paymentMapper;
    }



    @Override
    public PaymentDTO findById(Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Payment with ID " + id + " not found."));
        return paymentMapper.mapToDTO(payment);
    }

    @Override
    public PaymentDTO createPayment(PaymentDTO paymentDTO) {
        Payment payment = paymentMapper.mapFromDTO(paymentDTO);
        payment = paymentRepository.save(payment);
        return paymentMapper.mapToDTO(payment);


    }

    @Override
    public PaymentDTO updatePayment(Long id, PaymentDTO paymentDTO) {
        Payment existingPayment = paymentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Payment with ID " + id + " not found."));



        existingPayment = paymentMapper.mapFromDTO(existingPayment, paymentDTO);
        paymentRepository.save(existingPayment);
        return paymentMapper.mapToDTO(existingPayment);
    }

    @Override
    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }
}

