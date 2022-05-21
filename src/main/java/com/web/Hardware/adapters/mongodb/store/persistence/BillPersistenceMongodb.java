package com.web.Hardware.adapters.mongodb.store.persistence;

import com.web.Hardware.adapters.mongodb.store.daos.BillRepository;
import com.web.Hardware.adapters.mongodb.store.entities.BillEntity;
import com.web.Hardware.domain.exceptions.NotFoundException;
import com.web.Hardware.domain.models.store.Bill;
import com.web.Hardware.domain.persistence_ports.store.BillPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository("billPersistence")
public class BillPersistenceMongodb implements BillPersistence {

    private final BillRepository billRepository;

    @Autowired
    public BillPersistenceMongodb(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    @Override
    public Flux<Bill> getAllBills() {
        return this.billRepository
                .findAll()
                .map(billEntity -> billEntity.toBill());
    }

    @Override
    public Mono<Bill> generateBill(Bill bill) {
        return this.billRepository
                .save(new BillEntity(bill))
                .map(billEntity -> billEntity.toBill());
    }

    @Override
    public Optional<Flux<Bill>> getBillsUsingDate(LocalDateTime dateTime) {
        return this.billRepository
                .findBillEntitiesByFecha(dateTime)
                .map(billEntityFlux -> billEntityFlux
                        .map(billEntity -> billEntity.toBill()));
    }

    @Override
    public Optional<Flux<Bill>> getBillsUsingClientName(String name) {
        return this.billRepository
                .findBillEntitiesByNombreCliente(name)
                .map(billEntityFlux -> billEntityFlux
                        .map(billEntity -> billEntity.toBill()));
    }

    @Override
    public Optional<Flux<Bill>> getBillsUsingIncrementable(Long incrementable) {
        return this.billRepository
                .findBillEntitiesByIncrementable(incrementable)
                .map(billEntityFlux -> billEntityFlux
                        .map(billEntity -> billEntity.toBill()));
    }

    @Override
    public void eraseBill(String id) {
        Mono<BillEntity> billEntityToDelete = this.billRepository
                .findById(id);
        billEntityToDelete.subscribe(billEntity -> {
            this.billRepository.delete(billEntity);
        });
        //this.billRepository.delete(billEntityToDelete.block());
    }
}
