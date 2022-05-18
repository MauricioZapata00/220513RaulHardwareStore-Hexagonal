package com.web.Hardware.adapters.mongodb.store.entities;

import com.web.Hardware.domain.models.store.Supplier;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;
import java.util.UUID;

@Document(collection = "Suppliers")
public class SupplierEntity {

    @Id
    private String id;
    private String nombreProveedor;
    private String celularProveedor;

    @Indexed(unique = true)
    private String NIT;

    public SupplierEntity() {
        //empty due to framework
    }

    public SupplierEntity(Supplier supplier){
        BeanUtils.copyProperties(supplier, this);
        this.id = UUID.randomUUID().toString();
    }

    public Supplier toSupplier(){
        Supplier supplier = new Supplier();
        BeanUtils.copyProperties(this, supplier);
        return supplier;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SupplierEntity)) return false;
        SupplierEntity that = (SupplierEntity) o;
        return getId().equals(that.getId()) && getNombreProveedor().equals(that.getNombreProveedor()) && getCelularProveedor().equals(that.getCelularProveedor()) && getNIT().equals(that.getNIT());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNombreProveedor(), getCelularProveedor(), getNIT());
    }

    @Override
    public String toString() {
        return "SupplierEntity{" +
                "id='" + id + '\'' +
                ", nombreProveedor='" + nombreProveedor + '\'' +
                ", celularProveedor='" + celularProveedor + '\'' +
                ", NIT='" + NIT + '\'' +
                '}';
    }
}
