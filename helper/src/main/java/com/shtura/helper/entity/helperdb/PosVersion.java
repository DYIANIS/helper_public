package com.shtura.helper.entity.helperdb;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "posVersion")
public class PosVersion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @JoinColumn(name = "inventoryHostname")
    private String inventoryHostname;
    
    @JoinColumn(name = "hostIP")
    private String hostIP;
    
    @JoinColumn(name = "hostPort")
    private String hostPort;
    
    @JoinColumn(name = "hostOrganization")
    private String hostOrganization;
    
    @JoinColumn(name = "hostType")
    private String hostType;
    
    @JoinColumn(name = "farminVersion")
    private String farminVersion;
    
    @JoinColumn(name = "itpharmaVersion")
    private String itpharmaVersion;
    
    @JoinColumn(name = "status")
    private String status;
    
    @JoinColumn(name = "launchDirectory")
    private String launchDirectory;
    
    @JoinColumn(name = "lastSynchronizationTime")
    private String lastSynchronizationTime;
    
    public PosVersion() {
        
    }    
    
    public PosVersion(String inventoryHostname, String hostIP, String hostPort, String hostOrganization, String hostType,
            String farminVersion, String itpharmaVersion, String status, String launchDirectory,
            String lastSynchronizationTime) {
        super();
        this.inventoryHostname = inventoryHostname;
        this.hostIP = hostIP;
        this.hostPort = hostPort;
        this.hostOrganization = hostOrganization;
        this.hostType = hostType;
        this.farminVersion = farminVersion;
        this.itpharmaVersion = itpharmaVersion;
        this.status = status;
        this.launchDirectory = launchDirectory;
        this.lastSynchronizationTime = lastSynchronizationTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInventoryHostname() {
        return inventoryHostname;
    }

    public void setInventoryHostname(String inventoryHostname) {
        this.inventoryHostname = inventoryHostname;
    }

    public String getHostIP() {
        return hostIP;
    }

    public void setHostIP(String hostIP) {
        this.hostIP = hostIP;
    }

    public String getHostPort() {
        return hostPort;
    }

    public void setHostPort(String hostPort) {
        this.hostPort = hostPort;
    }

    public String getHostOrganization() {
        return hostOrganization;
    }

    public void setHostOrganization(String hostOrganization) {
        this.hostOrganization = hostOrganization;
    }

    public String getHostType() {
        return hostType;
    }

    public void setHostType(String hostType) {
        this.hostType = hostType;
    }

    public String getFarminVersion() {
        return farminVersion;
    }

    public void setFarminVersion(String farminVersion) {
        this.farminVersion = farminVersion;
    }

    public String getItpharmaVersion() {
        return itpharmaVersion;
    }

    public void setItpharmaVersion(String itpharmaVersion) {
        this.itpharmaVersion = itpharmaVersion;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLaunchDirectory() {
        return launchDirectory;
    }

    public void setLaunchDirectory(String launchDirectory) {
        this.launchDirectory = launchDirectory;
    }

    public String getLastSynchronizationTime() {
        return lastSynchronizationTime;
    }

    public void setLastSynchronizationTime(String lastSynchronizationTime) {
        this.lastSynchronizationTime = lastSynchronizationTime;
    }

    @Override
    public String toString() {
        return "PosVersion [id=" + id + ", hostName=" + inventoryHostname + ", hostIP=" + hostIP + ", hostPort=" + hostPort
                + ", hostOrganization=" + hostOrganization + ", hostType=" + hostType + ", farminVersion="
                + farminVersion + ", itpharmaVersion=" + itpharmaVersion + ", status=" + status + ", launchDirectory="
                + launchDirectory + ", lastSynchronizationTime=" + lastSynchronizationTime + "]";
    } 
    
}
