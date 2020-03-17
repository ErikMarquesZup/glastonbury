package br.com.zup.inventory.repository.stock;

import br.com.zup.inventory.entity.stock.Stock;
import br.com.zup.inventory.entity.ticket.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock, String> {

    Optional<Stock> findByTicket(Ticket ticket);
}
