package com.web.Hardware.adapters.mongodb.store.persistence;

import com.web.Hardware.adapters.mongodb.store.daos.ClientRepository;
import com.web.Hardware.adapters.mongodb.store.entities.ClientEntity;
import com.web.Hardware.domain.exceptions.NotFoundException;
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
        return cedula.equals(clientsFlux
                .filter(client -> client
                        .getCedulaCliente()
                        .equals(cedula))
                .onErrorMap(e -> new NotFoundException("Cedula does not exist: " + cedula))
                .map(client -> client.getCedulaCliente()));
    }

    @Override
    public Boolean notExistId(String id) {
        Flux<ClientEntity> clientEntityFlux = this.clientRepository.findAll();
        return id.equals(clientEntityFlux
                .filter(clientEntity -> clientEntity
                        .getId()
                        .equals(id))
                        //.onErrorReturn(new ClientEntity(new Client("aaa","000","xyz")))
                        .onErrorMap(e -> new NotFoundException("ID does not exist: " + id))
                .map(clientEntity -> clientEntity.getId()));
    }

    @Override
    public Mono<Client> updateClient(String id, Client client) {
        Flux<ClientEntity> clientEntityFlux = this.clientRepository.findAll();
        return Mono.from(clientEntityFlux
                .filter(clientEntity -> clientEntity
                        .getId()
                        .equals(id))
                .onErrorMap(e -> new NotFoundException("ID does not exist: " + id))
                .map(clientEntity -> {
                    clientEntity.setNombreCliente(client.getNombreCliente());
                    clientEntity.setCedulaCliente(client.getCedulaCliente());
                    clientEntity.setCelularCliente(client.getCelularCliente());
                    this.clientRepository.save(clientEntity);
                    return clientEntity.toClient();
                })
        );
    }

    @Override
    public void eraseClient(String id) {
        Flux<ClientEntity> clientEntityFlux = this.clientRepository.findAll();
        clientEntityFlux
                .filter(clientEntity -> clientEntity
                        .getId()
                        .equals(id))
                .onErrorMap(e -> new NotFoundException("ID does not exist: " + id))
                .map(clientEntity -> this.clientRepository.delete(clientEntity));
    }
}
