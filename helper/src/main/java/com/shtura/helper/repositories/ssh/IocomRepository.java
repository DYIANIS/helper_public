package com.shtura.helper.repositories.ssh;

import java.util.Vector;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.Session;
import com.shtura.helper.service.ssh.JschService;

public class IocomRepository {

    public void putFile(String localFile, String remoteFile) throws Exception {
        
        JschService jschService = new JschService();

        Session session = jschService.getSession();
        
        session.connect();
        
        ChannelSftp channelSftp = (ChannelSftp) session.openChannel("sftp");
        channelSftp.connect();
        
        channelSftp.put(localFile, remoteFile);
        
        channelSftp.exit();
        session.disconnect();
    }
    
    public void getFile(String remoteFile, String localFile) throws Exception {
        
        JschService jschService = new JschService();

        Session session = jschService.getSession();
        
        session.connect();
        
        ChannelSftp channelSftp = (ChannelSftp) session.openChannel("sftp");
        channelSftp.connect();
        
        channelSftp.get(remoteFile, localFile);
        
        channelSftp.exit();
        session.disconnect();
    }
    
    public void delFile(String remoteDir, String remoteFileName) throws Exception {
        
        JschService jschService = new JschService();

        Session session = jschService.getSession();
        
        session.connect();
        
        ChannelSftp channelSftp = (ChannelSftp) session.openChannel("sftp");
        channelSftp.connect();
        
        channelSftp.cd(remoteDir);
        channelSftp.rm(remoteFileName);
        
        channelSftp.exit();
        session.disconnect();
    }
    
    public Vector<ChannelSftp.LsEntry> getFilesList(String remoteDir) throws Exception {
        
        JschService jschService = new JschService();

        Session session = jschService.getSession();
        
        session.connect();
        
        ChannelSftp channelSftp = (ChannelSftp) session.openChannel("sftp");
        channelSftp.connect();
        
        channelSftp.cd(remoteDir);
        Vector<ChannelSftp.LsEntry> filesList = channelSftp.ls("*");
        
        channelSftp.exit();
        session.disconnect();
        
        return filesList;
    }
}
