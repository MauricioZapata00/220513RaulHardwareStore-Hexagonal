package com.web.Hardware.domain.persistence_ports.store;

import com.web.Hardware.domain.models.store.Bill;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Repository
public interface BillPersistence {

    Flux<Bill> getAllBills();

    Mono<Bill> generateBill(Bill bill);

    Flux<Bill> getBillsUsingDate(LocalDateTime dateTime);

    Flux<Bill> getBillsUsingClientName(String name);

    void eraseBill(String id);
}
