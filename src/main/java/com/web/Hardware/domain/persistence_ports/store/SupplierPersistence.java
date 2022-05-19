package com.web.Hardware.domain.persistence_ports.store;

import com.web.Hardware.domain.models.store.Supplier;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface SupplierPersistence {

    Flux<Supplier> getAllSuppliers();

    Flux<Supplier> getSuppliersUsingName(String name);

    Boolean notExistNIT(String NIT);

    Boolean ExistNIT(String NIT);

    Boolean notExistId(String id);

    Mono<Supplier> getSupplierUsingNIT(String NIT);

    Mono<Supplier> generateSupplier(Supplier supplier);

    Mono<Supplier> updateSupplier(String id, Supplier supplier);

    void eraseSupplier(String id);
}
