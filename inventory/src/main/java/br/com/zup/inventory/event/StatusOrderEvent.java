package br.com.zup.inventory.event;

public class StatusOrderEvent {

    private String orderId;
    private String status;

    public StatusOrderEvent() {
    }

    public StatusOrderEvent(String orderId, String status) {
        this.orderId = orderId;
        this.status = status;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
