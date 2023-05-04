package pl.project.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "promotion_notification")
public class PromotionNotification {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "promotion_notification_id")
    private Long id;

    @Column(name = "promotion_notification_message")
    private String message;

    @ManyToOne
    @JoinColumn(name = "promotion_id")
    private Promotion promotion;


    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;




    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
