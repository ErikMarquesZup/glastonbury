package br.com.zup.inventory.entity.stock;

import br.com.zup.inventory.entity.ticket.Ticket;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity(name = "stocks")
public class Stock {

    @Id
    private String id;

    @OneToOne
    private Ticket ticket;

    private Integer quantity;

    public Stock() {
    }

    public Stock(String id, Ticket ticket, Integer quantity) {
        this.id = id;
        this.ticket = ticket;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
