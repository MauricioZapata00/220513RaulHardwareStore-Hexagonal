package com.web.Hardware.adapters.mongodb.store.daos;

import com.web.Hardware.adapters.mongodb.DatabaseSeederService;
import com.web.Hardware.adapters.mongodb.store.StoreSeederService;
import com.web.Hardware.adapters.mongodb.store.entities.BillEntity;
import com.web.Hardware.domain.models.store.Bill;
import com.web.Hardware.domain.models.store.Client;
import com.web.Hardware.domain.models.store.Employee;
import com.web.Hardware.domain.models.store.Product;
import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.ArrayList;

import static org.mockito.Mockito.*;


public class BillRepositoryIT {

    BillRepository billRepository;

    ClientRepository clientRepository;

    EmployeeRepository employeeRepository;

    ProductRepository productRepository;

    SupplierRepository supplierRepository;

    TicketRepository ticketRepository;

    private StoreSeederService storeSeederService;

    private DatabaseSeederService databaseSeederService;

    @BeforeEach
    @Autowired
    public void setUp(){
        billRepository = mock(BillRepository.class);
        clientRepository = mock(ClientRepository.class);
        employeeRepository = mock(EmployeeRepository.class);
        productRepository = mock(ProductRepository.class);
        supplierRepository = mock(SupplierRepository.class);
        ticketRepository = mock(TicketRepository.class);
        this.storeSeederService = new StoreSeederService(billRepository,
                clientRepository, employeeRepository, productRepository,
                supplierRepository, ticketRepository);
        this.databaseSeederService = new DatabaseSeederService(this.storeSeederService);
    }

    @Test
    void findBillsUsingDates(){
        this.setUp();
        //Arrange
        ArrayList<Product> productArrayList = new ArrayList<>();
        productArrayList.add(new Product("Destornillador", 1 , Double.valueOf(15000)));
        productArrayList.add(new Product("Estuco Plástico", 1 , Double.valueOf(90000)));
        productArrayList.add(new Product("Cera Para Madera", 2 , Double.valueOf(30000)));
        /*
        Flux<Product> productFlux = Flux.just(productArrayList.get(0), productArrayList.get(1), productArrayList.get(2));
        Mono<Employee> employeeTest = Mono.just(new Employee("John Cuartas", "3017179937", "1023506987"));
        Mono<Client> clientTest = Mono.just(new Client("Benito Camelas", "3178245611", "1056412369"));
        BillEntity billEntityTest = new BillEntity(new Bill(productFlux, employeeTest, clientTest));

         */
        Employee employeeTest = new Employee("John Cuartas", "3017179937", "1023506987");
        Client clientTest = new Client("Benito Camelas", "3178245611", "1056412369");
        BillEntity billEntityTest = new BillEntity(new Bill(productArrayList, employeeTest, clientTest));
        //Act
        LogManager.getLogger(this.getClass()).info("-----------BillEntity ID: " + billEntityTest.getId());
        LogManager.getLogger(this.getClass()).info("All BillEntity: " + this.billRepository.findAll());
        when(this.billRepository.findAll()).thenReturn(Flux.just(billEntityTest));
        StepVerifier.create(this.billRepository.findAll())
                .expectNextMatches(billEntity -> {
                    Assertions.assertInstanceOf(this.billRepository.findBillEntitiesByFecha(billEntity.getFecha()).getClass(),
                            this.billRepository.findBillEntitiesByFecha(billEntity.getFecha()));
                    Assertions.assertEquals(this.billRepository.findById(billEntity.getId()),
                            this.billRepository.findBillEntitiesByFecha(billEntity.getFecha()));
                    return true;
                }).verifyComplete();
        //Assert
        verify(this.billRepository).findBillEntitiesByFecha(billEntityTest.getFecha());
    }
}
