package br.com.zup.inventory.controller.ticket.response;

import br.com.zup.inventory.entity.ticket.Ticket;

import java.math.BigDecimal;

public class TicketResponse {

    private String id;

    private String name;

    private BigDecimal amount;

    public TicketResponse(String id, String name, BigDecimal amount) {
        this.id = id;
        this.name = name;
        this.amount = amount;
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

    public static TicketResponse fromEntity(Ticket ticket) {
        return new TicketResponse(
                ticket.getId(),
                ticket.getName(),
                ticket.getAmount()
        );
    }
}
