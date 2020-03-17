package br.com.zup.order.controller.request;

import br.com.zup.order.entity.Order;
import br.com.zup.order.entity.OrderItem;
import br.com.zup.order.entity.StatusItem;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class CreateOrderRequest {

    private String customerId;

    private BigDecimal amount;

    private List<OrderItemPart> items;

    public CreateOrderRequest() {
    }

    public CreateOrderRequest(String customerId, BigDecimal amount, List<OrderItemPart> items) {
        this.customerId = customerId;
        this.amount = amount;
        this.items = items;
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

    public List<OrderItemPart> getItems() {
        return items;
    }

    public void setItems(List<OrderItemPart> items) {
        this.items = items;
    }

    public Order toEntity() {
        return new Order(
                UUID.randomUUID().toString(),
                this.customerId,
                this.amount,
                this.items.stream()
                        .map(OrderItemPart::toEntity)
                        .collect(Collectors.toList()),
                Collections.singletonList(new StatusItem("Pending"))
        );
    }


    public static class OrderItemPart {

        private String id;

        private String name;

        private BigDecimal amount;

        private Integer quantity;

        public OrderItemPart() {
        }

        public OrderItemPart(String id, String name, BigDecimal amount, Integer quantity) {
            this.id = id;
            this.name = name;
            this.amount = amount;
            this.quantity = quantity;
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

        public Integer getQuantity() {
            return quantity;
        }

        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }

        public OrderItem toEntity() {
            return new OrderItem(
                    this.id,
                    this.name,
                    this.amount,
                    this.quantity
            );
        }
    }
}
