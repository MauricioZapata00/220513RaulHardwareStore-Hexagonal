package com.web.Hardware.domain.services.store;

import com.web.Hardware.domain.persistence_ports.store.EmployeePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private final EmployeePersistence employeePersistence;

    @Autowired
    public EmployeeService(EmployeePersistence employeePersistence) {
        this.employeePersistence = employeePersistence;
    }

    public void assertCedulaNotExist(String cedula){

    }
}
