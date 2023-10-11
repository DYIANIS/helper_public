package com.shtura.helper.repositories.workWithFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

public class StorageService {
    
    //private static final String UPLOAD_DIR_Default = "src/main/resources/connections/files/";
    
    public StorageService() {
        
    }
    
    public static void save(String dir, MultipartFile selectedFile) throws Exception {
        
        // normalize the file path
        String fileName = StringUtils.cleanPath(selectedFile.getOriginalFilename());
        
        Path path = Paths.get(dir + fileName);
        
        Files.copy(selectedFile.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
    }
    
    public static List<File> getFilesList(String dir){
        
        File defaultFile = new File(dir);
        File[] arrFiles = defaultFile.listFiles();
        List<File> filesList = Arrays.asList(arrFiles);
        
        return filesList;
    }
    
    public static void delete(String file) throws Exception{
        
        Files.delete(Paths.get(file));
    }
}
