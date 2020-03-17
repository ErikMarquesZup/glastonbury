package br.com.zup.order.controller.response;

import br.com.zup.order.entity.Order;
import br.com.zup.order.entity.StatusItem;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class StatusItemResponse {

    private String id;

    private Date date;

    private String status;

    public StatusItemResponse() {
    }

    public StatusItemResponse(String id, Date date, String status) {
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

    public static StatusItemResponse fromEntity(StatusItem status) {
        return new StatusItemResponse(
                status.getId(),
                status.getDate(),
                status.getStatus()
        );
    }
}
