package br.com.zup.order.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Entity(name = "statusItems")
public class StatusItem {

    @Id
    private String id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    private String status;

    public StatusItem() {
    }

    public StatusItem(String id, Date date) {
        this.id = id;
        this.date = date;
    }

    public StatusItem(String status) {
        this.id = UUID.randomUUID().toString();
        this.date = Calendar.getInstance().getTime();
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
