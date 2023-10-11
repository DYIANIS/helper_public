package com.shtura.helper.entity.helperdb;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.shtura.helper.repositories.UserRepository;
import com.shtura.helper.repositories.helperdb.ConnectionIPAdressRepository;
import com.shtura.helper.repositories.helperdb.ConnectionNameOrganizationRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConnectionIPAdressTest {
    
    @Autowired
    private ConnectionNameOrganizationRepository connectionNameOrganizationRepository;

    @Autowired
    private ConnectionIPAdressRepository connectionIPAdressRepository;
    
    @Test
    public void saveee() {
        
        ConnectionIPAdress connectionIPAdress = new ConnectionIPAdress();
        
        ConnectionNameOrganization connectionNameOrganization = new ConnectionNameOrganization();
        
        connectionNameOrganization.setName("connectionNameOrganizationTest");
        
        connectionNameOrganizationRepository.save(connectionNameOrganization);

        connectionIPAdress.setConnectionNameOrganization(connectionNameOrganization);
        connectionIPAdress.setIp("testIP");
        connectionIPAdress.setName("testName");
        
        assertEquals("testName", connectionIPAdress.getName());

    }
}
