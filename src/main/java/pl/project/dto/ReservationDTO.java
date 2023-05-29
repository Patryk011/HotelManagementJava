package pl.project.dto;

import java.util.Date;

public class ReservationDTO {

    private Long id;
    private Long customerId;

    private String customerEmail;
    private Long roomId;
    private Long hotelId;
    private Date startDate;
    private Date endDate;
    private String status;

    public ReservationDTO() {
    }

    public ReservationDTO(Long id, Long customerId, String customerEmail, Long roomId, Long hotelId, Date startDate, Date endDate, String status) {
        this.id = id;
        this.customerId = customerId;
        this.customerEmail = customerEmail;
        this.roomId = roomId;
        this.hotelId = hotelId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }
}