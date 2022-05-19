package com.web.Hardware.domain.services.store;

import com.web.Hardware.domain.models.store.Bill;
import com.web.Hardware.domain.persistence_ports.store.BillPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class BillService {

    private final BillPersistence billPersistence;

    @Autowired
    public BillService(BillPersistence billPersistence) {
        this.billPersistence = billPersistence;
    }

    public Flux<Bill> getAllBills(){
        return this.billPersistence.getAllBills();
    }

    public Optional<Flux<Bill>> getBillsWithDate(LocalDateTime dateTime){
        return this.billPersistence.getBillsUsingDate(dateTime);
    }

    public Optional<Flux<Bill>> getBillsWithClientName(String name){
        return this.billPersistence.getBillsUsingClientName(name);
    }

    public Mono<Bill> createNewBill(Bill bill){
        return this.billPersistence.generateBill(bill);
    }

    public void deleteBill(String id){
        this.billPersistence.eraseBill(id);
    }
}
