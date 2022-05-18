package com.web.Hardware.adapters.mongodb.store.daos;

import com.web.Hardware.adapters.mongodb.store.entities.ProductEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

import java.util.Optional;

public interface ProductRepository extends ReactiveMongoRepository<ProductEntity, String> {
    Optional<Flux<ProductEntity>> findProductEntitiesByNombreProducto (String nombre);
    Optional<Flux<ProductEntity>> findProductEntitiesByPrecio (Double precio);
}
