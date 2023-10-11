package com.shtura.helper.web.dto;

import javax.validation.constraints.NotEmpty;

public class CreateLinkiRetailManagementDto {

    @NotEmpty(message = "{CreateLinkiRetailManagementDto.selectedAnsibleGroup}")
    String ansibleGroup;
    
    @NotEmpty(message = "{CreateLinkiRetailManagementDto.selectedAnsibleHost}")
    String ansibleHost;
    
    @NotEmpty(message = "{CreateLinkiRetailManagementDto.selectedPath}")
    String iRetailManagementPath;

    public void setAnsibleGroup(String ansibleGroup) {
        this.ansibleGroup = ansibleGroup;
    }

    public String getAnsibleGroup() {
        return ansibleGroup;
    }

    public void setAnsibleHost(String ansibleHost) {
        this.ansibleHost = ansibleHost;
    }

    public String getAnsibleHost() {
        return ansibleHost;
    }

    public String getiRetailManagementPath() {
        return iRetailManagementPath;
    }

    public void setiRetailManagementPath(String iRetailManagementPath) {
        this.iRetailManagementPath = iRetailManagementPath;
    }

    @Override
    public String toString() {
        return "****************************** '" + this.ansibleGroup + "'   '" + this.ansibleHost + "'   '" + this.iRetailManagementPath + "' ******************************";
    }
}
