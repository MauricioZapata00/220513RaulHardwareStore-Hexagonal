package com.web.Hardware.domain.models.store;


import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

public class Bill {

    private AtomicLong incrementable = new AtomicLong(0);
    private LocalDateTime fecha;
    private ArrayList<Product> productos;
    private Employee nombreVendedor;
    private Client nombreCliente;
    private Double totalAPagar;

    public Bill() {
        //empty due to framework
    }

    public Bill(ArrayList<Product> productos, Employee nombreVendedor, Client nombreCliente) {
        this.fecha = LocalDateTime.now();
        this.incrementable.incrementAndGet();
        this.productos = productos;
        this.nombreVendedor = nombreVendedor;
        this.nombreCliente = nombreCliente;
        //this.calculatePrice();
    }

    public Bill(ArrayList<Product> productos, Employee nombreVendedor, Client nombreCliente, Double totalAPagar) {
        this.fecha = LocalDateTime.now();
        this.incrementable.incrementAndGet();
        this.productos = productos;
        this.nombreVendedor = nombreVendedor;
        this.nombreCliente = nombreCliente;
        this.totalAPagar = totalAPagar;
    }

    /*
    public void calculatePrice(){
        Double currTotal = 0.0;
        this.totalAPagar = this.productos.reduce(currTotal, (aDouble, product) -> {
            double v = product.getCantidad() * product.getPrecio();
            return v;
        }).block();
    }
     */

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

    public ArrayList<Product> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<Product> productos) {
        this.productos = productos;
    }

    public Employee getNombreVendedor() {
        return nombreVendedor;
    }

    public void setNombreVendedor(Employee nombreVendedor) {
        this.nombreVendedor = nombreVendedor;
    }

    public Client getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(Client nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public Double getTotalAPagar() {
        return totalAPagar;
    }

    public void setTotalAPagar(Double totalAPagar) {
        this.totalAPagar = totalAPagar;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "incrementable=" + incrementable +
                ", fecha=" + fecha +
                ", productos=" + productos +
                ", nombreVendedor=" + nombreVendedor +
                ", nombreCliente=" + nombreCliente +
                ", totalAPagar=" + totalAPagar +
                '}';
    }
}
