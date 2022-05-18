package com.web.Hardware.domain.models.store;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

public class Ticket {

    private LocalDateTime fecha;
    private Mono<Supplier> proveedor;
    private Flux<Product> productosAIngresar;

    public Ticket() {
        //empty due to framework
    }

    public Ticket(Mono<Supplier> proveedor, Flux<Product> productosAIngresar) {
        this.fecha = LocalDateTime.now();
        this.proveedor = proveedor;
        this.productosAIngresar = productosAIngresar;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Mono<Supplier> getProveedor() {
        return proveedor;
    }

    public void setProveedor(Mono<Supplier> proveedor) {
        this.proveedor = proveedor;
    }

    public Flux<Product> getProductosAIngresar() {
        return productosAIngresar;
    }

    public void setProductosAIngresar(Flux<Product> productosAIngresar) {
        this.productosAIngresar = productosAIngresar;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "fecha=" + fecha +
                ", proveedor=" + proveedor +
                ", productosAIngresar=" + productosAIngresar +
                '}';
    }
}
