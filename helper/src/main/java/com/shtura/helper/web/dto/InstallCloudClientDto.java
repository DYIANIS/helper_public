package com.shtura.helper.web.dto;

import javax.validation.constraints.NotEmpty;

public class InstallCloudClientDto {

    @NotEmpty(message = "{InstallCloudClientDto.selectedAnsibleGroup}")
    String ansibleGroup;
    
    @NotEmpty(message = "{InstallCloudClientDto.selectedAnsibleHost}")
    String ansibleHost;

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
    
    @Override
    public String toString() {
        return "****************************** '" + this.ansibleGroup + "'   '" + this.ansibleHost + "' ******************************";
    }
}