package com.shtura.helper.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class ParserAnsibleHostsFile {

    String localHostsFile = "src/main/resources/ansible/hosts";
    
    public ParserAnsibleHostsFile() {
        
    }
    
    public List<String> getAnsibleGroup() throws Exception {
        
        File myFile = new File(localHostsFile);
        FileReader fileReader = new FileReader(myFile);
        BufferedReader reader = new BufferedReader(fileReader);
        
        List<String> ansibleGroupList = new ArrayList<String>();
        
        String line = reader.readLine();
        while (!(line = reader.readLine()).isEmpty()) {

            ansibleGroupList.add(line);
        }
        
        return ansibleGroupList;
    }
    
    public List<DataHolderAnsibleHost> getAnsibleHosts(String ansibleGroup) throws Exception {
        
        File myFile = new File(localHostsFile);
        FileReader fileReader = new FileReader(myFile);
        BufferedReader reader = new BufferedReader(fileReader);
        
        List<DataHolderAnsibleHost> ansibleHostList = new ArrayList<DataHolderAnsibleHost>();
        
        String line = reader.readLine();
        while ((line = reader.readLine()) != null) {
            
            if(line.equalsIgnoreCase("[" + ansibleGroup + "]")) {
                ansibleHostList.add(new DataHolderAnsibleHost(ansibleGroup, ansibleGroup));
                
                while (!(line = reader.readLine()).isEmpty()) {
                    String editLine = line.replaceAll("ansible_", "");
                    ansibleHostList.add(new DataHolderAnsibleHost(editLine, editLine));
                }
            }
        }
        
        return (List<DataHolderAnsibleHost>) ansibleHostList;
    }
    
}
