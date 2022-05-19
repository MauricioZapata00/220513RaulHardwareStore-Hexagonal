package com.web.Hardware.domain.persistence_ports.store;

import com.web.Hardware.domain.models.store.Product;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ProductPersistence {

    Flux<Product> getAllProducts();

    Boolean notExistId(String id);

    Flux<Product> getProductsUsingName(String name);

    Flux<Product> getProductsUsingPrice(Double price);

    Mono<Product> generateProduct(Product product);

    Mono<Product> updateProduct(String id, Product product);

    void eraseProduct(String id);
}
