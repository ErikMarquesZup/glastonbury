package br.com.zup.order.event;

public class OrderCreatedItemEvent {

    private String idTicket;
    private Integer quantity;

    public OrderCreatedItemEvent() {
    }

    public OrderCreatedItemEvent(String idTicket, Integer quantity) {
        this.idTicket = idTicket;
        this.quantity = quantity;
    }

    public String getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(String idTicket) {
        this.idTicket = idTicket;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
