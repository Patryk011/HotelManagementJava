package pl.project.dto;

public class PaymentDTO {



    private Long id;
    private Long reservationId;

    private Long customerId;
    private double amount;

    private String status;
    private boolean isPaid;

    public PaymentDTO() {
    }

    public PaymentDTO(Long id, Long reservationId, Long customerId, double amount, String status, boolean isPaid) {
        this.id = id;
        this.reservationId = reservationId;
        this.customerId = customerId;
        this.amount = amount;
        this.status = status;
        this.isPaid = isPaid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
