package pl.project.entity;


import jakarta.persistence.*;
import pl.project.exception.PeselException;

import java.util.List;

@Entity
@Table(name = "person")
public class Person {


   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "person_id")
    private int id;

   @Column(name = "person_email")
   private String emailAddress;


   @Column(name = "person_subscriber")
   private boolean isNewsletterSubscriber;
   @Column(name = "person_pesel")
   private int pesel;

   @Column(name = "person_name")
    private String name;

   @Column(name = "person_lastName")
    private String lastName;

   @OneToMany(cascade = CascadeType.ALL)
   private List<Reservation> reservations;


    public Person() {

    }

    public Person(int id, String emailAddress, int pesel, String name, String lastName, List<Reservation> reservations) {
        this.id = id;
        this.emailAddress = emailAddress;
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

    public void setPesel(int pesel) throws PeselException {
        if (String.valueOf(pesel).length() != 11) {
            throw new PeselException("Pesel should have 11 digits!");
        }
            this.pesel = pesel;
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


    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public boolean isNewsletterSubscriber() {
        return isNewsletterSubscriber;
    }

    public void setNewsletterSubscriber(boolean newsletterSubscriber) {
        isNewsletterSubscriber = newsletterSubscriber;
    }


    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}
