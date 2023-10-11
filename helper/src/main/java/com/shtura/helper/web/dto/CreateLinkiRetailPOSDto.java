package com.shtura.helper.web.dto;

import javax.validation.constraints.NotEmpty;

public class CreateLinkiRetailPOSDto {

    @NotEmpty(message = "{CreateLinkiRetailPOSDto.selectedAnsibleGroup}")
    String ansibleGroup;
    
    @NotEmpty(message = "{CreateLinkiRetailPOSDto.selectedAnsibleHost}")
    String ansibleHost;
    
    @NotEmpty(message = "{CreateLinkiRetailPOSDto.selectedPath}")
    String iRetailPosPath;

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

    public String getiRetailPosPath() {
        return iRetailPosPath;
    }

    public void setiRetailPosPath(String iRetailPosPath) {
        this.iRetailPosPath = iRetailPosPath;
    }

    @Override
    public String toString() {
        return "****************************** '" + this.ansibleGroup + "'   '" + this.ansibleHost + "'   '" + this.iRetailPosPath + "' ******************************";
    }
}
