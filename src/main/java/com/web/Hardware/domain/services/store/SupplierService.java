package com.web.Hardware.domain.services.store;

import com.web.Hardware.domain.exceptions.ConflictException;
import com.web.Hardware.domain.exceptions.NotFoundException;
import com.web.Hardware.domain.models.store.Supplier;
import com.web.Hardware.domain.persistence_ports.store.SupplierPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class SupplierService {

    private final SupplierPersistence supplierPersistence;

    @Autowired
    public SupplierService(SupplierPersistence supplierPersistence) {
        this.supplierPersistence = supplierPersistence;
    }

    public void assertNITExist(String NIT){
        if (this.supplierPersistence.notExistNIT(NIT)){
            throw new NotFoundException("NIT does not exist: " + NIT);
        }
    }

    public void assertNITNotExist(String NIT){
        if (this.supplierPersistence.ExistNIT(NIT)){
            throw new ConflictException("NIT already exist: " + NIT);
        }
    }

    public void assertIdExist(String id){
        if (this.supplierPersistence.notExistId(id)){
            throw new NotFoundException("ID does not exist: " + id);
        }
    }

    public Flux<Supplier> getAllSuppliers(){
        return this.supplierPersistence.getAllSuppliers();
    }

    public Flux<Supplier> getSuppliersWithName(String name){
        return this.supplierPersistence.getSuppliersUsingName(name);
    }

    public Mono<Supplier> getSupplierWithNIT(String NIT){
        this.assertNITExist(NIT);
        return this.supplierPersistence.getSupplierUsingNIT(NIT);
    }

    public Mono<Supplier> createNewSupplier(Supplier supplier){
        this.assertNITNotExist(supplier.getNIT());
        return this.supplierPersistence.generateSupplier(supplier);
    }

    public Mono<Supplier> updateCurrentSupplier(String id, Supplier supplier){
        this.assertIdExist(id);
        this.assertNITExist(supplier.getNIT());
        return this.supplierPersistence.updateSupplier(id, supplier);
    }

    public void deleteSupplier(String id){
        this.assertIdExist(id);
        this.supplierPersistence.eraseSupplier(id);
    }
}
