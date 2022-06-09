package com.web.Hardware.domain.services.store;

import com.web.Hardware.domain.exceptions.NotFoundException;
import com.web.Hardware.domain.models.store.Product;
import com.web.Hardware.domain.persistence_ports.store.ProductPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class ProductService {

    private final ProductPersistence productPersistence;

    @Autowired
    public ProductService(ProductPersistence productPersistence) {
        this.productPersistence = productPersistence;
    }

    public void assertIdExist(String id){
        if (this.productPersistence.notExistId(id)){
            throw new NotFoundException("ID does not exist: " + id);
        }
    }

    public Flux<Product> getAllProducts(){
        return this.productPersistence.getAllProducts();
    }

    public Optional<Flux<Product>> getProductsWithName(String name){
        return this.productPersistence.getProductsUsingName(name);
    }

    public Optional<Flux<Product>> getProductsWithPrice(Double price){
        return this.productPersistence.getProductsUsingPrice(price);
    }

    public Mono<Product> createNewProduct(Product product){
        return this.productPersistence.generateProduct(product);
    }

    public Mono<Product> updateCurrentProduct(String id, Product product){
        this.assertIdExist(id);
        return this.productPersistence.updateProduct(id, product);
    }

    public void deleteProduct(String id){
        this.assertIdExist(id);
        this.productPersistence.eraseProduct(id);
    }
}
