package com.web.Hardware.adapters.mongodb.store.entities;

import com.web.Hardware.domain.models.store.Client;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;
import java.util.UUID;

@Document(collection = "Clients")
public class ClientEntity {

    @Id
    private String id;

    private String nombreCliente;
    private String celularCliente;
    @Indexed(unique = true)
    private String cedulaCliente;

    public ClientEntity() {
        //empty due to framework
    }

    public ClientEntity(Client client){
        BeanUtils.copyProperties(client, this);
        this.id = UUID.randomUUID().toString();
    }

    public Client toClient(){
        Client client = new Client();
        BeanUtils.copyProperties(this, client);
        return client;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getCelularCliente() {
        return celularCliente;
    }

    public void setCelularCliente(String celularCliente) {
        this.celularCliente = celularCliente;
    }

    public String getCedulaCliente() {
        return cedulaCliente;
    }

    public void setCedulaCliente(String cedulaCliente) {
        this.cedulaCliente = cedulaCliente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClientEntity)) return false;
        ClientEntity that = (ClientEntity) o;
        return getId().equals(that.getId()) && getNombreCliente().equals(that.getNombreCliente()) && getCelularCliente().equals(that.getCelularCliente()) && getCedulaCliente().equals(that.getCedulaCliente());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNombreCliente(), getCelularCliente(), getCedulaCliente());
    }

    @Override
    public String toString() {
        return "ClientEntity{" +
                "id='" + id + '\'' +
                ", nombreCliente='" + nombreCliente + '\'' +
                ", celularCliente='" + celularCliente + '\'' +
                ", cedulaCliente='" + cedulaCliente + '\'' +
                '}';
    }
}
