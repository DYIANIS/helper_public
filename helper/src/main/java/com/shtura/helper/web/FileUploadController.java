package com.shtura.helper.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shtura.helper.entity.Role;
import com.shtura.helper.entity.helperdb.ConnectionIPAdress;
import com.shtura.helper.parser.MediaTypeUtils;
import com.shtura.helper.parser.Parser;
import com.shtura.helper.repositories.helperdb.ConnectionIPAdressRepository;
import com.shtura.helper.repositories.helperdb.ConnectionNameOrganizationRepository;
import com.shtura.helper.repositories.workWithFile.StorageService;
import com.shtura.helper.service.parser.MediaTypeUtilsService;

@Controller
public class FileUploadController {
    
    private static final String DEFAULT_DIRECTORY = "src/main/resources/connections/";
    private static final String UPLOAD_DIRECTORY = "src/main/resources/connections/files/";
    private static final String DEFAULT_FILE_NAME = "lol.jpg";
    
    MediaTypeUtilsService mediaTypeUtilsService = new MediaTypeUtilsService();
    
    @Autowired
    private ServletContext servletContext;
    
    private final String UPLOAD_DIR = "src/main/resources/connections/files/";
    
    @Autowired
    private ConnectionNameOrganizationRepository connectionNameOrganizationRepository;
    
    @Autowired
    private ConnectionIPAdressRepository connectionIPAdressRepository;

    @RequestMapping("/fileUpload")
    public String files() {
        System.out.println("--------------------- WELCOM TO FileUploadController ---------------------");
        return "fileUpload";
    }

    @PostMapping("/uploadFileToServer")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public String uploadFileToServer(@RequestParam("selectedFile") MultipartFile selectedFile, RedirectAttributes attributes) {
        
        if (selectedFile.isEmpty()) {
            attributes.addFlashAttribute("messageUploadFileToServer", "Please select a file to upload.");
            return "redirect:/fileUpload";
        }
        
        /*
         *          For DEMONSTRATION
         * */
        if(SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(Role.DEMONSTRATION)) {
            return "redirect:/fileUpload";
        }

        String fileName = StringUtils.cleanPath(selectedFile.getOriginalFilename());
        
        StorageService storageService = new StorageService();

        try {
            storageService.save(UPLOAD_DIRECTORY, selectedFile);
        } catch (Exception e) {
            System.out.println("--------------------- FileUploadController ---------------------");
            System.out.println("----------------- ERROR upload File To Server -----------------");
            System.out.println("- PROBLEM WITH FILE: '"+ selectedFile.getName() + "' -");
            
            e.printStackTrace();
            
            StringWriter errors = new StringWriter();
            
            e.printStackTrace(new PrintWriter(errors));
            
            ErrorController.setErrorString(errors.toString());
            
            return "redirect:/myerror";
        }

        attributes.addFlashAttribute("messageUploadFileToServer", "You successfully uploaded " + fileName + '!');

        return "redirect:/fileUpload";
    }
    
    @ModelAttribute("getConnectionsFiles")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<File> getConnectionsFiles() {
        StorageService storageService = new StorageService();
        
        return storageService.getFilesList(UPLOAD_DIRECTORY);
    }
    
    @RequestMapping(value = {"/workWithFiles"}, method = RequestMethod.POST, params="action=deleteFile")
    public String deleteFile(final String[] ids, RedirectAttributes attributes) {

        /*
         *          For DEMONSTRATION
         * */
        if(SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(Role.DEMONSTRATION)) {
            return "redirect:/fileUpload";
        }
        
        StorageService storageService = new StorageService();

        for (String id : ids) {
            if(!id.equals("src/main/resources/connections/files/DefaultConnectionList.xls")){
                try {
                    storageService.delete(id);
                }catch (Exception e) {
                    System.out.println("--------------------- FileUploadController ---------------------");
                    System.out.println("----------------- ERROR DELETE FILE FROM SERVER -----------------");
                    System.out.println("- PROBLEM WITH FILE: '"+ id + "' -");
                    
                    e.printStackTrace();
                    
                    StringWriter errors = new StringWriter();
                    
                    e.printStackTrace(new PrintWriter(errors));
                    
                    ErrorController.setErrorString(errors.toString());
                    
                    return "redirect:/myerror";
                }
            }
            
            if(id.equals("src/main/resources/connections/files/DefaultConnectionList.xls")){
                attributes.addFlashAttribute("messageWorkWithFile", "Please select ather file to delete. You can't delete the default file");
                
                System.out.println("****************************** ERROR ERROR ERROR ERROR ERROR ERROR ERROR ERROR ******************************");
                return "redirect:/fileUpload";
            }
        }
        
        return "redirect:/fileUpload";
    }
    
