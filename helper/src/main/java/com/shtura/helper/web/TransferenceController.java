package com.shtura.helper.web;

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
import com.shtura.helper.entity.aptekajet.Seller;
import com.shtura.helper.entity.aptekajet.Сheck;
import com.shtura.helper.service.aptekajet.CheckService;
import com.shtura.helper.service.aptekajet.CheckPositionService;
import com.shtura.helper.service.aptekajet.SellerService;
import com.shtura.helper.service.aptekajet.TConnectionStringMainServerService;
import com.shtura.helper.web.dto.TransferenceDto;

@Controller
public class TransferenceController {
    
    CheckService checkService = new CheckService();
    SellerService sellerService = new SellerService();
    CheckPositionService checkPositionService = new CheckPositionService();
    TConnectionStringMainServerService tConnectionStringMainServerService = new TConnectionStringMainServerService();

    @RequestMapping("/transference")
    public String login() {
        System.out.println("--------------------- WELCOM TO TRANSFERENCE ---------------------");
        return "transference";
    }
    
    @ModelAttribute("getTradePointName")
    public String getTradePointName() {
        return tConnectionStringMainServerService.getTradePointName();
    }
    
    @ModelAttribute("getStartSellers")
    public List<Seller> getStartSellers() {
        SellerService sellerService = new SellerService();
        return (List<Seller>) sellerService.findAllSeller();
    }
    
    @ModelAttribute("getEndSellers")
    public List<Seller> getEndSellers() {
        SellerService sellerService = new SellerService();
        return (List<Seller>) sellerService.findAllSeller();
    }
    
    @ModelAttribute("getRegistrars")
    public List<String> getRegistrars() {
        
        List<String> frList = checkService.getAllFR();
        return (List<String>) frList;
    }

    @GetMapping("/transference")
    public String showAddPersonForm(Model model) {
        //return new ModelAndView("redirect:/transference");
        model.addAttribute("transferenceDto", new TransferenceDto());
        return "transference";
    }
    
