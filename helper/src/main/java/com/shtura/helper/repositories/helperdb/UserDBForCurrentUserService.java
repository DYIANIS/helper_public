package com.shtura.helper.repositories.helperdb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.shtura.helper.entity.helperdb.UserDBForCurrentUser;

@Service
public class UserDBForCurrentUserService {

    @Autowired
    private UserDBForCurrentUserRepository userDBForCurrentUserRepository;
    
    public UserDBForCurrentUserService() {
        
    }
    
    public UserDBForCurrentUser getUserDBForCurrentUser() {
        
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String username;
        
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
            } else {
            username = principal.toString();
        }
        
        UserDBForCurrentUser userDBForCurrentUser;
        try{
            userDBForCurrentUser = userDBForCurrentUserRepository.findByUsername(username);
            System.out.println(userDBForCurrentUser.toString());
        }
        catch (Exception e) {
            System.out.println("--------------------- UserDBForCurrentUserService ---------------------");
            System.out.println("--------------------- Exception ---------------------");
            
            userDBForCurrentUser = new UserDBForCurrentUser();
            e.printStackTrace();
        }
        
        return userDBForCurrentUser;
    }
}
