package pl.project.mapper;

import org.springframework.stereotype.Component;
import pl.project.Exception.ReservationException;
import pl.project.dto.PaymentDTO;
import pl.project.entity.Payment;
import pl.project.entity.Reservation;
import pl.project.repository.ReservationRepository;

import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Component
public class PaymentMapper {

    private final ReservationRepository reservationRepository;


    public PaymentMapper(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public PaymentDTO mapToDTO(Payment payment) {
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setId(payment.getId());
        paymentDTO.setReservationId(payment.getReservation().getId());
        paymentDTO.setCustomerId(payment.getReservation().getCustomer().getId());
        paymentDTO.setAmount(payment.getAmount());
        paymentDTO.setPaid(payment.isPaid());
        return paymentDTO;
    }

    public List<PaymentDTO> mapToDto(Collection<Payment> payments) {
        return payments.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public Payment mapFromDTO(PaymentDTO paymentDTO) {
        Payment payment = new Payment();
        Reservation reservation = reservationRepository.findById(paymentDTO.getReservationId()).orElseThrow(() -> new ReservationException("Reservation not found"));


        payment.setReservation(reservation);
        payment.setAmount(paymentDTO.getAmount());
        payment.setPaid(paymentDTO.isPaid());
        return payment;
    }

    public Payment mapFromDTO(Payment payment, PaymentDTO paymentDTO) {
        Reservation reservation = reservationRepository.findById(paymentDTO.getReservationId()).orElseThrow(() -> new ReservationException("Reservation not found"));

        payment.setReservation(reservation);
        payment.setAmount(paymentDTO.getAmount());
        payment.setPaid(paymentDTO.isPaid());
        return payment;
    }
}