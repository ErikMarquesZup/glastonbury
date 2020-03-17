package br.com.zup.inventory.service.ticket;

import br.com.zup.inventory.controller.ticket.request.TicketRequest;
import br.com.zup.inventory.controller.ticket.response.TicketResponse;
import br.com.zup.inventory.entity.ticket.Ticket;

import java.util.List;
import java.util.Optional;

public interface TicketService {

    String save(TicketRequest request);

    List<TicketResponse> findAll();

    Optional<Ticket> getById(String ticketId);

}
