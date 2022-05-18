package com.web.Hardware.adapters.mongodb.store.daos;

import com.web.Hardware.adapters.mongodb.store.entities.SupplierEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

public interface SupplierRepository extends ReactiveMongoRepository<SupplierEntity, String> {
    Optional<Mono<SupplierEntity>> findSupplierEntityByNIT (String NIT);
    Optional<Flux<SupplierEntity>> findSupplierEntitiesByNombreProveedor (String nombre);
}
