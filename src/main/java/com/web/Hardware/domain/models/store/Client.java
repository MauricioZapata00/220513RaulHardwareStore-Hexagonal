package com.web.Hardware.domain.models.store;

public class Client {

    private String nombreCliente;
    private String celularCliente;
    private String cedulaCliente;

    public Client() {
        //empty due to framework
    }

    public Client(String nombreCliente, String celularCliente, String cedulaCliente) {
        this.nombreCliente = nombreCliente;
        this.celularCliente = celularCliente;
        this.cedulaCliente = cedulaCliente;
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
    public String toString() {
        return "Client{" +
                "nombreCliente='" + nombreCliente + '\'' +
                ", celularCliente='" + celularCliente + '\'' +
                ", cedulaCliente='" + cedulaCliente + '\'' +
                '}';
    }
}
