package com.shtura.helper.repositories.workWithFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Authenticator;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import com.shtura.helper.repositories.MyAuthenticator;

public class FileLoadreByUrl {
    

    private static String username = "";
    private static String password = "";

    public FileLoadreByUrl() {
        
    }
    
    public void getFile(String urlStr, String localDir) throws Exception {

        URL url = new URL(urlStr);
        Path path = Paths.get(url.getPath());
        
        Properties properties = new Properties();
        
        //properties.setProperty("username","farmin.user");
        //properties.setProperty("password", "HJnbuyTGB^&*T&*&n");
        
        properties.setProperty("username","shtura.ds@gmail.com");
        properties.setProperty("password", "U3K46w5LLrEWNAk");
        
        Authenticator.setDefault (new MyAuthenticator (properties));

        ReadableByteChannel rbc = Channels.newChannel(url.openStream());
        FileOutputStream fos = new FileOutputStream(localDir + path.getFileName());
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        fos.close();

        //String headerField = url.openConnection().getHeaderField("Last-Modified");

        //LastDownloadFile lastDownloadFile = new LastDownloadFile(urlStr, headerField);
        //lastDownloadFileRepository.save(lastDownloadFile);

    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    /*public void test(String urlStr, String localDir) {
        URL url;
        InputStream is = null;
        BufferedReader br;
        String line;

        // Install Authenticator
        MyAuthenticator.setPasswordAuthentication("farmin.user", "HJnbuyTGB^&*T&*&n");
        Authenticator.setDefault (new MyAuthenticator ());

        try {
            url = new URL("Your_URL_Here");
            is = url.openStream();  // throws an IOException
            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (MalformedURLException mue) {
             mue.printStackTrace();
        } catch (IOException ioe) {
             ioe.printStackTrace();
        } finally {
            try {
                if (is != null) is.close();
            } catch (IOException ioe) {
                // nothing to see here
            }
        }

    }*/
    
    /*public void getFile(List<String> urls) {
        try {
            for (String urlStr : this.urls) {
                URL url = new URL(urlStr);
                Path path = Paths.get(url.getPath());

                ReadableByteChannel rbc = Channels.newChannel(url.openStream());
                FileOutputStream fos = new FileOutputStream(this.file + path.getFileName());
                fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
                fos.close();

                String headerField = url.openConnection().getHeaderField("Last-Modified");

                LastDownloadFile lastDownloadFile = new LastDownloadFile(urlStr, headerField);
                lastDownloadFileRepository.save(lastDownloadFile);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
}
