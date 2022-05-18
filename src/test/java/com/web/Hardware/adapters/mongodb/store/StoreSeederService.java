package com.web.Hardware.adapters.mongodb.store;

import com.web.Hardware.adapters.mongodb.store.daos.*;
import com.web.Hardware.adapters.mongodb.store.entities.*;
import com.web.Hardware.domain.models.store.*;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
public class StoreSeederService {

    private final BillRepository billRepository;
    private final ClientRepository clientRepository;
    private final EmployeeRepository employeeRepository;
    private final ProductRepository productRepository;
    private final SupplierRepository supplierRepository;
    private final TicketRepository ticketRepository;

    @Autowired
    public StoreSeederService(BillRepository billRepository, ClientRepository clientRepository,
                              EmployeeRepository employeeRepository,
                              ProductRepository productRepository,
                              SupplierRepository supplierRepository,
                              TicketRepository ticketRepository) {
        this.billRepository = billRepository;
        this.clientRepository = clientRepository;
        this.employeeRepository = employeeRepository;
        this.productRepository = productRepository;
        this.supplierRepository = supplierRepository;
        this.ticketRepository = ticketRepository;
    }

    public void seedDatabase(){
        LogManager.getLogger(this.getClass()).info("-----------Store Initial Load-----------\n");
        LogManager.getLogger(this.getClass()).info("-----------Loading Seed Clients-----------");
        List<ClientEntity> nonReactiveStreamOfClients = new ArrayList<>();
        nonReactiveStreamOfClients.add(new ClientEntity(new Client("Juana Gaviria", "3147210641", "1143598881")));
        nonReactiveStreamOfClients.add(new ClientEntity(new Client("Paola Saldarriaga", "3015712596", "1071523698")));
        nonReactiveStreamOfClients.add(new ClientEntity(new Client("Sergio Suarez", "3019232741", "1103851469")));
        nonReactiveStreamOfClients.add(new ClientEntity(new Client("Jaime Isaza", "3185244440", "82954103")));
        LogManager.getLogger(this.getClass()).info("Client 0: " + nonReactiveStreamOfClients.get(0));
        LogManager.getLogger(this.getClass()).info("ArrayList: " + nonReactiveStreamOfClients);
        this.clientRepository.saveAll(nonReactiveStreamOfClients).subscribe();

        /*
        this.clientRepository.save(nonReactiveStreamOfClients.get(0)).subscribe();
        this.clientRepository.save(nonReactiveStreamOfClients.get(1)).subscribe();
        this.clientRepository.save(nonReactiveStreamOfClients.get(2)).subscribe();
        this.clientRepository.save(nonReactiveStreamOfClients.get(3)).subscribe(result ->{
            LogManager.getLogger(this.getClass()).info("-----------Saving Seed Clients-----------");
        });
        */
        LogManager.getLogger(this.getClass()).info("-----------Seed Clients Successfully Loaded-----------\n");

        LogManager.getLogger(this.getClass()).info("-------------------------------------------------");

        LogManager.getLogger(this.getClass()).info("-----------Loading Seed Employees-----------");
        List<EmployeeEntity> nonReactiveStreamOfEmployees = new ArrayList<>();
        nonReactiveStreamOfEmployees.add(new EmployeeEntity(new Employee("Tatiana López", "3174270927", "1152963541")));
        this.employeeRepository.saveAll(nonReactiveStreamOfEmployees).subscribe(result ->{
            LogManager.getLogger(this.getClass()).info("-----------Saving Seed Employees-----------");
        });
        LogManager.getLogger(this.getClass()).info("-----------Seed Employees Successfully Loaded-----------\n");

        LogManager.getLogger(this.getClass()).info("-------------------------------------------------");

        LogManager.getLogger(this.getClass()).info("-----------Loading Seed Products-----------");
        List<ProductEntity> nonReactiveStreamOfProduct = new ArrayList<>();
        nonReactiveStreamOfProduct.add(new ProductEntity(new Product("Pintura Verde", 5, Double.valueOf(45000))));
        nonReactiveStreamOfProduct.add(new ProductEntity(new Product("Pintura Blanca", 7, Double.valueOf(45000))));
        nonReactiveStreamOfProduct.add(new ProductEntity(new Product("Pintura Blanca", 2, Double.valueOf(47000))));
        nonReactiveStreamOfProduct.add(new ProductEntity(new Product("Martillo", 10, Double.valueOf(20000))));
        this.productRepository.saveAll(nonReactiveStreamOfProduct).subscribe(result ->{
            LogManager.getLogger(this.getClass()).info("-----------Saving Products Seed-----------");
        });
        LogManager.getLogger(this.getClass()).info("-----------Products Seed Successfully Loaded-----------\n");

        LogManager.getLogger(this.getClass()).info("-------------------------------------------------");

        LogManager.getLogger(this.getClass()).info("-----------Loading Seed Suppliers-----------");
        List<SupplierEntity> nonReactiveStreamOfSuppliers = new ArrayList<>();
        nonReactiveStreamOfSuppliers.add(new SupplierEntity(new Supplier("ATEK Holding S.A.S", "3245698215", "14785296")));
        nonReactiveStreamOfSuppliers.add(new SupplierEntity(new Supplier("Home Center", "3147215698", "701506489")));
        nonReactiveStreamOfSuppliers.add(new SupplierEntity(new Supplier("STANLEY Tools", "3177240923", "5042387453")));
        nonReactiveStreamOfSuppliers.add(new SupplierEntity(new Supplier("Vinicryl", "3027960103", "941574326")));
        this.supplierRepository.saveAll(nonReactiveStreamOfSuppliers).subscribe(result ->{
            LogManager.getLogger(this.getClass()).info("-----------Saving Suppliers Seed-----------");
        });
        LogManager.getLogger(this.getClass()).info("-----------Suppliers Seed Successfully Loaded-----------\n");

        LogManager.getLogger(this.getClass()).info("-------------------------------------------------");

        LogManager.getLogger(this.getClass()).info("-----------Loading Seed Bills-----------");
        List<BillEntity> nonReactiveStreamOfBills = new ArrayList<>();
        /*
        nonReactiveStreamOfBills.add(new BillEntity(new Bill(
                this.productRepository.findAll()
                        .filter(productEntity -> "Pintura Blanca".equals(productEntity.getNombreProducto()))
                        .map(productEntity -> productEntity.toProduct()),
                this.employeeRepository.findEmployeeEntityByCedulaVendedor("1152963541").get()
                .map(employeeEntity -> employeeEntity.toEmployee()),
                this.clientRepository.findById(nonReactiveStreamOfClients.get(2).getId())//Cliente Sergio Suarez
                .map(clientEntity -> clientEntity.toClient()), Double.valueOf(85500)
        )));
        nonReactiveStreamOfBills.add(new BillEntity(new Bill(
                this.productRepository.findAll()
                        .filter(productEntity -> "Martillo".equals(productEntity.getNombreProducto()))
                        .map(productEntity -> productEntity.toProduct()),
                this.employeeRepository.findEmployeeEntityByCedulaVendedor("1152963541").get()
                        .map(employeeEntity -> employeeEntity.toEmployee()),
                this.clientRepository.findById(nonReactiveStreamOfClients.get(3).getId())//Cliente Jaime Isaza
                        .map(clientEntity -> clientEntity.toClient()), Double.valueOf(60000)
        )));
         */
        nonReactiveStreamOfBills.add(new BillEntity(new Bill(
                Flux.just(nonReactiveStreamOfProduct.get(1).toProduct(),
                        nonReactiveStreamOfProduct.get(2).toProduct()),
                Mono.just(nonReactiveStreamOfEmployees.get(0).toEmployee()),
                Mono.just(nonReactiveStreamOfClients.get(2).toClient()), Double.valueOf(85500))));
        nonReactiveStreamOfBills.add(new BillEntity(new Bill(
                Flux.just(nonReactiveStreamOfProduct.get(3).toProduct()),
                Mono.just(nonReactiveStreamOfEmployees.get(0).toEmployee()),
                Mono.just(nonReactiveStreamOfClients.get(3).toClient()), Double.valueOf(60000))));
        this.billRepository.saveAll(nonReactiveStreamOfBills).subscribe(result ->{
            LogManager.getLogger(this.getClass()).info("-----------Saving Seed Bill-----------");
        });
        LogManager.getLogger(this.getClass()).info("-----------Seed Bill Successfully Loaded-----------\n");

        LogManager.getLogger(this.getClass()).info("-------------------------------------------------");

        LogManager.getLogger(this.getClass()).info("-----------Loading Seed Tickets-----------");
        List<TicketEntity> nonReactiveStreamOfTickets = new ArrayList<>();
        /*
        nonReactiveStreamOfTickets.add(new TicketEntity(new Ticket(
                this.supplierRepository.findById(nonReactiveStreamOfSuppliers.get(3).getId())//Supplier Vinicryl
                        .map(supplierEntity -> supplierEntity.toSupplier()),
                this.productRepository.findAll()
                        .filter(productEntity -> "Pintura Verde".equals(productEntity.getNombreProducto()))
                        .map(productEntity -> productEntity.toProduct())
        )));
         */
        nonReactiveStreamOfTickets.add(new TicketEntity(new Ticket(
                Mono.just(nonReactiveStreamOfSuppliers.get(3).toSupplier()),
                Flux.just(nonReactiveStreamOfProduct.get(0).toProduct())
        )));
        this.ticketRepository.saveAll(nonReactiveStreamOfTickets).subscribe(result ->{
            LogManager.getLogger(this.getClass()).info("-----------Saving Tickets Seed-----------");
        });
        //LogManager.getLogger(this.getClass()).info("Tickets saved: " + tickets);
        LogManager.getLogger(this.getClass()).info("-----------Tickets Seed Successfully Loaded-----------\n");

        LogManager.getLogger(this.getClass()).info("-----------Store Successfully Loaded With Seeds-----------\n");
    }

