package br.com.zup.inventory.controller.stock.request;

import br.com.zup.inventory.entity.stock.Stock;
import br.com.zup.inventory.entity.ticket.Ticket;

import java.util.UUID;

public class StockRequest {

    private String ticketId;

    private Integer quantity;

    public StockRequest() {
    }

    public StockRequest(String ticketId, Integer quantity) {
        this.ticketId = ticketId;
        this.quantity = quantity;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Stock toEntity() {
        return new Stock(
                UUID.randomUUID().toString(),
                new Ticket(this.ticketId),
                this.quantity
        );
    }
}
