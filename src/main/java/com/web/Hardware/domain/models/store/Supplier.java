package com.web.Hardware.domain.models.store;

public class Supplier {

    private String nombreProveedor;
    private String celularProveedor;
    private String NIT;

    public Supplier() {
        //empty due to framework
    }

    public Supplier(String nombreProveedor, String celularProveedor, String NIT) {
        this.nombreProveedor = nombreProveedor;
        this.celularProveedor = celularProveedor;
        this.NIT = NIT;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public String getCelularProveedor() {
        return celularProveedor;
    }

    public void setCelularProveedor(String celularProveedor) {
        this.celularProveedor = celularProveedor;
    }

    public String getNIT() {
        return NIT;
    }

    public void setNIT(String NIT) {
        this.NIT = NIT;
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "nombreProveedor='" + nombreProveedor + '\'' +
                ", celularProveedor='" + celularProveedor + '\'' +
                ", NIT='" + NIT + '\'' +
                '}';
    }
}
