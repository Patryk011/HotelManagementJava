package pl.project.entity;

import jakarta.persistence.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "document")
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "document_id")
    private Long id;

    @Column(name = "document_state")
    private int state;


    @Column(name = "createdOffer")
    private boolean createdOffer;

    @Column(name = "createdReservation")
    private boolean createdReservation;

    @Column(name = "createdInvoice")
    private boolean createdInvoice;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;




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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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
}



