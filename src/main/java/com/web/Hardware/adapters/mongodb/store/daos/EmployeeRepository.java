package com.web.Hardware.adapters.mongodb.store.daos;

import com.web.Hardware.adapters.mongodb.store.entities.EmployeeEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

public interface EmployeeRepository extends ReactiveMongoRepository<EmployeeEntity, String>{
    Optional<Mono<EmployeeEntity>> findEmployeeEntityByCedulaVendedor (String cedula);
    Optional<Flux<EmployeeEntity>> findEmployeeEntitiesByNombreVendedor (String nombre);
}
