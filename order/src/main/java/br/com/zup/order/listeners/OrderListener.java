package br.com.zup.order.listeners;

import br.com.zup.order.event.StatusOrderEvent;
import br.com.zup.order.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

import java.io.IOException;

@Configuration
public class OrderListener {

    Logger log = LoggerFactory.getLogger(OrderListener.class);

    private ObjectMapper objectMapper;
    private OrderService orderService;

    public OrderListener(ObjectMapper objectMapper, OrderService orderService) {
        this.objectMapper = objectMapper;
        this.orderService = orderService;
    }

    @KafkaListener(topics = "status-orders", groupId = "order-group-id")
    public void listenStatus(String message) throws IOException {
        StatusOrderEvent orderStatus = this.objectMapper.readValue(message, StatusOrderEvent.class);
        orderService.updateStatus(orderStatus.getOrderId(), orderStatus.getStatus());
        log.info("Status Order - Order {}, Status {}", orderStatus.getOrderId(), orderStatus.getStatus());
    }
}
