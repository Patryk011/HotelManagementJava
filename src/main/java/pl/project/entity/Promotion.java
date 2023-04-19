package pl.project.entity;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "promotion")
public class Promotion {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private LocalDate startDate;

    private LocalDate endDate;



}
