package com.shtura.helper.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.security.Principal;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
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
import com.shtura.helper.entity.helperdb.PosVersion;
import com.shtura.helper.parser.MediaTypeUtils;
import com.shtura.helper.parser.Parser;
import com.shtura.helper.parser.ParserOutputPosVersionFile;
import com.shtura.helper.repositories.helperdb.ConnectionIPAdressRepository;
import com.shtura.helper.repositories.helperdb.ConnectionNameOrganizationRepository;
import com.shtura.helper.repositories.helperdb.PosVersionRepository;
import com.shtura.helper.repositories.helperdb.UserDBRepository;
import com.shtura.helper.repositories.workWithFile.EditTextFile;
import com.shtura.helper.repositories.workWithFile.FileLoadreByUrl;
import com.shtura.helper.repositories.workWithFile.StorageService;
import com.shtura.helper.service.ansible.posVersion.UpdatePosVersionService;
import com.shtura.helper.service.parser.MediaTypeUtilsService;
import com.shtura.helper.service.ssh.JschService;
import com.shtura.helper.web.dto.SendMessagDto;

@Controller
public class PosVersionController {
    
    @Autowired
    private PosVersionRepository posVersionRepository;
    
    @Autowired
    private UpdatePosVersionService updatePosVersionService;

    @RequestMapping("/posVersion")
    public String files() {
        System.out.println("--------------------- WELCOM TO PosVersionController ---------------------");
        return "posVersion";
    }
    
    @ModelAttribute("getPosVersionList")
    public List<PosVersion> getPosVersionList() {
        
        /*try {
          
            updatePosVersionService.getOutputPosVersionFile();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/
        
        /*try {
            updatePosVersionService.transerOutputPosVersionFile();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/
        
        /*try {
            updatePosVersionService.updateDBFromFile();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/
        
        return (List<PosVersion>) posVersionRepository.findAll();
    }
    
    @RequestMapping(value = {"/posVersion"}, method = RequestMethod.POST, params="action=deleteSelected")
    public String deleteSelected(final String[] ids, RedirectAttributes attributes) {

        /*
         *          For DEMONSTRATION
         * */
        if(SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(Role.DEMONSTRATION)) {
            return "redirect:/posVersion";
        }
        
        if (ids == null) {
            return "redirect:/posVersion";
        }

        for (int i = 0; i < ids.length; i++) {

            List<PosVersion> list = (List<PosVersion>) posVersionRepository.findByInventoryHostname(ids[i]);

            if(list.size() > 0) {
                posVersionRepository.deleteById(list.get(0).getId());
            }
        }

        return "redirect:/posVersion";
    }

    @RequestMapping(value = {"/posVersion"}, method = RequestMethod.POST, params="action=checkVersionSelected")
    public String checkVersionSelected(final String[] ids, RedirectAttributes attributes) {

        /*
         *          For DEMONSTRATION
         * */
        if(SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(Role.DEMONSTRATION)) {
            return "redirect:/posVersion";
        }
        
        String hosrMask = "";
        for (int i = ids.length -1; i >= 0; i--) {

            hosrMask += ids[i];
            if (i > 0) {
                hosrMask += ":";
            }
        }

        System.out.println("****** " + hosrMask + " ******");
        
        JschService jschService = new JschService();
        try {
            jschService.runPlaybook("check_and_return_iRetailPOS_version.yml", "--forks 15", hosrMask);
            
            updatePosVersionService.transerOutputPosVersionFile();
            updatePosVersionService.updateDBFromFile();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return "redirect:/posVersion";
    }
    
    @RequestMapping(value = {"/posVersion"}, method = RequestMethod.POST, params="action=checkVersionAll")
    public String checkVersionAll(final String[] ids, RedirectAttributes attributes) {

        /*
         *          For DEMONSTRATION
         * */
        if(SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(Role.DEMONSTRATION)) {
            return "redirect:/posVersion";
        }
        
        String hosrMask = "*_x64";
        
        JschService jschService = new JschService();
        try {
            jschService.runPlaybook("check_and_return_iRetailPOS_version.yml", "--forks 15", hosrMask);
            
            updatePosVersionService.transerOutputPosVersionFile();
            updatePosVersionService.updateDBFromFile();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return "redirect:/posVersion";
    }
    
    @RequestMapping(value = {"/posVersion"}, method = RequestMethod.POST, params="action=checkActualVersion")
    public String checkActualVersion(RedirectAttributes attributes) {
        
        /*
         *          For DEMONSTRATION
         * */
        if(SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(Role.DEMONSTRATION)) {
            return "redirect:/posVersion";
        }

        FileLoadreByUrl fileLoadreByUrl = new FileLoadreByUrl();
        
        fileLoadreByUrl.setUsername("farmin.user");
        fileLoadreByUrl.setPassword("HJnbuyTGB^&*T&*&n");
        
        try {
            fileLoadreByUrl.getFile("https://github.com/DYIANIS/helper/archive/refs/heads/main.zip", "src/main/resources/ansible/file/shtura/posVersion/");
        
            //fileLoadreByUrl.getFile("https://github.com/DYIANIS/helper/archive/refs/heads/main.zip", "src/main/resources/ansible/file/shtura/posVersion/");
            
            
            System.out.println("***** File loated *****");
        } catch (MalformedURLException mue) {
            mue.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/posVersion";
    }

}