    @PostMapping("/transference")
    public ModelAndView transferenceСhecks(@ModelAttribute("transferenceDto") @Valid TransferenceDto transferenceDto, BindingResult result, Principal user) {
        
        System.out.println(transferenceDto.toString());
        
        if (result.hasErrors()) {
            return new ModelAndView("transference", "transferenceDto", transferenceDto);
        }
        
        String startCheckNumber = transferenceDto.getStartCheckNumber();
        String endCheckNumber = transferenceDto.getEndCheckNumber();
        String selectedStartSeller = transferenceDto.getSelectedStartSeller();
        String selectedEndSeller = transferenceDto.getSelectedEndSeller();
        String selectedRegistrar = transferenceDto.getSelectedRegistrar();
        String startTime = transferenceDto.getStartTime();
        String endTime = transferenceDto.getEndTime();
        String selectedDate = transferenceDto.getSelectedDate();
        
        Integer startCheckKey = null;
        Integer endCheckKey = null;
        Seller fromSeller = null;
        Seller toSeller = null;
        String onRegistrars = selectedRegistrar;
        String startDateTime = "";
        String endDateTime = "";
        
        //--------------------- error checking AND data validation ---------------------
        /*
         * 
         * 
         * 
         * 
         * */
        
        //Error не возможно определить начальный id чека так как не задан не номер не время 
        if (startCheckNumber.isEmpty() && startTime.isEmpty()) {
            System.out.println("--------------------- transferenceController ---------------------");
            System.out.println("--------------------- NullPointerException ---------------------");
            System.out.println("------------- startCheckNumber && startTime IS Empty -------------");
            
            StringWriter errors = new StringWriter();
            
            errors.write("NullPointerException" + System.lineSeparator());
            errors.write("Number of the first check is not set" + System.lineSeparator());
            errors.write("Time of first check is not set" + System.lineSeparator());
            errors.write("----------------------------------------" + System.lineSeparator());
            errors.write("Сomments: enter the number or time of the first check!");
            
            ErrorController.setErrorString(errors.toString());
            
            return new ModelAndView("redirect:/myerror");
        }
        
        //Error не возможно определить конечный номер чека так как не задан не сам номер не время 
        if (endCheckNumber.isEmpty() &&  endTime.isEmpty()) {
            System.out.println("--------------------- transferenceController ---------------------");
            System.out.println("--------------------- NullPointerException ---------------------");
            System.out.println("------------- endCheckNumber && endTime IS Empty -------------");
            
            StringWriter errors = new StringWriter();
            
            errors.write("NullPointerException" + System.lineSeparator());
            errors.write("Number of the last check is not set" + System.lineSeparator());
            errors.write("Time of last check is not set" + System.lineSeparator());
            errors.write("----------------------------------------" + System.lineSeparator());
            errors.write("Сomments: enter the number or time of the last check!");
            
            ErrorController.setErrorString(errors.toString());
            
            return new ModelAndView("redirect:/myerror");
        }
        
        //Error не указана дата . дата необходимо так как номера чеков на штрихах повторяются каждые 10000 чеков !
        if (selectedDate.isEmpty()) {
            System.out.println("--------------------- transferenceController ---------------------");
            System.out.println("--------------------- NullPointerException ---------------------");
            System.out.println("--------------------- selectedDate IS Empty ---------------------");
            
            StringWriter errors = new StringWriter();
            
            errors.write("NullPointerException" + System.lineSeparator());
            errors.write("The Date is Empty" + System.lineSeparator());
            errors.write("----------------------------------------" + System.lineSeparator());
            errors.write("Сomments: enter date!");
            
            ErrorController.setErrorString(errors.toString());
            
            return new ModelAndView("redirect:/myerror");
        }
        
        //Error не указан продавец на которого необходимо перенести чеки
        if (selectedEndSeller.isEmpty()) {
            System.out.println("--------------------- transferenceController ---------------------");
            System.out.println("--------------------- NullPointerException ---------------------");
            System.out.println("-------------------- selectedEndSeller IS Empty --------------------");
            
            StringWriter errors = new StringWriter();
            
            errors.write("NullPointerException" + System.lineSeparator());
            errors.write("EndSeller is Empty" + System.lineSeparator());
            errors.write("----------------------------------------" + System.lineSeparator());
            errors.write("Сomments: enter EndSeller!");
            
            ErrorController.setErrorString(errors.toString());
            
            return new ModelAndView("redirect:/myerror");
        }
        
        /**
         * 
         * Требуется переписать логику так как номер чека может быть указан отличный от нужного. Например :
         * указан начальный номер и несколько ФР. понятно связка начальный номер + ФР совпадут только для одного ФР 
         * 
         */
        
        // если начальный номер чека указан ищем в БД нужный startCheckKey
        if (!startCheckNumber.isEmpty()) {
            List<Сheck> checkList = checkService.findByNumberWisoutNeedRegistrar(startCheckNumber, selectedDate);
            
            if (!checkList.isEmpty()) {
                startCheckKey = checkList.get(0).getKey();
            }else {
                //Error
                if (checkList.isEmpty()) {
                    System.out.println("--------------------- transferenceController ---------------------");
                    System.out.println("--------------------- NullPointerException ---------------------");
                    System.out.println("---- startCheckKey not find by startCheckNumber for needDate ----");
                    
                    StringWriter errors = new StringWriter();
                    
                    errors.write("Error searching startCheckKey by startCheckNumber for enter date!" + System.lineSeparator());
                    errors.write("startCheckNumber='" + startCheckNumber + "'   needDate='" + selectedDate +  "'" + System.lineSeparator());
                    
                    errors.write("----------------------------------------" + System.lineSeparator());
                    errors.write("Сomments: SQL did not find startCheckKey by startCheckNumber for enter date! Try to find the check in manual mode ");
                    
                    ErrorController.setErrorString(errors.toString());
                    
                    return new ModelAndView("redirect:/myerror");
                }
            }
        }
        
        /*
         *          For DEMONSTRATION
         * */
        if(SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(Role.DEMONSTRATION)) {
            return new ModelAndView("redirect:/transference");
        }
        
        /**
         * 
         * Требуется переписать логику так как номер чека может быть указан отличный от нужного. Например :
         * указан начальный номер и несколько ФР. понятно связка начальный номер + ФР совпадут только для одного ФР 
         * 
         */
        
        // если конечный номер чека указан ищем в БД нужный endCheckKey
        if (!endCheckNumber.isEmpty()) {
            List<Сheck> checkList = checkService.findByNumberWisoutNeedRegistrar(endCheckNumber, selectedDate);
            
            if (!checkList.isEmpty()) {
                endCheckKey = checkList.get(checkList.size() -1).getKey();
            }else {
                //Error
                if (checkList.isEmpty()) {
                    System.out.println("--------------------- transferenceController ---------------------");
                    System.out.println("--------------------- NullPointerException ---------------------");
                    System.out.println("---- endCheckKey not find by endCheckNumber for needDate ----");
                    
                    StringWriter errors = new StringWriter();
                    
                    errors.write("Error searching endCheckKey by endCheckNumber for enter date!" + System.lineSeparator());
                    errors.write("endCheckNumber='" + endCheckNumber + "'   needDate='" + selectedDate +  "'" + System.lineSeparator());
                    
                    errors.write("----------------------------------------" + System.lineSeparator());
                    errors.write("Сomments: SQL did not find endCheckKey by endCheckNumber for enter date! Try to find the check in manual mode ");
                    
                    ErrorController.setErrorString(errors.toString());
                    
                    return new ModelAndView("redirect:/myerror");
                }
            }
        }
        
        if (!selectedDate.isEmpty() && !startTime.isEmpty())
            startDateTime = selectedDate + " " + startTime;
        
        if (!selectedDate.isEmpty() && !endTime.isEmpty())
            endDateTime = selectedDate + " " + endTime;
        
        //Если начальный номер чека не указан но указано время. определим startCheckKey по дате и времени
        if (startCheckNumber.isEmpty() && !startDateTime.isEmpty()  ) {
            List<Сheck> checkList = checkService.findStartСhecksByDateTime(startDateTime);
            
            if (!checkList.isEmpty()) {
                startCheckKey = checkList.get(0).getKey();
            }else {
                //Error
                if (checkList.isEmpty()) {
                    System.out.println("--------------------- transferenceController ---------------------");
                    System.out.println("--------------------- NullPointerException ---------------------");
                    System.out.println("-- checkService.findStartСhecksByDateTime(startDateTime) IS Empty --");
                    
                    StringWriter errors = new StringWriter();
                    
                    errors.write("Error searching startCheckKey by dateTime!" + System.lineSeparator());
                    errors.write("The number of the first check is not set" + System.lineSeparator());
                    errors.write("StartCheckKey was searched by DateTime." + System.lineSeparator());
                    errors.write("----------------------------------------" + System.lineSeparator());
                    errors.write("Сomments: SQL did not find startCheckKey by DateTime! Сheck the entered data or time");
                    
                    ErrorController.setErrorString(errors.toString());
                    
                    return new ModelAndView("redirect:/myerror");
                }
            }
        }
        
      //Если конечный номер чека не указан но указано время. определим endCheckKey по дате и времени
        if (endCheckNumber.isEmpty() && endDateTime != "") {
            List<Сheck> checkList = checkService.findEndСhecksByDateTime(endDateTime);
            
            if (!checkList.isEmpty()) {
                endCheckKey = checkList.get(0).getKey();
            }else {
                //Error
                if (checkList.isEmpty()) {
                    System.out.println("--------------------- transferenceController ---------------------");
                    System.out.println("--------------------- NullPointerException ---------------------");
                    System.out.println("-- checkService.findEndСhecksByDateTime(endDateTime) IS Empty --");
                    
                    StringWriter errors = new StringWriter();
                    
                    errors.write("Error searching endCheckKey by dateTime!" + System.lineSeparator());
                    errors.write("The number of the last check is not set" + System.lineSeparator());
                    errors.write("EndCheckKey was searched by DateTime." + System.lineSeparator());
                    errors.write("----------------------------------------" + System.lineSeparator());
                    errors.write("Сomments: SQL did not find endCheckKey by DateTime! Сheck the entered DateTime");
                    
                    ErrorController.setErrorString(errors.toString());
                    
                    return new ModelAndView("redirect:/myerror");
                }
            }
        }
        
        // если startCheckKey не получилось определить
        if ( startCheckKey == null) {
            System.out.println("--------------------- transferenceController ---------------------");
            System.out.println("--------------------- NullPointerException ---------------------");
            System.out.println("-------------------- startCheckKey IS Empty ----------------------");
            
            StringWriter errors = new StringWriter();
            
            errors.write("NullPointerException" + System.lineSeparator());
            errors.write("startCheckKey=null" + System.lineSeparator());
            errors.write(System.lineSeparator());
            errors.write("StartCheckKey was not found!" + System.lineSeparator());
            errors.write("The program could not get it by StartCheckNumber AND could not get it by DateTime" + System.lineSeparator());
            errors.write("Execution has been aborted. Data has not been changed" + System.lineSeparator());
            errors.write("----------------------------------------" + System.lineSeparator());
            errors.write("Сomments: This is very bad. This is a serious error. Check manually using SQL");
            
            ErrorController.setErrorString(errors.toString());
            
            return new ModelAndView("redirect:/myerror");
        }
        
        // если endCheckKey не получилось определить
        if ( endCheckKey == null) {
            System.out.println("--------------------- transferenceController ---------------------");
            System.out.println("--------------------- NullPointerException ---------------------");
            System.out.println("--------------------- endCheckKey IS Empty -----------------------");
            
            StringWriter errors = new StringWriter();
            
            errors.write("NullPointerException" + System.lineSeparator());
            errors.write("endCheckKey=null" + System.lineSeparator());
            errors.write(System.lineSeparator());
            errors.write("EndCheckKey was not found!" + System.lineSeparator());
            errors.write("The program could not get it by EndCheckNumber AND could not get it by DateTime" + System.lineSeparator());
            errors.write("Execution has been aborted. Data has not been changed" + System.lineSeparator());
            errors.write("----------------------------------------" + System.lineSeparator());
            errors.write("Сomments: This is very bad. This is a serious error. Check manually using SQL");
            
            ErrorController.setErrorString(errors.toString());
        }
        
        // find fromSeller
        if( !sellerService.findByName(selectedStartSeller).isEmpty() ) {
            fromSeller = sellerService.findByName(selectedStartSeller).get(0);
        }
        
        // find toSeller
        if( !sellerService.findByName(selectedEndSeller).isEmpty() ) {
            toSeller = sellerService.findByName(selectedEndSeller).get(0);
        }
        
        //Error не получилось определить конечного продовца
        if ( toSeller == null) {
            System.out.println("--------------------- transferenceController ---------------------");
            System.out.println("--------------------- NullPointerException ---------------------");
            System.out.println("----------------------- toSeller IS null -------------------------");
            
            StringWriter errors = new StringWriter();
            
            errors.write("Error searching for the toSeller!" + System.lineSeparator());
            errors.write("----------------------------------------" + System.lineSeparator());
            errors.write("Сomments: SQL did not find the toSeller! Choose a seller!");
            
            ErrorController.setErrorString(errors.toString());
        }
        
        //prosto print
        System.out.println("-----> startCheckKey='" + startCheckKey.toString()  + "'    endCheckKey='" + endCheckKey.toString() + "' " + " <-----");
        System.out.println("-----> fromSeller='" + fromSeller  + "'    toSeller='" + toSeller + "' " + " <-----");
        System.out.println("-----> onRegistrars='" + onRegistrars  + "' <-----");
        System.out.println("-----> startDateTime='" + startDateTime  + "'    endDateTime='" + endDateTime + "' " + " <-----");
        System.out.println("-----> selectedDate='" + selectedDate  + "' " + " <-----");
        
        //----------------------------- main functionality -----------------------------
        /*
         * 
         * 
         * 
         * 
         * */
        
        List<Сheck> listСhecksForEdit = null;
        
        //Maybe начального юзера игнорируем .. перебиваем со всех юзеров на указанного
        if ( fromSeller == null && toSeller != null) {
            listСhecksForEdit = checkService.findСhecksForEditWisoutFromSeller(startCheckKey, endCheckKey, onRegistrars, selectedDate);
        }
        
        //Maybe перебиваем со указаного начального юзера на указанного конечного
        if ( fromSeller != null && toSeller != null) {
            listСhecksForEdit = checkService.findСhecksForEdit(startCheckKey, endCheckKey, fromSeller.getKey(), selectedRegistrar, selectedDate);
        }
        
        System.out.println("these checks will be changed:");
        //print find cheks for edit
        for (int i = 0; i < listСhecksForEdit.size(); i++) {
            System.out.println("-----> " + i + "   " + listСhecksForEdit.get(i).toString());
        }
        
        for (int i = 0; i < listСhecksForEdit.size(); i++) {
            listСhecksForEdit.get(i).setSeller(toSeller);
        }
        
        for (int i = 0; i < listСhecksForEdit.size(); i++) {
            checkService.updateCheck(listСhecksForEdit.get(i));
        }
        
        List<Integer> listForbiddenСhecksForkickStorage = checkPositionService.findForbiddenСhecksForkickStorage(startCheckKey, endCheckKey);
            
        for (int i = 0; i < listСhecksForEdit.size(); i++) {
            if( !listForbiddenСhecksForkickStorage.contains(listСhecksForEdit.get(i).getKey()) )
            checkService.kickStorage(listСhecksForEdit.get(i).getKey());
        }

        System.out.println("--------------------- BYE FROM TRANSFERENCE ---------------------"); 
        //result.rejectValue("startCheckNumber", "error.user.exists");
        
        return new ModelAndView("transference", "transferenceDto", transferenceDto);
    }   
}
