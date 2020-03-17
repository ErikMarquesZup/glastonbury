package br.com.zup.inventory.controller.ticket;

import br.com.zup.inventory.controller.ticket.request.TicketRequest;
import br.com.zup.inventory.controller.ticket.response.TicketResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import br.com.zup.inventory.service.ticket.TicketService;

import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    private TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public String create(@RequestBody TicketRequest request) {
        return this.ticketService.save(request);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<TicketResponse> getTickets() {
        return this.ticketService.findAll();
    }
}
