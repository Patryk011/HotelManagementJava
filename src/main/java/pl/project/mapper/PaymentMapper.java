package pl.project.mapper;

import org.springframework.stereotype.Component;
import pl.project.dto.PaymentDTO;
import pl.project.entity.Payment;
import pl.project.entity.Reservation;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PaymentMapper {


    public PaymentDTO mapToDTO(Payment payment) {
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setId(payment.getId());
        paymentDTO.setReservationId(payment.getReservation().getId());
        paymentDTO.setAmount(payment.getAmount());
        paymentDTO.setStatus(payment.getStatus());
        return paymentDTO;
    }

    public List<PaymentDTO> mapToDto(Collection<Payment> payments) {
        return payments.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public Payment mapFromDTO(PaymentDTO paymentDTO) {
        Payment payment = new Payment();
        Reservation reservation = new Reservation();
        reservation.setId(paymentDTO.getReservationId());

        payment.setReservation(reservation);
        payment.setAmount(paymentDTO.getAmount());
        payment.setStatus(paymentDTO.getStatus());
        return payment;
    }

    public Payment mapFromDTO(PaymentDTO paymentDTO, Payment payment) {
        Reservation reservation = new Reservation();
        reservation.setId(paymentDTO.getReservationId());
        payment.setAmount(paymentDTO.getAmount());
        payment.setStatus(paymentDTO.getStatus());
        return payment;
    }
}