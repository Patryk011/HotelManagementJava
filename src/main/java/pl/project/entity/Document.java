package pl.project.entity;

import jakarta.persistence.*;

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
}



