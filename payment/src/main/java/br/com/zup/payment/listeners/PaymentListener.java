package br.com.zup.payment.listeners;

import br.com.zup.payment.event.OrderCreatedEvent;
import br.com.zup.payment.event.StatusOrderEvent;
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
import java.util.Random;

@Configuration
public class PaymentListener {

    Logger log = LoggerFactory.getLogger(PaymentListener.class);

    private ObjectMapper objectMapper;
    private KafkaTemplate<String, OrderCreatedEvent> templatePayment;
    private KafkaTemplate<String, StatusOrderEvent> templateStatus;

    public PaymentListener(ObjectMapper objectMapper, KafkaTemplate<String, OrderCreatedEvent> templatePayment, KafkaTemplate<String, StatusOrderEvent> templateStatus) {
        this.objectMapper = objectMapper;
        this.templatePayment = templatePayment;
        this.templateStatus = templateStatus;
    }

    @KafkaListener(topics = "stock-success-orders", groupId = "payment-group-id")
    public void listen(String message) throws IOException {
        OrderCreatedEvent order = this.objectMapper.readValue(message, OrderCreatedEvent.class);
        String status;

        if (new Random().nextInt(5) >= 3){
            templatePayment.send(getMessagePayment(order, "payment-success-orders"));
            log.info(":: Payment Success - Order {}", order.getOrderId());
            status = "Order Payment Success";
        } else {
            templatePayment.send(getMessagePayment(order, "payment-failure-orders"));
            log.info(":: Payment Failure - Order {}", order.getOrderId());
            status = "Order Payment Failure";
        }

        templateStatus.send(getMessageStatus(new StatusOrderEvent(order.getOrderId(), status), "status-orders"));
        log.info(":: Status - Order {}", order.getOrderId());
    }

    private Message<OrderCreatedEvent> getMessagePayment(OrderCreatedEvent order, String topic) {
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
