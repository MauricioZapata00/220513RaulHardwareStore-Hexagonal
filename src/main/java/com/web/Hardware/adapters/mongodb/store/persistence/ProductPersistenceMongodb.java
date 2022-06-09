package com.web.Hardware.adapters.mongodb.store.persistence;

import com.web.Hardware.adapters.mongodb.store.daos.ProductRepository;
import com.web.Hardware.adapters.mongodb.store.entities.ProductEntity;
import com.web.Hardware.domain.exceptions.NotFoundException;
import com.web.Hardware.domain.models.store.Product;
import com.web.Hardware.domain.persistence_ports.store.ProductPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

public class ProductPersistenceMongodb implements ProductPersistence {

    private final ProductRepository productRepository;

    @Autowired
    public ProductPersistenceMongodb(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Flux<Product> getAllProducts() {
        return this.productRepository
                .findAll()
                .map(ProductEntity::toProduct);
    }

    @Override
    public Boolean notExistId(String id) {
        Flux<ProductEntity> productEntityFlux = this.productRepository.findAll();
        return id.equals(productEntityFlux
                .filter(productEntity -> productEntity
                        .getId()
                        .equals(id))
                .defaultIfEmpty(new ProductEntity(new Product("qpwoeui", 1, 0.0)))
                .onErrorMap(e -> new NotFoundException("ID does not exist: " + id)));
    }

    @Override
    public Optional<Flux<Product>> getProductsUsingName(String name) {
        return this.productRepository
                .findProductEntitiesByNombreProducto(name)
                .map(productEntityFlux -> productEntityFlux
                        .map(productEntity -> productEntity.toProduct()));
    }

    @Override
    public Optional<Flux<Product>> getProductsUsingPrice(Double price) {
        return this.productRepository
                .findProductEntitiesByPrecio(price)
                .map(productEntityFlux -> productEntityFlux
                        .map(productEntity -> productEntity.toProduct()));
    }

    @Override
    public Mono<Product> generateProduct(Product product) {
        return this.productRepository
                .save(new ProductEntity(product))
                .map(ProductEntity::toProduct);
    }

    @Override
    public Mono<Product> updateProduct(String id, Product product) {
        Flux<ProductEntity> productEntityFlux = this.productRepository.findAll();
        return Mono.from(productEntityFlux
                .filter(productEntity -> productEntity
                        .getId()
                        .equals(id))
                .onErrorMap(e -> new NotFoundException("ID does not exist: " + id))
                .map(productEntity -> {
                    productEntity.setNombreProducto(product.getNombreProducto());
                    productEntity.setCantidad(product.getCantidad());
                    productEntity.setPrecio(product.getPrecio());
                    this.productRepository.save(productEntity);
                    return productEntity.toProduct();
                }));
    }

    @Override
    public void eraseProduct(String id) {
        Flux<ProductEntity> productEntityFlux = this.productRepository.findAll();
        productEntityFlux.filter(productEntity -> productEntity
                .getId()
                .equals(id))
                .onErrorMap(e -> new NotFoundException("ID does not exist: " + id))
                .map(productEntity -> this.productRepository.delete(productEntity));
    }
}
