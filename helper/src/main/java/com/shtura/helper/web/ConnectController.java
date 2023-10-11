package com.shtura.helper.web;

import java.sql.DatabaseMetaData;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.security.Principal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
import com.shtura.helper.dao.jdbc.HibernateSessionFactoryUtil;
import com.shtura.helper.entity.helperdb.ConnectionIPAdress;
import com.shtura.helper.entity.aptekajet.UserDB;
import com.shtura.helper.entity.helperdb.UserDBForCurrentUser;
import com.shtura.helper.parser.Parser;
import com.shtura.helper.repositories.helperdb.ConnectionIPAdressRepository;
import com.shtura.helper.repositories.helperdb.ConnectionNameOrganizationRepository;
import com.shtura.helper.repositories.helperdb.UserDBForCurrentUserRepository;
import com.shtura.helper.repositories.helperdb.UserDBRepository;
import com.shtura.helper.service.aptekajet.CheckPositionService;
import com.shtura.helper.service.aptekajet.CheckService;
import com.shtura.helper.web.dto.ConnectDto;


@Controller
public class ConnectController {
    
    @Autowired
    private UserDBForCurrentUserRepository userDBForCurrentUserRepository;
    
    CheckPositionService checkPositionService = new CheckPositionService();
    CheckService checkService = new CheckService();
    
    @Autowired
    UserDBRepository userDBRepository;
    @Autowired
    ConnectionNameOrganizationRepository connectionNameOrganizationRepository;
    @Autowired
    ConnectionIPAdressRepository connectionIPAdressRepository;

    @RequestMapping("/connect")
    public String login() {
        System.out.println("--------------------- WELCOM TO CONNECT ---------------------");
        return "connect";
    }

    @ModelAttribute("getConnectionNameOrganization")
    public List<String> getConnectionNameOrganization() {
        return (List<String>) this.connectionNameOrganizationRepository.findAllNames();
    }

    @ModelAttribute("getUserDBs")
    public List<UserDB> getUserDB() {
        return (List<UserDB>) this.userDBRepository.findAll();
    }
    
    @RequestMapping(value = "/aptekas")
    @ResponseBody
    public Set getAptekas(@RequestParam String organization) {
        
        if(organization.isEmpty()) {
            Map<String, Set<ConnectionIPAdress>> mapForSelectpicker = new HashMap<String, Set<ConnectionIPAdress>>();
            Set<ConnectionIPAdress> setAptekaNames = new HashSet<>();
            
            ConnectionIPAdress defoltItems = new ConnectionIPAdress();
            defoltItems.setIp("");
            defoltItems.setName("Необходимо выбрать организацию");
            
            setAptekaNames.add(defoltItems);
            mapForSelectpicker.put(organization, setAptekaNames);
            
            return mapForSelectpicker.get(organization);
        }
        
        Map<String, Set<ConnectionIPAdress>> mapForSelectpicker = new HashMap<String, Set<ConnectionIPAdress>>();
        
        List<ConnectionIPAdress> connectionIPAdress = (List<ConnectionIPAdress>) connectionIPAdressRepository.findByConnectionIPAdressID(connectionNameOrganizationRepository.findByName(organization).get(0).getId());
        
        Set<ConnectionIPAdress> setAptekaNames = new LinkedHashSet<>();
        
        ConnectionIPAdress defoltItems = new ConnectionIPAdress();
        defoltItems.setIp("");
        defoltItems.setName("Выберите необходимую аптеку");
        
        setAptekaNames.add( defoltItems );
        
        for(int i = 0; i < connectionIPAdress.size(); i++) {
            setAptekaNames.add(connectionIPAdress.get(i));
        }
        
        mapForSelectpicker.put(organization, setAptekaNames);
        
        return mapForSelectpicker.get(organization);
    }
    
    @GetMapping("/connect")
    public String showAddPersonForm123(Model model) {
        //return new ModelAndView("redirect:/transference");
        model.addAttribute("connectDto", new ConnectDto());
        return "connect";
    }

    @PostMapping("/connect")
    public ModelAndView getConnectIPAndPass(@ModelAttribute("connectDto") @Valid ConnectDto connectDto, BindingResult result, Principal user) {
        
        System.out.println(connectDto.toString());
        
        if (result.hasErrors()) {
            return new ModelAndView("connect", "connectDto", connectDto);
        }
        
        String connectionIPAdress = connectDto.getIpForConnect();
        String userDb = connectDto.getSelectedUserDb();
        
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String username;
        
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
            } else {
            username = principal.toString();
        }
        
        UserDB userDB = userDBRepository.findByName(userDb);
        
        UserDBForCurrentUser userDBForCurrentUser = new UserDBForCurrentUser();
        
        try{
            userDBForCurrentUser = userDBForCurrentUserRepository.findByUsername(username);
            
            System.out.println(userDBForCurrentUser.toString());

        }
        catch (NullPointerException e) {
            System.out.println("--------------------- ConnectController ---------------------");
            System.out.println("--------------------- NullPointerException ---------------------");
            
            userDBForCurrentUser = new UserDBForCurrentUser();

        }
        catch (Exception e) {
            System.out.println("--------------------- ConnectController ---------------------");
            System.out.println("--------------------- Exception ---------------------");
            
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            
            ErrorController.setErrorString(errors.toString());
            
            e.printStackTrace();
            return new ModelAndView("redirect:/myerror");
        }

        userDBForCurrentUser.setUsername(username);
        
        userDBForCurrentUser.setConnectionIPAdress(connectionIPAdress);
        userDBForCurrentUser.setConnectionUserDB(userDB.getName());
        userDBForCurrentUser.setConnectionPassDB(userDB.getPass());
        
        userDBForCurrentUserRepository.save(userDBForCurrentUser);
        
        HibernateSessionFactoryUtil.setConnectionIPAdress(connectionIPAdress);
        HibernateSessionFactoryUtil.setConnectionUserDB(userDB.getName());
        HibernateSessionFactoryUtil.setConnectionPassDB(userDB.getPass());
        
        return new ModelAndView("redirect:/transference");
        
    }
}
