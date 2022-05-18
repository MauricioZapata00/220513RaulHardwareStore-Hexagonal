package com.web.Hardware.domain.models.store;

public class Employee {

    private String nombreVendedor;
    private String celularVendedor;
    private String cedulaVendedor;

    public Employee() {
        //empty due to framework
    }

    public Employee(String nombreVendedor, String celularVendedor, String cedulaVendedor) {
        this.nombreVendedor = nombreVendedor;
        this.celularVendedor = celularVendedor;
        this.cedulaVendedor = cedulaVendedor;
    }

    public String getNombreVendedor() {
        return nombreVendedor;
    }

    public void setNombreVendedor(String nombreVendedor) {
        this.nombreVendedor = nombreVendedor;
    }

    public String getCelularVendedor() {
        return celularVendedor;
    }

    public void setCelularVendedor(String celularVendedor) {
        this.celularVendedor = celularVendedor;
    }

    public String getCedulaVendedor() {
        return cedulaVendedor;
    }

    public void setCedulaVendedor(String cedulaVendedor) {
        this.cedulaVendedor = cedulaVendedor;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "nombreVendedor='" + nombreVendedor + '\'' +
                ", celularVendedor='" + celularVendedor + '\'' +
                ", cedulaVendedor='" + cedulaVendedor + '\'' +
                '}';
    }
}
