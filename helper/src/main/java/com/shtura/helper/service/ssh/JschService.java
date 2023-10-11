package com.shtura.helper.service.ssh;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.util.Properties;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class JschService {
    
    private final String host = "***.***.***.***";
    private final String username = "******";
    private static final int port = 22;

    private final String command = "cd /etc/ansible/yml && ansible-playbook ---PLAYBOOK_NAME--- ---PLAYBOOK_OTHER_PARAMS--- --extra-vars \"MYHOSTS=cashboxes\" -l '---ANSIBLE_CASHBOXES_GROUP---'";
    
    public JschService() {
        
    }
    
    public Session getSession() throws Exception {
        
        JSch jsch = new JSch();
        Session session = jsch.getSession(username, host, port);
        session.setConfig("StrictHostKeyChecking", "no");
        jsch.addIdentity("src/main/resources/ssh/iocom.ppk");
        
        return session;
    }
    
    public void runPlaybook(String playbookName, String otherParams, String cashboxesGroup) throws Exception {
        
        Session session = getSession();
        
        session.connect();
        
        ChannelExec channel = null;
        String response = null;

        channel = (ChannelExec) session.openChannel("exec");
        channel.setCommand(command.replaceFirst("---PLAYBOOK_NAME---", playbookName).replaceFirst("---PLAYBOOK_OTHER_PARAMS---", otherParams).replaceFirst("---ANSIBLE_CASHBOXES_GROUP---", cashboxesGroup));
        
        ByteArrayOutputStream responseStream = new ByteArrayOutputStream();
        ByteArrayOutputStream errorStream = new ByteArrayOutputStream();
        
        channel.setOutputStream(responseStream);
        channel.setErrStream(errorStream);
        channel.connect();
        
        while (channel.isConnected()) {
            Thread.sleep(100);
        }
        
        response = new String(responseStream.toByteArray());
        String errorResponse = new String(errorStream.toByteArray());
        
        if(!errorResponse.isEmpty()) {
            
            if (session != null)
                session.disconnect();
            if (channel != null)
                channel.disconnect();
            
            throw new Exception(errorResponse);
        }

        if (session != null)
            session.disconnect();
        if (channel != null)
            channel.disconnect();
    }

}
