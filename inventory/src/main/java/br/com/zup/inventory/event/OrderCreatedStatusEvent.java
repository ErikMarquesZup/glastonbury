package br.com.zup.inventory.event;

import java.util.Date;

public class OrderCreatedStatusEvent {

    private String id;
    private Date date;
    private String status;

    public OrderCreatedStatusEvent() {
    }

    public OrderCreatedStatusEvent(String id, Date date, String status) {
        this.id = id;
        this.date = date;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
