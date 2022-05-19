package com.web.Hardware.adapters.mongodb.store.persistence;

import com.web.Hardware.adapters.mongodb.store.daos.ClientRepository;
import com.web.Hardware.adapters.mongodb.store.entities.ClientEntity;
import com.web.Hardware.domain.models.store.Client;
import com.web.Hardware.domain.persistence_ports.store.ClientPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository("clientPersistence")
public class ClientPersistenceMongodb implements ClientPersistence {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientPersistenceMongodb(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Flux<Client> getAllClients() {
        return this.clientRepository
                .findAll()
                .map(clientEntity -> clientEntity.toClient());
    }

    @Override
    public Mono<Client> generateClient(Client client) {
        return this.clientRepository
                .save(new ClientEntity(client))
                .map(clientEntity -> clientEntity.toClient());
    }

    @Override
    public Boolean existCedula(String cedula) {
        Flux<Client> clientsFlux = this.getAllClients();
        return clientsFlux.
                hasElement(this.clientRepository
                        .findClientEntitiesByCedulaCliente(cedula)
                        .map(clientEntity -> clientEntity.toClient())
                        .block())
                .block();
    }

    @Override
    public Boolean notExistId(String id) {
        Flux<Client> clientsFlux = this.getAllClients();
        return clientsFlux.
                hasElement(this.clientRepository
                        .findById(id)
                        .map(clientEntity -> clientEntity.toClient())
                        .block())
                .block();
    }

    @Override
    public Mono<Client> updateClient(String id, Client client) {
        
        return null;
    }

    @Override
    public void eraseClient(String id) {

    }
}
