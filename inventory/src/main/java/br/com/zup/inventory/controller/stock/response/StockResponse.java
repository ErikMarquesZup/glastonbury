package br.com.zup.inventory.controller.stock.response;

import br.com.zup.inventory.entity.stock.Stock;
import br.com.zup.inventory.entity.ticket.Ticket;

public class StockResponse {

    private String id;

    private Ticket ticket;

    private Integer quantity;

    public StockResponse() {
    }

    public StockResponse(String id, Ticket ticket, Integer quantity) {
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

    public static StockResponse fromEntity(Stock stock) {
        return new StockResponse(
                stock.getId(),
                stock.getTicket(),
                stock.getQuantity()
        );
    }
}
