package br.com.zup.payment.event;

import java.math.BigDecimal;
import java.util.List;

public class OrderCreatedEvent {

    private String orderId;
    private String customerId;
    private BigDecimal amount;
    private List<OrderCreatedItemEvent> items;
    private List<OrderCreatedStatusEvent> status;

    public OrderCreatedEvent() {
    }

    public OrderCreatedEvent(String orderId, String customerId, BigDecimal amount, List<OrderCreatedItemEvent> items, List<OrderCreatedStatusEvent> status) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.amount = amount;
        this.items = items;
        this.status = status;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public List<OrderCreatedItemEvent> getItems() {
        return items;
    }

    public void setItems(List<OrderCreatedItemEvent> items) {
        this.items = items;
    }

    public List<OrderCreatedStatusEvent> getStatus() {
        return status;
    }

    public void setStatus(List<OrderCreatedStatusEvent> status) {
        this.status = status;
    }
}
