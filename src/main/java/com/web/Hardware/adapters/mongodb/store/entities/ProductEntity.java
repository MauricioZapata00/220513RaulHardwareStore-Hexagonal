package com.web.Hardware.adapters.mongodb.store.entities;

import com.web.Hardware.domain.models.store.Product;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;
import java.util.UUID;

@Document(collection = "Products")
public class ProductEntity {

    @Id
    private String id;
    private String nombreProducto;
    private Integer cantidad;
    private Double precio;

    public ProductEntity() {
        //empty due to framework
    }

    public ProductEntity(Product product){
        BeanUtils.copyProperties(product, this);
        this.id = UUID.randomUUID().toString();
    }

    public Product toProduct(){
        Product product = new Product();
        BeanUtils.copyProperties(this, product);
        return product;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductEntity)) return false;
        ProductEntity that = (ProductEntity) o;
        return getId().equals(that.getId()) && getNombreProducto().equals(that.getNombreProducto()) && getCantidad().equals(that.getCantidad()) && getPrecio().equals(that.getPrecio());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNombreProducto(), getCantidad(), getPrecio());
    }

    @Override
    public String toString() {
        return "ProductEntity{" +
                "id='" + id + '\'' +
                ", nombreProducto='" + nombreProducto + '\'' +
                ", cantidad=" + cantidad +
                ", precio=" + precio +
                '}';
    }
}
