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
import com.shtura.helper.web.dto.FixIndexFileCorruptDto;
import com.shtura.helper.web.dto.InstallCloudClientDto;

@Controller
public class InstallCloudClientController {

    @RequestMapping("/installCloudClient")
    public String login() {
        System.out.println("--------------------- WELCOM TO InstallCloudClient ---------------------");
        return "installCloudClient";
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

    @GetMapping("/installCloudClient")
    public String showAddPersonForm(Model model) {
        model.addAttribute("installCloudClientDto", new InstallCloudClientDto());
        return "installCloudClient";
    }

    @PostMapping("/installCloudClient")
    public ModelAndView installCloudClient(@ModelAttribute("installCloudClientDto") @Valid InstallCloudClientDto installCloudClientDto, BindingResult result, Principal user) {
        
        if (result.hasErrors()) {
            return new ModelAndView("installCloudClient", "installCloudClientDto", installCloudClientDto);
        }
        
        /*
         *          For DEMONSTRATION
         * */
        if(SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(Role.DEMONSTRATION)) {
            return new ModelAndView("redirect:/installCloudClient");
        }
        
        JschService jschService = new JschService();
        
        try {
            jschService.runPlaybook("install_Git_and_fix_CAFile.yml", "", installCloudClientDto.getAnsibleHost());
            jschService.runPlaybook("install_ircc.yml", "", installCloudClientDto.getAnsibleHost());
            
        } catch (Exception e) {
            e.printStackTrace();
            
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            
            ErrorController.setErrorString(errors.toString());
            
            return new ModelAndView("redirect:/myerror");
        }
        
        return new ModelAndView("redirect:/installCloudClient");
        
    }
}
