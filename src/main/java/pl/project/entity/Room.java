package pl.project.entity;

import jakarta.persistence.*;

@Entity
@Table(name="Room")
public class Room {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Long id;


    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;
    @Column(name = "room_number")
    private int number;

    @Column(name = "room_type")
    private String type;

    @Column(name = "room_price")
    private double price;

    @Column(name = "isFree")
    boolean isFree;

    public Room() {
    }

    public Room(Long id, Hotel hotel, int number, String type, double price) {
        this.id = id;
        this.hotel = hotel;
        this.number = number;
        this.type = type;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean free) {
        isFree = free;
    }
}