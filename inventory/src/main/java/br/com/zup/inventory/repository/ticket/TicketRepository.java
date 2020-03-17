package br.com.zup.inventory.repository.ticket;

import br.com.zup.inventory.entity.ticket.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, String> {
}
