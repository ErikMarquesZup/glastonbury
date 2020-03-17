package br.com.zup.inventory.listeners;

import br.com.zup.inventory.constants.StockOperationConstants;
import br.com.zup.inventory.event.OrderCreatedEvent;
import br.com.zup.inventory.event.StatusOrderEvent;
import br.com.zup.inventory.service.stock.StockService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import java.io.IOException;

@Configuration
public class InventoryListener {

    Logger log = LoggerFactory.getLogger(InventoryListener.class);

    private ObjectMapper objectMapper;
    private StockService stockService;
    private KafkaTemplate<String, OrderCreatedEvent> templateOrder;
    private KafkaTemplate<String, StatusOrderEvent> templateStatus;

    public InventoryListener(ObjectMapper objectMapper, KafkaTemplate<String, OrderCreatedEvent> templateOrder,
                             KafkaTemplate<String, StatusOrderEvent> templateStatus,
                             StockService stockService) {
        this.objectMapper = objectMapper;
        this.templateOrder = templateOrder;
        this.templateStatus = templateStatus;
        this.stockService = stockService;
    }

    @KafkaListener(topics = "created-orders", groupId = "inventory-group-id")
    public void listenCreatedOrder(String message) throws IOException {
        OrderCreatedEvent order = this.objectMapper.readValue(message, OrderCreatedEvent.class);
        String status;

        if (validateStock(order)) {
            updateStock(order);
            templateOrder.send(getMessageOrder(order, "stock-success-orders"));
            log.info(":: Stock Success - Order {}", order.getOrderId());
            status = "Order Stock Reserved";
        } else {
            templateOrder.send(getMessageOrder(order, "stock-failure-orders"));
            log.info(":: Stock Failure - Order {}", order.getOrderId());
            status = "Order Stock Failure";
        }

        templateStatus.send(getMessageStatus(new StatusOrderEvent(order.getOrderId(), status), "status-orders"));
        log.info(":: Status - Order {}", order.getOrderId());
    }

    @KafkaListener(topics = "payment-failure-orders", groupId = "inventory-group-id")
    public void listenPayment(String message) throws IOException {
        OrderCreatedEvent order = this.objectMapper.readValue(message, OrderCreatedEvent.class);
        updateStockReverse(order);
        log.info(":: Payment Failure, Stock Reverse - Order {}", order.getOrderId());
    }

    private boolean validateStock(OrderCreatedEvent order) {
        return order.getItems().stream().anyMatch(item -> stockService.getStockByTicket(item.getIdTicket())
                .get().getQuantity() >= item.getQuantity());
    }

    private void updateStock(OrderCreatedEvent order) {
        stockService.updateStock(order, StockOperationConstants.STOCK_LOW);
    }

    private void updateStockReverse(OrderCreatedEvent order) {
        stockService.updateStock(order, StockOperationConstants.STOCK_REVERSAL);
    }

    private Message<OrderCreatedEvent> getMessageOrder(OrderCreatedEvent order, String topic) {
        return  MessageBuilder.withPayload(order)
                .setHeader(KafkaHeaders.TOPIC, topic)
                .build();
    }

    private Message<StatusOrderEvent> getMessageStatus(StatusOrderEvent status, String topic) {
        return  MessageBuilder.withPayload(status)
                .setHeader(KafkaHeaders.TOPIC, topic)
                .build();
    }
}
