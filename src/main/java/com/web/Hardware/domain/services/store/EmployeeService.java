package com.web.Hardware.domain.services.store;

import com.web.Hardware.domain.exceptions.ConflictException;
import com.web.Hardware.domain.exceptions.NotFoundException;
import com.web.Hardware.domain.models.store.Employee;
import com.web.Hardware.domain.persistence_ports.store.EmployeePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeePersistence employeePersistence;

    @Autowired
    public EmployeeService(EmployeePersistence employeePersistence) {
        this.employeePersistence = employeePersistence;
    }

    public void assertCedulaNotExist(String cedula){
        if(this.employeePersistence.ExistCedula(cedula)){
            throw new ConflictException("Cedula already exist: " + cedula);
        }
    }

    public void assertCedulaExist(String cedula){
        if (this.employeePersistence.notExistCedula(cedula)){
            throw new NotFoundException("Cedula does not exist: " + cedula);
        }
    }

    public void assertIdExist(String id){
        if (this.employeePersistence.notExistId(id)){
            throw new NotFoundException("ID does not exist: " + id);
        }
    }

    public Flux<Employee> getAllEmployees(){
        return this.employeePersistence.getAllEmployees();
    }

    public Optional<Mono<Employee>> getEmployeeWithCedula(String cedula){
        this.assertCedulaExist(cedula);
        return this.employeePersistence.getEmployeeUsingCedula(cedula);
    }

    public Optional<Flux<Employee>> getEmployeesWithName(String name){
        return this.employeePersistence.getEmployeesUsingName(name);
    }

    public Mono<Employee> createNewEmployee(Employee employee){
        this.assertCedulaNotExist(employee.getCedulaVendedor());
        return this.employeePersistence.generateEmployee(employee);
    }

    public Mono<Employee> updateCurrentEmployee(String id, Employee employee){
        this.assertIdExist(id);
        return this.employeePersistence.updateEmployee(id, employee);
    }

    public void deleteEmployee(String id){
        this.assertIdExist(id);
        this.employeePersistence.eraseEmployee(id);
    }
}
