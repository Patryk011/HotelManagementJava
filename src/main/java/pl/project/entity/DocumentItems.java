package pl.project.entity;


import jakarta.persistence.*;

@Table(name="document_items")
@Entity
public class DocumentItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "document_items_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "document_id")
    private Document document;


    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @ManyToOne
    @JoinColumn(name = "document_room_id")
    private Room room;
    @Column(name = "document_quantity")
    private int quantity;
    @Column(name = "document_amount")
    private int amount;


    public DocumentItems(Long id, Document document, Hotel hotel, Room room, int quantity, int amount) {
        this.id = id;
        this.document = document;
        this.hotel = hotel;
        this.room = room;
        this.quantity = quantity;
        this.amount = amount;
    }

    public DocumentItems() {
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
