package com.shtura.helper.service.ssh;

import java.util.Vector;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.Session;
import com.shtura.helper.repositories.ssh.IocomRepository;
import com.shtura.helper.service.ssh.JschService;

public class IocomService {
    
    IocomRepository iocomRepository = new IocomRepository();
    
    public IocomService() {
        
    }

    public void putFile(String localFile, String remoteFile) throws Exception {
        
        iocomRepository.putFile(localFile, remoteFile);
    }
    
    public void getFile(String remoteFile, String localFile) throws Exception {
        
        iocomRepository.getFile(remoteFile, localFile);
    }
    
    public void delFile(String remoteDir, String remoteFileName) throws Exception {
        
        iocomRepository.delFile(remoteDir, remoteFileName);
    }
    
    public Vector<ChannelSftp.LsEntry> getFilesList(String remoteDir) throws Exception {
        
        return iocomRepository.getFilesList(remoteDir);
    }
}
