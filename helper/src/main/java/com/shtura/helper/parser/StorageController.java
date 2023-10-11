package com.shtura.helper.parser;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.annotation.Resource;

import org.springframework.core.io.AbstractFileResolvingResource;
import org.springframework.core.io.UrlResource;

public class StorageController {
    
    public StorageController() {
        
    }

    public Resource loadAsResource(String filename) {
        try {
            Path uploadLocation = Paths.get("src/main/resources/connectionList/");
            Path file = uploadLocation .resolve(filename);
            Resource resource = (Resource) new UrlResource(file.toUri());
            if (((AbstractFileResolvingResource) resource).exists() || ((AbstractFileResolvingResource) resource).isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read file: " + filename);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Could not read file: " + filename, e);
        }
    }
}
