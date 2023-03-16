package pl.project.entity;


import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "person")
public class Person {


   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "person_id")
    private int id;

   @Column(name = "person_pesel")
    private int pesel;

   @Column(name = "person_name")
    private String name;

   @Column(name = "person_lastName")
    private String lastName;

   @OneToMany(cascade = CascadeType.ALL)
   private Collection<Reservation> reservations;


    public Person() {
    }

    public Person(int id, int pesel, String name, String lastName, Collection<Reservation> reservations) {
        this.id = id;
        this.pesel = pesel;
        this.name = name;
        this.lastName = lastName;
        this.reservations = reservations;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPesel() {
        return pesel;
    }

    public void setPesel(int pesel) {
        if (String.valueOf(pesel).length() != 11) {
            throw new RuntimeException();
        } else {
            this.pesel = pesel;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
