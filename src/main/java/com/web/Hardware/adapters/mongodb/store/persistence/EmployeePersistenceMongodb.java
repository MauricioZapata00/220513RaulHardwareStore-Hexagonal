package com.web.Hardware.adapters.mongodb.store.persistence;

import com.web.Hardware.adapters.mongodb.store.daos.EmployeeRepository;
import com.web.Hardware.adapters.mongodb.store.entities.EmployeeEntity;
import com.web.Hardware.domain.exceptions.ConflictException;
import com.web.Hardware.domain.exceptions.NotFoundException;
import com.web.Hardware.domain.models.store.Employee;
import com.web.Hardware.domain.persistence_ports.store.EmployeePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Repository("employeePersistence")
public class EmployeePersistenceMongodb implements EmployeePersistence {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeePersistenceMongodb(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    @Override
    public Flux<Employee> getAllEmployees() {
        return this.employeeRepository
                .findAll()
                .map(employeeEntity -> employeeEntity.toEmployee());
    }

    @Override
    public Optional<Mono<Employee>> getEmployeeUsingCedula(String cedula) {
        return this.employeeRepository
                .findEmployeeEntityByCedulaVendedor(cedula)
                .map(employeeEntityMono ->
                        employeeEntityMono
                                .map(employeeEntity ->
                                        employeeEntity.toEmployee()));
    }

    @Override
    public Boolean ExistCedula(String cedula) {
        Flux<Employee> employeeFlux = this.getAllEmployees();
        return cedula.equals(employeeFlux
                .filter(employee ->
                        employee
                                .getCedulaVendedor()
                                .equals(cedula))
                .onErrorMap(e -> new NotFoundException("Cedula does not exist: " + cedula))
                .map(employee -> employee.getCedulaVendedor()));
    }

    @Override
    public Boolean notExistCedula(String cedula) {
        Flux<Employee> employeeFlux = this.getAllEmployees();
        return cedula.equals(employeeFlux
                .filter(employee ->
                        employee
                                .getCedulaVendedor()
                                .equals(cedula))
                .defaultIfEmpty(new Employee("xcaxvz", "000", "rw485gf"))
                .onErrorMap(e -> new ConflictException("Cedula already exist: " + cedula))
                .map(employee -> employee.getCedulaVendedor()));
    }

    @Override
    public Optional<Flux<Employee>> getEmployeesUsingName(String name) {
        return this.employeeRepository
                .findEmployeeEntitiesByNombreVendedor(name)
                .map(employeeEntityFlux ->
                        employeeEntityFlux
                                .map(employeeEntity ->
                                        employeeEntity.toEmployee()));
    }

    @Override
    public Mono<Employee> generateEmployee(Employee employee) {
        return this.employeeRepository
                .save(new EmployeeEntity(employee))
                .map(employeeEntity -> employeeEntity.toEmployee());
    }

    @Override
    public Mono<Employee> updateEmployee(String id, Employee employee) {
        Flux<EmployeeEntity> employeeEntityFlux = this.employeeRepository.findAll();
        return Mono.from(employeeEntityFlux
                .filter(employeeEntity -> employeeEntity
                        .getId()
                        .equals(id))
                .onErrorMap(e -> new NotFoundException("ID does not exist: " + id))
                .map(employeeEntity -> {
                    employeeEntity.setCedulaVendedor(employee.getCedulaVendedor());
                    employeeEntity.setCelularVendedor(employee.getCelularVendedor());
                    employeeEntity.setNombreVendedor(employee.getNombreVendedor());
                    this.employeeRepository.save(employeeEntity);
                    return employeeEntity.toEmployee();
                })
        );
    }

    @Override
    public Boolean notExistId(String id) {
        Flux<EmployeeEntity> employeeEntityFlux = this.employeeRepository.findAll();
        return id.equals(employeeEntityFlux
                .filter(employeeEntity ->
                        employeeEntity
                                .getId()
                                .equals(id))
                .defaultIfEmpty(new EmployeeEntity(new Employee("xcaxvz", "000", "rw485gf")))
                .onErrorMap(e -> new ConflictException("ID already exist: " + id))
                .map(employeeEntity -> employeeEntity.getId()));
    }

    @Override
    public void eraseEmployee(String id) {
        Flux<EmployeeEntity> employeeEntityFlux = this.employeeRepository.findAll();
        employeeEntityFlux.filter(employeeEntity ->
                employeeEntity
                        .getId()
                        .equals(id))
                .onErrorMap(e -> new NotFoundException("ID does not exist: " + id))
                .map(employeeEntity -> this.employeeRepository.delete(employeeEntity));
    }
}
