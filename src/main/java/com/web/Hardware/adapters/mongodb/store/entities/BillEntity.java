package com.web.Hardware.adapters.mongodb.store.entities;

import com.web.Hardware.domain.models.store.Bill;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

@Document(collection = "Bills")
public class BillEntity {

    @Id
    private String id;

    private AtomicLong incrementable = new AtomicLong(0);
    private LocalDateTime fecha;
    private Flux<ProductEntity> productos;

    @DBRef
    private Mono<EmployeeEntity> nombreVendedor;

    @DBRef
    private Mono<ClientEntity> nombreCliente;
    private Double totalAPagar;

    public BillEntity() {
        //empty due to framework
    }

    public BillEntity(Bill bill){
        BeanUtils.copyProperties(bill, this);
        this.id = UUID.randomUUID().toString();
    }

    public Bill toBill(){
        Bill bill = new Bill();
        BeanUtils.copyProperties(this, bill);
        return bill;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public AtomicLong getIncrementable() {
        return incrementable;
    }

    public void setIncrementable(AtomicLong incrementable) {
        this.incrementable = incrementable;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Flux<ProductEntity> getProductos() {
        return productos;
    }

    public void setProductos(Flux<ProductEntity> productos) {
        this.productos = productos;
    }

    public Mono<EmployeeEntity> getNombreVendedor() {
        return nombreVendedor;
    }

    public void setNombreVendedor(Mono<EmployeeEntity> nombreVendedor) {
        this.nombreVendedor = nombreVendedor;
    }

    public Mono<ClientEntity> getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(Mono<ClientEntity> nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public Double getTotalAPagar() {
        return totalAPagar;
    }

    public void setTotalAPagar(Double totalAPagar) {
        this.totalAPagar = totalAPagar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BillEntity)) return false;
        BillEntity that = (BillEntity) o;
        return getId().equals(that.getId()) && getIncrementable().equals(that.getIncrementable()) && getFecha().equals(that.getFecha()) && getProductos().equals(that.getProductos()) && getNombreVendedor().equals(that.getNombreVendedor()) && getNombreCliente().equals(that.getNombreCliente()) && getTotalAPagar().equals(that.getTotalAPagar());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getIncrementable(), getFecha(), getProductos(), getNombreVendedor(), getNombreCliente(), getTotalAPagar());
    }

    @Override
    public String toString() {
        return "BillEntity{" +
                "id='" + id + '\'' +
                ", incrementable=" + incrementable +
                ", fecha=" + fecha +
                ", productos=" + productos +
                ", nombreVendedor=" + nombreVendedor +
                ", nombreCliente=" + nombreCliente +
                ", totalAPagar=" + totalAPagar +
                '}';
    }
}
