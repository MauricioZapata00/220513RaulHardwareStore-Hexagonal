package com.web.Hardware.domain.persistence_ports.store;

import com.web.Hardware.domain.models.store.Employee;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Repository
public interface EmployeePersistence {

    Flux<Employee> getAllEmployees();

    Optional<Mono<Employee>> getEmployeeUsingCedula(String cedula);

    Boolean ExistCedula(String cedula);

    Boolean notExistCedula(String cedula);

    Optional<Flux<Employee>> getEmployeesUsingName(String name);

    Mono<Employee> generateEmployee(Employee employee);

    Mono<Employee> updateEmployee(String id, Employee employee);

    Boolean notExistId(String id);

    void eraseEmployee(String id);
}
