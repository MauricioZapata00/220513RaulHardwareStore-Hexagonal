package com.web.Hardware.domain.persistence_ports.store;

import com.web.Hardware.domain.models.store.Ticket;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Repository
public interface TicketPersistence {

    Flux<Ticket> getAllTickets();

    Flux<Ticket> getTicketsUsingDate(LocalDateTime dateTime);

    Flux<Ticket> getTicketsUsingSupplierNIT(String NIT);

    Mono<Ticket> generateTicket(Ticket ticket);

    Boolean notExistId(String id);

    void eraseTicket(String id);
}
