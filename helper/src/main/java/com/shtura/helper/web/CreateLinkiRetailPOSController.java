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
import com.shtura.helper.web.dto.CreateLinkiRetailPOSDto;

@Controller
public class CreateLinkiRetailPOSController {

    @RequestMapping("/createLinkiRetailPOS")
    public String login() {
        System.out.println("--------------------- WELCOM TO CreateLinkiRetailPOSController ---------------------");
        return "createLinkiRetailPOS";
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

    @GetMapping("/createLinkiRetailPOS")
    public String showAddPersonForm123(Model model) {
        model.addAttribute("createLinkiRetailPOSDto", new CreateLinkiRetailPOSDto());
        return "createLinkiRetailPOS";
    }

    @PostMapping("/createLinkiRetailPOS")
    public ModelAndView getSendMessage(@ModelAttribute("createLinkiRetailPOSDto") @Valid CreateLinkiRetailPOSDto createLinkiRetailPOSDto, BindingResult result, Principal user) {
        
        if (result.hasErrors()) {
            return new ModelAndView("createLinkiRetailPOS", "createLinkiRetailPOSDto", createLinkiRetailPOSDto);
        }
        
        /*
         *          For DEMONSTRATION
         * */
        if(SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(Role.DEMONSTRATION)) {
            return new ModelAndView("redirect:/createLinkiRetailPOS");
        }
        
        JschService jschService = new JschService();
        
        try {
            if(createLinkiRetailPOSDto.getiRetailPosPath().equals("farmin")) {
                jschService.runPlaybook("create_LNK_POS_On_DesktopFromFarmin.yml", "", createLinkiRetailPOSDto.getAnsibleHost());
            } else if(createLinkiRetailPOSDto.getiRetailPosPath().equals("itpharma")) {
                jschService.runPlaybook("create_LNK_POS_On_DesktopFromItpharma.yml", "", createLinkiRetailPOSDto.getAnsibleHost());
            }            
            
        } catch (Exception e) {
            e.printStackTrace();
            
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            
            ErrorController.setErrorString(errors.toString());
            
            return new ModelAndView("redirect:/myerror");
        }
        
        return new ModelAndView("redirect:/createLinkiRetailPOS");
        
    }
}