    @RequestMapping(value = {"/workWithFiles"}, method = RequestMethod.POST, params="action=loadFromFile")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public String uploadFile(final String[] ids) {
        
        /*
         *          For DEMONSTRATION
         * */
        if(SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(Role.DEMONSTRATION)) {
            return "redirect:/fileUpload";
        }

        if(ids.length > 0) {
            
            List<ConnectionIPAdress> listConnectionIPAdressForDelete = (List<ConnectionIPAdress>) connectionIPAdressRepository.findAll();
            for(int i = 0; i < listConnectionIPAdressForDelete.size(); i++) {
                connectionIPAdressRepository.delete(listConnectionIPAdressForDelete.get(i));
            }
            
            try {
                Parser.xlsParser(ids[0]);
            }catch (Exception e) {
                System.out.println("--------------------- FileUploadController ---------------------");
                System.out.println("----------------------- ERROR xlsParser -----------------------");
                
                StringWriter errors = new StringWriter();
                e.printStackTrace(new PrintWriter(errors));
                
                ErrorController.setErrorString(errors.toString());

                e.printStackTrace();
                return "redirect:/myerror";
            }
        }

        return "fileUpload";
    }
    
    @RequestMapping(value = {"/workWithFiles"}, method = RequestMethod.POST, params="action=uploadToFile")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public String compileFile() {
        
        /*
         *          For DEMONSTRATION
         * */
        if(SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(Role.DEMONSTRATION)) {
            return "redirect:/fileUpload";
        }

        try {
            Parser.saveToFileFromDB();
        } catch (Exception e) {
            System.out.println("--------------------- FileUploadController ---------------------");
            System.out.println("--------------- ERROR SAVE TABLE TO FILE FROM DB ---------------");
            
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            
            ErrorController.setErrorString(errors.toString());
            
            
            e.printStackTrace();
            return "redirect:/myerror";
        }

        return "redirect:/fileUpload";
    }
    
    @RequestMapping(value = {"/workWithFiles"}, method = RequestMethod.POST, params="action=downloadFile")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<InputStreamResource> downloadFilew(final String[] ids) {
        
        String fileName = DEFAULT_FILE_NAME;
        String dir = DEFAULT_DIRECTORY;
        
        if (ids != null) {
            fileName = ids[0];
            dir = "";
        }
            
        MediaType mediaType = mediaTypeUtilsService.getMediaTypeForFileName(this.servletContext, fileName);

        File file = new File(dir + fileName);
        InputStreamResource resource = null;
        try {
            resource = new InputStreamResource(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            System.out.println("--------------------- FileUploadController ---------------------");
            System.out.println("--------------- ERROR downloadFilew FILE TO PC ---------------");
            
            e.printStackTrace();
        }

        return ResponseEntity.ok()
                // Content-Disposition
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
                // Content-Type
                .contentType(mediaType)
                // Contet-Length
                .contentLength(file.length()) //
                .body(resource);
    }
    
    /*
     *                                  Use Virtual Page
     *                                  
     *                                  Add To HTML Page
     * <a href="downloadConnectionList" onclick="document.getElementById('myform').download()">Submit</a>
     * 
     * 
    */
    //                                  And use this metod
    /*@RequestMapping("/downloadConnectionList")
    public ResponseEntity<InputStreamResource> downloadFile1(
            @RequestParam(defaultValue = DEFAULT_FILE_NAME) String fileName) throws IOException {

        MediaType mediaType = MediaTypeUtils.getMediaTypeForFileName(this.servletContext, fileName);
        System.out.println("fileName: " + fileName);
        System.out.println("mediaType: " + mediaType);

        File file = new File(DIRECTORY + "/" + fileName);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                // Content-Disposition
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
                // Content-Type
                .contentType(mediaType)
                // Contet-Length
                .contentLength(file.length()) //
                .body(resource);
    }*/
}
