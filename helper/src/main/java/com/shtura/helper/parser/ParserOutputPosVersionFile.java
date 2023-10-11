package com.shtura.helper.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import java.util.Date;

import com.shtura.helper.entity.helperdb.PosVersion;


public class ParserOutputPosVersionFile {

    String localHostsFile = "src/main/resources/ansible/file/shtura/posVersion/outputPosVersion";
    
    public ParserOutputPosVersionFile() {
        
    }
    
    public List<PosVersion> getPosVersionList() throws Exception {
        
        File myFile = new File(localHostsFile);
        FileReader fileReader = new FileReader(myFile);
        BufferedReader reader = new BufferedReader(fileReader);
        
        List<PosVersion> ansibleGroupList = new ArrayList<PosVersion>();
        
        String line = "";
        while ((line = reader.readLine()) != null) {

            String[] posVersionfields = line.split("\\|");
            
            PosVersion posVersion = new PosVersion();
            
            posVersion.setInventoryHostname(posVersionfields[0]);
            posVersion.setHostIP(posVersionfields[1]);
            posVersion.setHostPort(posVersionfields[2]);
            posVersion.setFarminVersion(posVersionfields[3]);
            posVersion.setItpharmaVersion(posVersionfields[4]);
            posVersion.setStatus(posVersionfields[5]);
            posVersion.setLaunchDirectory(posVersionfields[6]);
            
            posVersion.setHostType("касса");
            
            DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
            Date date = new Date();
            
            posVersion.setLastSynchronizationTime(dateFormat.format(date));
            
            ansibleGroupList.add(posVersion);
        }
        
        return ansibleGroupList;
    }
}
