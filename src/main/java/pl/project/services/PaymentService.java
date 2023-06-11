package pl.project.services;

import pl.project.dto.PaymentDTO;

import java.util.Date;
import java.util.List;

public interface PaymentService {



    List<PaymentDTO> getAllPayments();
    PaymentDTO findById(Long id);

    List<PaymentDTO> findPaymentsByReservationId(Long reservationId);

    PaymentDTO createPayment(Long reservationId);

    double calculateTotalPayments(Long customerId);

    PaymentDTO markPaymentAsPaid(Long paymentId);

    PaymentDTO markPaymentAsUnPaid(Long paymentId);


    PaymentDTO updatePayment(Long reservationId, Long newRoomId, Date newStartDate, Date newEndDate);

    void deletePayment(Long id);

    double calculateTotalCost(Long customerId);

    int calculateDuration(Date startDate, Date endDate);

}
