package com.web.Hardware.domain.persistence_ports.store;

import com.web.Hardware.domain.models.store.Client;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ClientPersistence {

    Flux<Client> getAllClients();

    Mono<Client> generateClient(Client client);

    Boolean existCedula(String cedula);

    Boolean notExistId(String id);

    Mono<Client> updateClient(String id, Client client);

    void eraseClient(String id);
}
