package pl.project.entity;


import jakarta.persistence.*;

@Table(name="document_items")
@Entity
public class Document_items {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "document_items_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "document_room_id")
    private Room room;
    @Column(name = "document_items_id")
    private int quantity;
    @Column(name = "document_amount")
    private int amount;


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
