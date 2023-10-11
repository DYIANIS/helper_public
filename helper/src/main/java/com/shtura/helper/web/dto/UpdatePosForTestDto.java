package com.shtura.helper.web.dto;

import javax.validation.constraints.NotEmpty;

public class UpdatePosForTestDto {

    @NotEmpty(message = "{UpdatePosForTestDto.selectedAnsibleGroup}")
    String ansibleGroup;
    
    @NotEmpty(message = "{UpdatePosForTestDto.selectedAnsibleHost}")
    String ansibleHost;

    public String getAnsibleGroup() {
        return ansibleGroup;
    }

    public void setAnsibleGroup(String ansibleGroup) {
        this.ansibleGroup = ansibleGroup;
    }

    public String getAnsibleHost() {
        return ansibleHost;
    }

    public void setAnsibleHost(String ansibleHost) {
        this.ansibleHost = ansibleHost;
    }

    @Override
    public String toString() {
        return "****************************** '" + this.ansibleGroup + "'   '" + this.ansibleHost + "' ******************************";
    }
}
