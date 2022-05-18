package com.web.Hardware.adapters.mongodb.store.entities;

import com.web.Hardware.domain.models.store.Ticket;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

@Document(collection = "Tickets")
public class TicketEntity {

    @Id
    private String id;

    private LocalDateTime fecha;

    @DBRef
    private SupplierEntity proveedor;

    private ArrayList<ProductEntity> productosAIngresar;

    public TicketEntity() {
        //empty due to framework
    }

    public TicketEntity(Ticket ticket){
        BeanUtils.copyProperties(ticket, this);
        this.id = UUID.randomUUID().toString();
    }

    public Ticket toTicket(){
        Ticket ticket = new Ticket();
        BeanUtils.copyProperties(this, ticket);
        return ticket;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public SupplierEntity getProveedor() {
        return proveedor;
    }

    public void setProveedor(SupplierEntity proveedor) {
        this.proveedor = proveedor;
    }

    public ArrayList<ProductEntity> getProductosAIngresar() {
        return productosAIngresar;
    }

    public void setProductosAIngresar(ArrayList<ProductEntity> productosAIngresar) {
        this.productosAIngresar = productosAIngresar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TicketEntity)) return false;
        TicketEntity that = (TicketEntity) o;
        return getId().equals(that.getId()) && getFecha().equals(that.getFecha()) && getProveedor().equals(that.getProveedor()) && getProductosAIngresar().equals(that.getProductosAIngresar());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFecha(), getProveedor(), getProductosAIngresar());
    }

    @Override
    public String toString() {
        return "TicketEntity{" +
                "id='" + id + '\'' +
                ", fecha=" + fecha +
                ", proveedor=" + proveedor +
                ", productosAIngresar=" + productosAIngresar +
                '}';
    }
}
