package com.shtura.helper.web;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shtura.helper.entity.Role;
import com.shtura.helper.parser.ParserAnsibleHostsFile;
import com.shtura.helper.repositories.workWithFile.EditTextFile;
import com.shtura.helper.repositories.workWithFile.StorageService;
import com.shtura.helper.service.ssh.JschService;
import com.shtura.helper.service.ssh.IocomService;
import com.shtura.helper.web.dto.FixIndexFileCorruptDto;
import com.shtura.helper.web.dto.SendMessagDto;
import com.shtura.helper.web.dto.UpdatePosForTestDto;

import java.io.IOException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class UpdatePosForTestController {
    
    private static final String UPLOAD_DIRECTORY = "src/main/resources/ansible/file/shtura/updatePosForTest/";
    
    @RequestMapping("/updatePosForTest")
    public String login() {
        System.out.println("--------------------- WELCOM TO UpdatePosForTestController ---------------------");
        return "updatePosForTest";
    }
    
    @PostMapping("/uploadZip")
    public ModelAndView uploadFileToServer(@RequestParam("selectedFile") MultipartFile selectedFile, RedirectAttributes attributes) {
        
        if (selectedFile.isEmpty()) {
            attributes.addFlashAttribute("messageUploadFileToServer", "Please select a file to upload.");
            return new ModelAndView("redirect:/updatePosForTest");
        }
        
        /*
         *          For DEMONSTRATION
         * */
        if(SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(Role.DEMONSTRATION)) {
            return new ModelAndView("redirect:/updatePosForTest");
        }

        String fileName = StringUtils.cleanPath(selectedFile.getOriginalFilename());
        
        StorageService storageService = new StorageService();

        try {
            storageService.save(UPLOAD_DIRECTORY, selectedFile);
        } catch (Exception e) {
            System.out.println("--------------------- UpdatePosForTestController ---------------------");
            System.out.println("----------------- ERROR upload File To Server -----------------");
            System.out.println("- PROBLEM WITH FILE: '"+ selectedFile.getName() + "' -");
            
            e.printStackTrace();
            
            StringWriter errors = new StringWriter();
            
            e.printStackTrace(new PrintWriter(errors));
            
            ErrorController.setErrorString(errors.toString());
            
            return new ModelAndView("redirect:/myerror");
        }

        attributes.addFlashAttribute("messageUploadFileToServer", "You successfully uploaded " + fileName + '!');
        
        JschService jschService = new JschService();
        IocomService iocomService = new IocomService();
        
        String localFile = "src/main/resources/ansible/file/shtura/updatePosForTest/" + selectedFile.getOriginalFilename();
        String remoteFile = "/etc/ansible/file/shtura/updatePosForTest/newPosArchive.zip";
        
        try {            
            iocomService.putFile(localFile, remoteFile);
            //jschService.runPlaybook("send_Message_To_Wine.yml", sendMessagesDto.getAnsibleHost());
        } catch (Exception e) {

            e.printStackTrace();
            
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            
            ErrorController.setErrorString(errors.toString());
            
            return new ModelAndView("redirect:/myerror");
        }

        return new ModelAndView("redirect:/updatePosForTest");
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
    
    @GetMapping("/updatePosForTest")
    public String updatePosForTest123(Model model) {
        model.addAttribute("updatePosForTestDto", new UpdatePosForTestDto());
        return "updatePosForTest";
    }
    
    @PostMapping("/updatePosForTest")
    public ModelAndView updatePosForTest(@ModelAttribute("updatePosForTestDto") @Valid UpdatePosForTestDto updatePosForTestDto, BindingResult result, Principal user) {
        
        if(SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(Role.DEMONSTRATION)) {
            return new ModelAndView("redirect:/updatePosForTest");
        }
        
        System.out.println(updatePosForTestDto.toString());
        
        if (result.hasErrors()) {
            return new ModelAndView("updatePosForTest", "updatePosForTestDto", updatePosForTestDto);
        }
        
        JschService jschService = new JschService();
        
        try {
            jschService.runPlaybook("updatePosForTest.yml", "", updatePosForTestDto.getAnsibleHost());
        } catch (Exception e) {
            e.printStackTrace();
            
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            
            ErrorController.setErrorString(errors.toString());
            
            return new ModelAndView("redirect:/myerror");
        }
        
        return new ModelAndView("redirect:/updatePosForTest");
        
    }
    

}
