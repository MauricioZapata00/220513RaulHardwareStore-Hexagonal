package com.web.Hardware.domain.services.store;

import com.web.Hardware.domain.exceptions.ConflictException;
import com.web.Hardware.domain.exceptions.NotFoundException;
import com.web.Hardware.domain.models.store.Client;
import com.web.Hardware.domain.persistence_ports.store.ClientPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ClientService {

    private final ClientPersistence clientPersistence;

    @Autowired
    public ClientService(ClientPersistence clientPersistence) {
        this.clientPersistence = clientPersistence;
    }

    public void assertCedulaNotExist(String cedula){
        if (this.clientPersistence.existCedula(cedula)){
            throw new ConflictException("Cedula already exist: " + cedula);
        }
    }

    public void assertIdClientExist(String id){
        if (this.clientPersistence.notExistId(id)){
            throw new NotFoundException("ID does not exist: " + id);
        }
    }

    public Flux<Client> getAllClients(){
        return this.clientPersistence.getAllClients();
    }

    public Mono<Client> createNewClient(Client client){
        this.assertCedulaNotExist(client.getCedulaCliente());
        return this.clientPersistence.generateClient(client);
    }

    public Mono<Client> updateCurrentClient(String id, Client client){
        this.assertCedulaNotExist(client.getCedulaCliente());
        return this.clientPersistence.updateClient(id, client);
    }

    public void deleteClient(String id){
        this.assertIdClientExist(id);
        this.clientPersistence.eraseClient(id);
    }
}
