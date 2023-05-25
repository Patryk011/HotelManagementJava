package pl.project.dto;

public class PaymentDTO {



    private Long id;
    private Long reservationId;
    private double amount;
    private String status;

    public PaymentDTO() {
    }

    public PaymentDTO(Long id, Long reservationId, double amount, String status) {
        this.id = id;
        this.reservationId = reservationId;
        this.amount = amount;
        this.status = status;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
