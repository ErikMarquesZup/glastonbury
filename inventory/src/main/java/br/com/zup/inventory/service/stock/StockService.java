package br.com.zup.inventory.service.stock;

import br.com.zup.inventory.constants.StockOperationConstants;
import br.com.zup.inventory.controller.stock.request.StockRequest;
import br.com.zup.inventory.controller.stock.response.StockResponse;
import br.com.zup.inventory.entity.stock.Stock;
import br.com.zup.inventory.event.OrderCreatedEvent;

import java.util.List;
import java.util.Optional;

public interface StockService {

    String save(StockRequest request);

    List<StockResponse> findAll();

    Optional<Stock> getStockByTicket(String ticketId);

    void updateStock(OrderCreatedEvent order, StockOperationConstants type);
}
