package br.com.zup.inventory.entity.ticket;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity(name = "tickets")
public class Ticket {

    @Id
    private String id;

    private String name;

    private BigDecimal amount;

    public Ticket() {
    }

    public Ticket(String id, String name, BigDecimal amount) {
        this.id = id;
        this.name = name;
        this.amount = amount;
    }

    public Ticket(String ticketId) {
        this.id = ticketId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
