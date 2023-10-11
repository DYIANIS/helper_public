package com.shtura.helper.service.parser;

import javax.annotation.Resource;

import com.shtura.helper.parser.StorageController;

public class StorageControllerService {

    public Resource loadAsResource(String filename) {
        StorageController storageController = new StorageController();
        
        return storageController.loadAsResource(filename);
    }
}
