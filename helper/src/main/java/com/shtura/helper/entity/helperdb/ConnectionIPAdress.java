package com.shtura.helper.entity.helperdb;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "connectionIPAdress")
public class ConnectionIPAdress {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "connectionNameOrganization")
    private ConnectionNameOrganization connectionNameOrganization;
    private String ip;
    private String name;

    public ConnectionIPAdress() {

    }

    public ConnectionIPAdress(ConnectionNameOrganization connectionNameOrganization, String ip) {
        this.connectionNameOrganization = connectionNameOrganization;
        this.ip = ip;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getIp() {
        return this.ip;
    }

    public ConnectionNameOrganization getConnectionNameOrganization() {
        return connectionNameOrganization;
    }

    public void setConnectionNameOrganization(ConnectionNameOrganization connectionNameOrganization) {
        this.connectionNameOrganization = connectionNameOrganization;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "*************** '" + this.id.toString()  + "'   '" + this.connectionNameOrganization.getName() + "'   '" + this.ip + "'   '" + this.name + "' ***************";
    }
}
