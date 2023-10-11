package com.shtura.helper.web;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

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

import com.jcraft.jsch.ChannelSftp;
import com.shtura.helper.entity.Role;
import com.shtura.helper.parser.ParserAnsibleHostsFile;
import com.shtura.helper.repositories.ssh.IocomRepository;
import com.shtura.helper.repositories.workWithFile.StorageService;
import com.shtura.helper.service.ssh.JschService;
import com.shtura.helper.service.ssh.IocomService;
import com.shtura.helper.web.dto.CreateLinkiRetailManagementDto;
import com.shtura.helper.web.dto.CreateLinkiRetailPOSDto;
import com.shtura.helper.web.dto.RunPlaybookDto;

@Controller
public class RunPlaybookController {
    
    private static final String UPLOAD_DIRECTORY = "/etc/ansible/yml";
    
    IocomService iocomService = new IocomService();

    @RequestMapping("/runPlaybook")
    public String login() {
        System.out.println("--------------------- WELCOM TO RunPlaybookController ---------------------");
        
        return "runPlaybook";
    }

    @ModelAttribute("getAnsibleGroup")
    public List<String> getAnsibleGroup() {
        
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
        
        try {
            iocomService.getFilesList(UPLOAD_DIRECTORY);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return (List<String>) ansibleGroupList;
    }

    @GetMapping("/runPlaybook")
    public String showAddPersonForm123(Model model) {
        model.addAttribute("runPlaybookDto", new RunPlaybookDto());
        return "runPlaybook";
    }
    
    @ModelAttribute("getFilesList")
    public List<String> getFilesList() {
        IocomRepository iocomRepository = new IocomRepository();
        
        Vector<ChannelSftp.LsEntry> filesList = null;
        try {
            filesList = iocomRepository.getFilesList(UPLOAD_DIRECTORY);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        List<String> returnList = new ArrayList<>();
        
        for(ChannelSftp.LsEntry entry: filesList) {
            returnList.add(entry.getFilename());
        }
        
        return returnList;
    }

    @PostMapping("/runPlaybook")
    public ModelAndView getSendMessage(@ModelAttribute("runPlaybookDto") @Valid RunPlaybookDto runPlaybookDto, BindingResult result, Principal user) {
        
        if (result.hasErrors()) {
            return new ModelAndView("runPlaybook", "runPlaybookDto", runPlaybookDto);
        }
        
        /*
         *          For DEMONSTRATION
         * */
        if(SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(Role.DEMONSTRATION)) {
            return new ModelAndView("redirect:/runPlaybook");
        }
        
        JschService jschService = new JschService();
        
        try {
            jschService.runPlaybook(runPlaybookDto.getPlaybookName(), runPlaybookDto.getOtherParam(), runPlaybookDto.getAnsibleHost());      
            
        } catch (Exception e) {
            e.printStackTrace();
            
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            
            ErrorController.setErrorString(errors.toString());
            
            return new ModelAndView("redirect:/myerror");
        }
        
        return new ModelAndView("redirect:/runPlaybook");
        
    }
}
