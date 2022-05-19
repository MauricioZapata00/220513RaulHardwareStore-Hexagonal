package com.web.Hardware.domain.services.store;

import com.web.Hardware.domain.exceptions.NotFoundException;
import com.web.Hardware.domain.models.store.Ticket;
import com.web.Hardware.domain.persistence_ports.store.TicketPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
public class TicketService {

    private final TicketPersistence ticketPersistence;

    @Autowired
    public TicketService(TicketPersistence ticketPersistence) {
        this.ticketPersistence = ticketPersistence;
    }

    public void assertIdExist(String id){
        if (this.ticketPersistence.notExistId(id)){
            throw new NotFoundException("ID does not exist: " + id);
        }
    }

    public Flux<Ticket> getAllTickets(){
        return this.ticketPersistence.getAllTickets();
    }

    public Flux<Ticket> getTicketsWithDate(LocalDateTime dateTime){
        return this.ticketPersistence.getTicketsUsingDate(dateTime);
    }

    public Flux<Ticket> getTicketsWithSuppliersNIT(String NIT){
        return this.ticketPersistence.getTicketsUsingSupplierNIT(NIT);
    }

    public Mono<Ticket> createNewTicket(Ticket ticket){
        return this.ticketPersistence.generateTicket(ticket);
    }

    public void deleteTicket(String id){
        this.assertIdExist(id);
        this.ticketPersistence.eraseTicket(id);
    }
}
