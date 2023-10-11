package com.shtura.helper.web;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.security.Principal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.shtura.helper.entity.Role;
import com.shtura.helper.parser.DataHolderAnsibleHost;
import com.shtura.helper.parser.ParserAnsibleHostsFile;
import com.shtura.helper.repositories.workWithFile.EditTextFile;

import com.shtura.helper.service.ssh.JschService;
import com.shtura.helper.service.ssh.IocomService;
import com.shtura.helper.web.dto.SendMessagDto;


@Controller
public class SendMessagController {

    @RequestMapping("/sendMessag")
    public String login() {
        System.out.println("--------------------- WELCOM TO SENDMessages ---------------------");
        return "sendMessag";
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

    @GetMapping("/sendMessag")
    public String showAddPersonForm123(Model model) {
        model.addAttribute("sendMessagesDto", new SendMessagDto());
        return "sendMessag";
    }

    @PostMapping("/sendMessag")
    public ModelAndView getSendMessage(@ModelAttribute("sendMessagesDto") @Valid SendMessagDto sendMessagesDto, BindingResult result, Principal user) {
        
        System.out.println(sendMessagesDto.toString());
        
        if (result.hasErrors()) {
            return new ModelAndView("sendMessag", "sendMessagesDto", sendMessagesDto);
        }
        
        /*
         *          For DEMONSTRATION
         * */
        if(SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(Role.DEMONSTRATION)) {
            return new ModelAndView("redirect:/sendMessag");
        }
        
        JschService jschService = new JschService();
        IocomService iocomService = new IocomService();
        
        String localFile = "src/main/resources/ansible/file/shtura/messageToWine/messageToWine.txt";
        String remoteFile = "/etc/ansible/file/shtura/messageToWine/messageToWine.txt";
        
        try {
            EditTextFile.outputToFile(sendMessagesDto.getTextareaField());
            
            iocomService.putFile(localFile, remoteFile);
            jschService.runPlaybook("send_Message_To_Wine.yml", "--forks 15", sendMessagesDto.getAnsibleHost());
        } catch (Exception e) {

            e.printStackTrace();
            
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            
            ErrorController.setErrorString(errors.toString());
            
            return new ModelAndView("redirect:/myerror");
        }
        
        return new ModelAndView("redirect:/sendMessag");
        
    }
}
