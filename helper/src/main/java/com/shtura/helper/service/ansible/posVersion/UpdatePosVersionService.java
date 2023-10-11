package com.shtura.helper.service.ansible.posVersion;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shtura.helper.entity.helperdb.PosVersion;
import com.shtura.helper.parser.ParserOutputPosVersionFile;
import com.shtura.helper.repositories.helperdb.PosVersionRepository;
import com.shtura.helper.service.ssh.JschService;
import com.shtura.helper.service.ssh.IocomService;

@Component
public class UpdatePosVersionService {
    
    public UpdatePosVersionService() {
        
    }
    
    @Autowired
    private PosVersionRepository posVersionRepository;
    
    public void getOutputPosVersionFile() throws Exception {
        JschService jschService = new JschService();
    
        jschService.runPlaybook("check_and_return_iRetailPOS_version.yml", "--forks 15", "_x64");
    }
    
    public void transerOutputPosVersionFile() throws Exception {
        
        IocomService iocomService = new IocomService();
        
        String localFile = "src/main/resources/ansible/file/shtura/posVersion/outputPosVersion";
        String remoteFileDir = "/tmp/";
        String remoteFileName = "outputPosVersion";
        
        iocomService.getFile(remoteFileDir + remoteFileName, localFile);
        iocomService.delFile(remoteFileDir, remoteFileName);
    }
    
    public void updateDBFromFile() throws Exception {
        List<PosVersion> posVersionList = null;
        
        ParserOutputPosVersionFile parserOutputPosVersionFile = new ParserOutputPosVersionFile();
        posVersionList = parserOutputPosVersionFile.getPosVersionList();
        
        for (int i = 0; i < posVersionList.size(); i++) {
            List<PosVersion> pVL = null;
            
            pVL = (List<PosVersion>) posVersionRepository.findByInventoryHostname(posVersionList.get(i).getInventoryHostname());
            
            if(pVL.size() > 0) {
                System.out.println("2" + pVL.get(0).toString());
                PosVersion posVersion = pVL.get(0);
                
                posVersionList.get(i).setId(posVersion.getId());

            }            
            
        }
        
        posVersionRepository.saveAll(posVersionList);
    }


}
