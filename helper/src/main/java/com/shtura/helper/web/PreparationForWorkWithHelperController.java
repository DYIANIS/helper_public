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
import com.shtura.helper.web.dto.PreparationForWorkWithHelperDto;

@Controller
public class PreparationForWorkWithHelperController {

    @RequestMapping("/preparationForWorkWithHelper")
    public String login() {
        System.out.println("--------------------- WELCOM TO preparationForWorkWithHelper ---------------------");
        return "preparationForWorkWithHelper";
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

    @GetMapping("/preparationForWorkWithHelper")
    public String showAddPersonForm(Model model) {
        model.addAttribute("preparationForWorkWithHelperDto", new PreparationForWorkWithHelperDto());
        return "preparationForWorkWithHelper";
    }

    @PostMapping("/preparationForWorkWithHelper")
    public ModelAndView startWorkingWineWithHelper(@ModelAttribute("preparationForWorkWithHelperDto") @Valid PreparationForWorkWithHelperDto preparationForWorkWithHelperDto, BindingResult result, Principal user) {
        
        if (result.hasErrors()) {
            return new ModelAndView("preparationForWorkWithHelper", "preparationForWorkWithHelperDto", preparationForWorkWithHelperDto);
        }
        
        /*
         *          For DEMONSTRATION
         * */
        if(SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(Role.DEMONSTRATION)) {
            return new ModelAndView("redirect:/preparationForWorkWithHelper");
        }
        
        JschService jschService = new JschService();
        
        try {
            jschService.runPlaybook("start_Working_Wine_With_Helper.yml", "", preparationForWorkWithHelperDto.getAnsibleHost());
            
        } catch (Exception e) {
            e.printStackTrace();
            
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            
            ErrorController.setErrorString(errors.toString());
            
            return new ModelAndView("redirect:/myerror");
        }
        
        return new ModelAndView("redirect:/preparationForWorkWithHelper");
        
    }
}
