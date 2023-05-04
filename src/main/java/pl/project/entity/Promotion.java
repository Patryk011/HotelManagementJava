package pl.project.entity;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "promotion")
public class Promotion {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "promotion_id")
    private Long id;
    @Column(name = "promotion_title")
    private String title;
    @Column(name = "promotion_description")
    private String description;
    @Column(name = "promotion_start_date")
    private LocalDate startDate;
    @Column(name = "promotion_end_date")
    private LocalDate endDate;
    @Column(name = "promotion_active")
    private boolean active;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
