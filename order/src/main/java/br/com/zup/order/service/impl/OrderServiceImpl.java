package br.com.zup.order.service.impl;

import br.com.zup.order.controller.request.CreateOrderRequest;
import br.com.zup.order.controller.response.OrderResponse;
import br.com.zup.order.entity.Order;
import br.com.zup.order.entity.StatusItem;
import br.com.zup.order.event.OrderCreatedEvent;
import br.com.zup.order.event.OrderCreatedItemEvent;
import br.com.zup.order.event.OrderCreatedStatusEvent;
import br.com.zup.order.repository.OrderRepository;
import br.com.zup.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private KafkaTemplate<String, OrderCreatedEvent> template;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, KafkaTemplate<String, OrderCreatedEvent> template) {
        this.orderRepository = orderRepository;
        this.template = template;
    }

    @Override
    public String save(CreateOrderRequest request) {
        Order order = this.orderRepository.save(request.toEntity());

        OrderCreatedEvent event = new OrderCreatedEvent(
                order.getId(),
                order.getCustomerId(),
                order.getAmount(),
                order.getItems().stream().map(item -> new OrderCreatedItemEvent(item.getId(), item.getQuantity())).collect(Collectors.toList()),
                order.getStatus().stream().map(status -> new OrderCreatedStatusEvent(status.getId(), status.getDate(), status.getStatus())).collect(Collectors.toList())
        );

        this.template.send("created-orders", event);

        return order.getId();
    }

    @Override
    public List<OrderResponse> findAll() {
        return this.orderRepository.findAll()
                .stream()
                .map(OrderResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void updateStatus(String orderId, String status) {
        Optional<Order> order = orderRepository.findById(orderId);
        order.get().getStatus().add(new StatusItem(status));
        orderRepository.save(order.get());
    }
}