    public void deleteAllSeedsInDatabase(){
        LogManager.getLogger(this.getClass()).info("-----------Starting Deleting Seeds In DataBase-----------\n");
        LogManager.getLogger(this.getClass()).info("-----------Deleting Tickets Seeds-----------");
        this.ticketRepository.deleteAll();
        LogManager.getLogger(this.getClass()).info("-----------Tickets Seeds Deleted-----------\n");
        LogManager.getLogger(this.getClass()).info("-----------Deleting Bill Seeds-----------");
        this.billRepository.deleteAll();
        LogManager.getLogger(this.getClass()).info("-----------Bill Seeds Deleted-----------\n");
        LogManager.getLogger(this.getClass()).info("-----------Deleting Supplier Seeds-----------");
        this.supplierRepository.deleteAll();
        LogManager.getLogger(this.getClass()).info("-----------Bill Seeds Deleted-----------\n");
        LogManager.getLogger(this.getClass()).info("-----------Deleting Product Seeds-----------");
        this.productRepository.deleteAll();
        LogManager.getLogger(this.getClass()).info("-----------Product Seeds Deleted-----------\n");
        LogManager.getLogger(this.getClass()).info("-----------Deleting Employee Seeds-----------");
        this.employeeRepository.deleteAll();
        LogManager.getLogger(this.getClass()).info("-----------Employee Seeds Deleted-----------\n");
        LogManager.getLogger(this.getClass()).info("-----------Deleting Client Seeds-----------");
        this.clientRepository.deleteAll();
        LogManager.getLogger(this.getClass()).info("-----------Client Seeds Deleted-----------\n");
        LogManager.getLogger(this.getClass()).info("-----------Seeds In DataBase Deleted-----------");
    }
}
