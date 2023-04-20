package pl.project.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "promotion_notification")
public class Promotion_Notification {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "promotion_notification_id")
    private Long id;

    @Column(name = "promotion_notification_title")
    private String title;
    @Column(name = "promotion_notification_message")
    private String message;

    @ManyToOne
    @JoinColumn(name = "promotion_id")
    private Promotion promotion;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

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
}
