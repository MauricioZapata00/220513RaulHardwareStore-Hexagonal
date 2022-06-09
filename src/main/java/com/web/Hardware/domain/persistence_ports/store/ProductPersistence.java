package com.web.Hardware.domain.persistence_ports.store;

import com.web.Hardware.domain.models.store.Product;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Repository
public interface ProductPersistence {

    Flux<Product> getAllProducts();

    Boolean notExistId(String id);

    Optional<Flux<Product>> getProductsUsingName(String name);

    Optional<Flux<Product>> getProductsUsingPrice(Double price);

    Mono<Product> generateProduct(Product product);

    Mono<Product> updateProduct(String id, Product product);

    void eraseProduct(String id);
}
