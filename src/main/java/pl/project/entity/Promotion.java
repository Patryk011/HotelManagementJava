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



}
