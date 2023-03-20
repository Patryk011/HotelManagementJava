package pl.project.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "email_address")
public class EmailAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "email_address_id")
    private int id;

    @Column(name = "email_address")
    private String emailAddress;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;


    public EmailAddress() {
    }

    public EmailAddress(int id, String emailAddress) {
        this.id = id;
        this.emailAddress = emailAddress;
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
}
