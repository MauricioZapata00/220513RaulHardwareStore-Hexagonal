package com.web.Hardware.domain.persistence_ports.store;

import com.web.Hardware.domain.models.store.Bill;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface BillPersistence {

    Flux<Bill> getAllBills();

    Mono<Bill> generateBill(Bill bill);

    Optional<Flux<Bill>> getBillsUsingDate(LocalDateTime dateTime);

    Optional<Flux<Bill>> getBillsUsingClientName(String name);

    Optional<Flux<Bill>> getBillsUsingIncrementable(Long incrementable);

    void eraseBill(String id);
}
