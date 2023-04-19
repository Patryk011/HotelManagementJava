package pl.project.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "promotion_notification")
public class Promotion_Notification {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String title;

    private String message;

    @ManyToOne
    @JoinColumn(name = "promotion_id")
    private Promotion promotion;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
