package com.web.Hardware.adapters.mongodb.store.daos;

import com.web.Hardware.adapters.mongodb.store.entities.TicketEntity;
import com.web.Hardware.domain.models.store.Supplier;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;
import java.util.Optional;

public interface TicketRepository extends ReactiveMongoRepository<TicketEntity, String> {
    Optional<Flux<TicketEntity>> findTicketEntitiesByFecha (LocalDateTime fecha);
    Optional<Flux<TicketEntity>> findTicketEntitiesByProveedor (Supplier supplier);
}
