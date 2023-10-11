package com.shtura.helper.web;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shtura.helper.parser.DataHolderAnsibleHost;
import com.shtura.helper.parser.ParserAnsibleHostsFile;

@Controller
public class GetAnsibleHostsByGroupController {

    @RequestMapping(value = "/getAnsibleHostsByGroup")
    @ResponseBody
    public Set<DataHolderAnsibleHost> getAnsibleHosts(@RequestParam String ansibleGroup) {
        
        Map<String, Set<DataHolderAnsibleHost>> mapForSelectpicker = new HashMap<String, Set<DataHolderAnsibleHost>>();
        Set<DataHolderAnsibleHost> setAnsibleHosts = new LinkedHashSet<>();
        
        if(ansibleGroup.isEmpty()) {

            
            DataHolderAnsibleHost defoltItems = new DataHolderAnsibleHost();
            defoltItems.setHostNameValue("");
            defoltItems.setHostName("Необходимо выбрать Ansible группу");
            
            setAnsibleHosts.add(defoltItems);
            mapForSelectpicker.put(ansibleGroup, setAnsibleHosts);
            
            return mapForSelectpicker.get(ansibleGroup);
        }
        
        if(ansibleGroup.equalsIgnoreCase("All")) {
            
            DataHolderAnsibleHost defoltItems = new DataHolderAnsibleHost();
            defoltItems.setHostNameValue("*");
            defoltItems.setHostName("Выбраны все доступные хосты");
            
            setAnsibleHosts.add(defoltItems);
            mapForSelectpicker.put(ansibleGroup, setAnsibleHosts);
            
            return mapForSelectpicker.get(ansibleGroup);
        }
        
        DataHolderAnsibleHost defoltItems = new DataHolderAnsibleHost();
        defoltItems.setHostNameValue("");
        defoltItems.setHostName("Выберите необходимый хост");
        
        setAnsibleHosts.add( defoltItems );
        
        ParserAnsibleHostsFile parserAnsibleHostsFile = new ParserAnsibleHostsFile();
        List<DataHolderAnsibleHost> ansibleHostList = null;
        
        try {
            ansibleHostList = parserAnsibleHostsFile.getAnsibleHosts(ansibleGroup);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        for(int i = 0; i < ansibleHostList.size(); i++) {
            setAnsibleHosts.add(ansibleHostList.get(i));
        }
        
        mapForSelectpicker.put(ansibleGroup, setAnsibleHosts);
        
        return mapForSelectpicker.get(ansibleGroup);
    }
}
