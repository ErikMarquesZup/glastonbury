package br.com.zup.inventory.service.ticket.impl;

import br.com.zup.inventory.controller.ticket.request.TicketRequest;
import br.com.zup.inventory.controller.ticket.response.TicketResponse;
import br.com.zup.inventory.entity.ticket.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.zup.inventory.repository.ticket.TicketRepository;
import br.com.zup.inventory.service.ticket.TicketService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl implements TicketService {

    private TicketRepository ticketRepository;

    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public String save(TicketRequest request) {
        String ticketId = this.ticketRepository.save(request.toEntity()).getId();
        return ticketId;
    }

    @Override
    public List<TicketResponse> findAll() {
        return this.ticketRepository.findAll()
                .stream()
                .map(TicketResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Ticket> getById(String ticketId) {
        return ticketRepository.findById(ticketId);
    }
}
