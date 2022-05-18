package com.web.Hardware.adapters.mongodb.store.entities;

import com.web.Hardware.domain.models.store.Employee;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;
import java.util.UUID;

@Document(collection = "Employees")
public class EmployeeEntity {

    @Id
    private String id;

    private String nombreVendedor;
    private String celularVendedor;

    @Indexed(unique = true)
    private String cedulaVendedor;

    public EmployeeEntity() {
        //empty due to framework
    }

    public EmployeeEntity(Employee employee){
        BeanUtils.copyProperties(employee, this);
        this.id = UUID.randomUUID().toString();
    }

    public Employee toEmployee(){
        Employee employee = new Employee();
        BeanUtils.copyProperties(this, employee);
        return employee;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmployeeEntity)) return false;
        EmployeeEntity that = (EmployeeEntity) o;
        return getId().equals(that.getId()) && getNombreVendedor().equals(that.getNombreVendedor()) && getCelularVendedor().equals(that.getCelularVendedor()) && getCedulaVendedor().equals(that.getCedulaVendedor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNombreVendedor(), getCelularVendedor(), getCedulaVendedor());
    }

    @Override
    public String toString() {
        return "EmployeeEntity{" +
                "id='" + id + '\'' +
                ", nombreVendedor='" + nombreVendedor + '\'' +
                ", celularVendedor='" + celularVendedor + '\'' +
                ", cedulaVendedor='" + cedulaVendedor + '\'' +
                '}';
    }
}
