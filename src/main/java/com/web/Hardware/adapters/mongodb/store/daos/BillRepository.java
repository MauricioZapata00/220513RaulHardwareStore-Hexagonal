package com.web.Hardware.adapters.mongodb.store.daos;

import com.web.Hardware.adapters.mongodb.store.entities.BillEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;
import java.util.Optional;

public interface BillRepository extends ReactiveMongoRepository<BillEntity, String> {
    Optional<Flux<BillEntity>> findBillEntitiesByFecha(LocalDateTime fecha);
    Optional<Flux<BillEntity>> findBillEntitiesByIncrementable(Long incrementable);
    Optional<Flux<BillEntity>> findBillEntitiesByNombreCliente (String nombreCliente);
}
