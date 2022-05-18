package com.web.Hardware.domain.models.store;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Ticket {

    private LocalDateTime fecha;
    private Supplier proveedor;
    private ArrayList<Product> productosAIngresar;

    public Ticket() {
        //empty due to framework
    }

    public Ticket(Supplier proveedor, ArrayList<Product> productosAIngresar) {
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

    public Supplier getProveedor() {
        return proveedor;
    }

    public void setProveedor(Supplier proveedor) {
        this.proveedor = proveedor;
    }

    public ArrayList<Product> getProductosAIngresar() {
        return productosAIngresar;
    }

    public void setProductosAIngresar(ArrayList<Product> productosAIngresar) {
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
