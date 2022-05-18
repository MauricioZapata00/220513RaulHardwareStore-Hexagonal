package com.web.Hardware.adapters.mongodb;

import com.web.Hardware.adapters.mongodb.store.StoreSeederService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DatabaseSeederService {

    private final StoreSeederService storeSeederService;

    @Autowired
    public DatabaseSeederService(StoreSeederService storeSeederService) {
        this.storeSeederService = storeSeederService;
        this.seedStoreDatabase();
        //this.resetSeedStoreDatabase();
    }

    public void seedStoreDatabase(){
        this.storeSeederService.seedDatabase();
    }

    public void deleteStoreDatabase(){
        this.storeSeederService.deleteAllSeedsInDatabase();
    }

    public void resetSeedStoreDatabase(){
        this.deleteStoreDatabase();
        this.seedStoreDatabase();
    }
}
