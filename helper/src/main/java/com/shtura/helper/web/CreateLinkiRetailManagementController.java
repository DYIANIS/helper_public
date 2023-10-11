package com.shtura.helper.web;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.shtura.helper.entity.Role;
import com.shtura.helper.parser.ParserAnsibleHostsFile;
import com.shtura.helper.service.ssh.JschService;
import com.shtura.helper.service.ssh.IocomService;
import com.shtura.helper.web.dto.CreateLinkiRetailManagementDto;

@Controller
public class CreateLinkiRetailManagementController {

    @RequestMapping("/createLinkiRetailManagement")
    public String login() {
        System.out.println("--------------------- WELCOM TO CreateLinkiRetailManagementController ---------------------");
        return "createLinkiRetailManagement";
    }

    @ModelAttribute("getAnsibleGroup")
    public List<String> getAnsibleGroup() {
        
        IocomService iocomService = new IocomService();
        
        String localFile = "src/main/resources/ansible/hosts";
        String remoteFile = "/etc/ansible/hosts";
        
        ParserAnsibleHostsFile parserHostsFile = new ParserAnsibleHostsFile();
        
        List<String> ansibleGroupList = null;
        
        try {
            iocomService.getFile(remoteFile, localFile);
        
            ansibleGroupList = parserHostsFile.getAnsibleGroup();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        ansibleGroupList.add(0, "All");
        
        return (List<String>) ansibleGroupList;
    }

    @GetMapping("/createLinkiRetailManagement")
    public String showAddPersonForm123(Model model) {
        model.addAttribute("createLinkiRetailManagementDto", new CreateLinkiRetailManagementDto());
        return "createLinkiRetailManagement";
    }

    @PostMapping("/createLinkiRetailManagement")
    public ModelAndView getSendMessage(@ModelAttribute("createLinkiRetailManagementDto") @Valid CreateLinkiRetailManagementDto createLinkiRetailManagementDto, BindingResult result, Principal user) {
        
        if (result.hasErrors()) {
            return new ModelAndView("createLinkiRetailManagement", "createLinkiRetailManagementDto", createLinkiRetailManagementDto);
        }
        
        /*
         *          For DEMONSTRATION
         * */
        if(SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(Role.DEMONSTRATION)) {
            return new ModelAndView("redirect:/createLinkiRetailManagement");
        }
        
        JschService jschService = new JschService();
        
        try {
            if(createLinkiRetailManagementDto.getiRetailManagementPath().equals("farmin")) {
                jschService.runPlaybook("create_LNK_Management_FromFarmin.yml", "", createLinkiRetailManagementDto.getAnsibleHost());
            } else if(createLinkiRetailManagementDto.getiRetailManagementPath().equals("itpharma")) {
                jschService.runPlaybook("create_LNK_Management_FromItpharma.yml", "", createLinkiRetailManagementDto.getAnsibleHost());
            }            
            
        } catch (Exception e) {
            e.printStackTrace();
            
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            
            ErrorController.setErrorString(errors.toString());
            
            return new ModelAndView("redirect:/myerror");
        }
        
        return new ModelAndView("redirect:/createLinkiRetailManagement");
        
    }
}
