package pl.project.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "newsletter_subscriber")
public class NewsletterSubscriber {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "newsletter_subscriber_id")
    private int id;

    @Column(name = "email_address")
    private String emailAddress;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;





    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
