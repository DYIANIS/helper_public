package com.shtura.helper.entity.helperdb;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "userDBForCurrentUser")
public class UserDBForCurrentUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @JoinColumn(name = "username")
    private String username;
    
    @JoinColumn(name = "connectionIPAdress")
    private String connectionIPAdress;
    
    @JoinColumn(name = "connectionUserDB")
    private String connectionUserDB;
    
    @JoinColumn(name = "connectionPassDB")
    private String connectionPassDB;
    
    public UserDBForCurrentUser() {
        
    }
    
    public UserDBForCurrentUser(String username, String connectionIPAdress, String connectionUserDB, String connectionPassDB) {
        this.username = username;
        this.connectionIPAdress = connectionIPAdress;
        this.connectionUserDB = connectionUserDB;
        this.connectionPassDB = connectionPassDB;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getConnectionIPAdress() {
        return connectionIPAdress;
    }

    public void setConnectionIPAdress(String connectionIPAdress) {
        this.connectionIPAdress = connectionIPAdress;
    }

    public String getConnectionUserDB() {
        return connectionUserDB;
    }

    public void setConnectionUserDB(String connectionUserDB) {
        this.connectionUserDB = connectionUserDB;
    }

    public String getConnectionPassDB() {
        return connectionPassDB;
    }

    public void setConnectionPassDB(String connectionPassDB) {
        this.connectionPassDB = connectionPassDB;
    }

    @Override
    public String toString() {
        return "*" + this.id.toString() + "* *" + this.username + "* *" + this.connectionIPAdress + "* *"    + this.connectionUserDB + "* *"    + this.connectionPassDB;
    }
}
