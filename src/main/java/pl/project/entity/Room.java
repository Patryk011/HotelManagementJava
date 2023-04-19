package pl.project.entity;

import jakarta.persistence.*;

@Entity
@Table(name="Room")
public class Room {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="room_id")
    private Long id;




    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
