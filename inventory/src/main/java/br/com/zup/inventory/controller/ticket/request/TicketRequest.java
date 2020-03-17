package br.com.zup.inventory.controller.ticket.request;

import br.com.zup.inventory.entity.ticket.Ticket;

import java.math.BigDecimal;
import java.util.UUID;

public class TicketRequest {

    private String name;

    private BigDecimal amount;

    public TicketRequest() {
    }

    public TicketRequest(String name, BigDecimal amount) {
        this.name = name;
        this.amount = amount;
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

    public Ticket toEntity() {
        return new Ticket(
                UUID.randomUUID().toString(),
                this.name,
                this.amount
        );
    }
}
