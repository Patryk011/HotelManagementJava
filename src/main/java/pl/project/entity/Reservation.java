package pl.project.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="reservation_id")
    private int id;

    @Column(name = "reservation_arrival")
    private Date arrivalDate;

    @Column(name = "reservation_num_of_persons")
    private int persons;

    @Column(name = "reservation_stay_days")
    private int stayDays;
    @Column(name = "reservation_person_id")
    private int personId;

    @Column(name = "reservation_room")
    private String room;
    @Column(name = "reservation_price")
    private int price;
    @Column(name = "reservation_num_of_rooms")
    private int numRooms;


    public Reservation() {
    }

    public Reservation(int id, Date arrivalDate, int persons, int stayDays, int personId, String room, int price, int numRooms) {
        this.id = id;
        this.arrivalDate = arrivalDate;
        this.persons = persons;
        this.stayDays = stayDays;
        this.personId = personId;
        this.room = room;
        this.price = price;
        this.numRooms = numRooms;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public int getPersons() {
        return persons;
    }

    public void setPersons(int persons) {
        this.persons = persons;
    }

    public int getStayDays() {
        return stayDays;
    }

    public void setStayDays(int stayDays) {
        this.stayDays = stayDays;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getNumRooms() {
        return numRooms;
    }

    public void setNumRooms(int numRooms) {
        this.numRooms = numRooms;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
