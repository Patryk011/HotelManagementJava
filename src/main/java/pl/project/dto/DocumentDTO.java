package pl.project.dto;

public class DocumentDTO {

    private Long id;
    private int state;
    private boolean createdOffer;
    private boolean createdReservation;
    private boolean createdInvoice;
    private Long customerId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public boolean isCreatedOffer() {
        return createdOffer;
    }

    public void setCreatedOffer(boolean createdOffer) {
        this.createdOffer = createdOffer;
    }

    public boolean isCreatedReservation() {
        return createdReservation;
    }

    public void setCreatedReservation(boolean createdReservation) {
        this.createdReservation = createdReservation;
    }

    public boolean isCreatedInvoice() {
        return createdInvoice;
    }

    public void setCreatedInvoice(boolean createdInvoice) {
        this.createdInvoice = createdInvoice;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
