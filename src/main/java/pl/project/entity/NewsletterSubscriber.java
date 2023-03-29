package pl.project.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "newsletter_subscriber")
public class NewsletterSubscriber {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "newsletter_subscriber_id")
    private int id;

    @Column(name = "newsletter_email_address")
    private String emailAddress;

    @Column(name = "newsletter_name")
    private String name;

    @Column(name = "newsletter_lastName")
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    public NewsletterSubscriber() {
    }

    public NewsletterSubscriber(int id, String emailAddress, String name, String lastName, Person person) {
        this.id = id;
        this.emailAddress = emailAddress;
        this.name = name;
        this.lastName = lastName;
        this.person = person;
    }


    public NewsletterSubscriber(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public NewsletterSubscriber(Person person) {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
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

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}












