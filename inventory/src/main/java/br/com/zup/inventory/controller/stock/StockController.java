package br.com.zup.inventory.controller.stock;

import br.com.zup.inventory.controller.stock.request.StockRequest;
import br.com.zup.inventory.controller.stock.response.StockResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import br.com.zup.inventory.service.stock.StockService;

import java.util.List;

@RestController
@RequestMapping("/stocks")
public class StockController {

    private StockService stockService;

    @Autowired
    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public String create(@RequestBody StockRequest request) {
        return this.stockService.save(request);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<StockResponse> getTickets() {
        return this.stockService.findAll();
    }
}
