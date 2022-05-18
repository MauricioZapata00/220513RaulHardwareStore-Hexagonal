package com.web.Hardware.adapters.mongodb.store.daos;

import com.web.Hardware.adapters.mongodb.store.entities.ClientEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ClientRepository extends ReactiveMongoRepository<ClientEntity, String> {
}
