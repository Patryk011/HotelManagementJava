package pl.project.services;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import pl.project.Exception.PaymentException;
import pl.project.Exception.ReservationException;
import pl.project.dto.PaymentDTO;
import pl.project.dto.ReservationDTO;
import pl.project.entity.Payment;
import pl.project.entity.Reservation;
import pl.project.mapper.PaymentMapper;
import pl.project.repository.PaymentRepository;
import pl.project.repository.ReservationRepository;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final RoomService roomService;

    private final ReservationRepository reservationRepository;

    private final ReservationService reservationService;
    private final PaymentMapper paymentMapper;

    @Lazy
    public PaymentServiceImpl(PaymentRepository paymentRepository, RoomService roomService, ReservationRepository reservationRepository, ReservationService reservationService, PaymentMapper paymentMapper) {
        this.paymentRepository = paymentRepository;
        this.roomService = roomService;
        this.reservationRepository = reservationRepository;
        this.reservationService = reservationService;
        this.paymentMapper = paymentMapper;
    }

    @Override
    public List<PaymentDTO> getAllPayments() {
        return paymentMapper.mapToDto(paymentRepository.findAll());
    }

    @Override
    public PaymentDTO findById(Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new PaymentException("Payment with ID " + id + " not found."));
        return paymentMapper.mapToDTO(payment);
    }

    @Override
    public List<PaymentDTO> findPaymentsByReservationId(Long reservationId) {
        List<Payment> payments = paymentRepository.findByReservationId(reservationId);
        return paymentMapper.mapToDto(payments);
    }

    @Override
    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }




    @Override
    public PaymentDTO updatePayment(Long reservationId, Long newRoomId, Date newStartDate, Date newEndDate) {
        ReservationDTO reservationDTO = reservationService.findById(reservationId);
        if (reservationDTO == null) {
            throw new ReservationException("Reservation with ID " + reservationId + " not found.");
        }

        double newAmount = roomService.getRoomPrice(newRoomId) * calculateDuration(newStartDate, newEndDate);

        Payment payment = paymentRepository.findById(reservationId)
                .orElseThrow(() -> new PaymentException("Payment for Reservation with ID " + reservationId + " not found."));


        if (!payment.getReservation().getRoom().getId().equals(newRoomId)) {

            newAmount = roomService.getRoomPrice(newRoomId) * calculateDuration(newStartDate, newEndDate);
        }

        payment.setAmount(newAmount);
        paymentRepository.save(payment);

        return paymentMapper.mapToDTO(payment);
    }


    @Override
    public PaymentDTO markPaymentAsPaid(Long paymentId) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new PaymentException("Payment with ID " + paymentId + " not found."));

        payment.setPaid(true);
        payment.setStatus("Confirmed");
        payment = paymentRepository.save(payment);

        Reservation reservation = payment.getReservation();
        reservation.setStatus("Paid");
        reservationRepository.save(reservation);

        return paymentMapper.mapToDTO(payment);
    }


    @Override
    public PaymentDTO markPaymentAsUnPaid(Long paymentId) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new PaymentException("Payment with ID " + paymentId + " not found."));

        payment.setPaid(false);
        payment.setStatus("Cancelled");
        payment = paymentRepository.save(payment);

        Reservation reservation = payment.getReservation();
        reservation.setStatus("Cancelled");
        reservationRepository.save(reservation);

        return paymentMapper.mapToDTO(payment);
    }



    @Override
    public int calculateDuration(Date startDate, Date endDate) {
        long diff = endDate.getTime() - startDate.getTime();
        long durationInMillis = 1000 * 60 * 60 * 24; // 1 dzień w milisekundach
        return (int) (diff / durationInMillis);
    }

    @Override
    public PaymentDTO createPayment(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new NoSuchElementException("Reservation with ID " + reservationId + " not found."));

        double amount = roomService.getRoomPrice(reservation.getRoom().getId()) * calculateDuration(reservation.getStartDate(), reservation.getEndDate());

        Payment payment = new Payment();
        payment.setReservation(reservation);
        payment.setAmount(amount);
        payment.setPaid(false);
        payment.setStatus("Pending");
        payment = paymentRepository.save(payment);

        return paymentMapper.mapToDTO(payment);
    }

}
