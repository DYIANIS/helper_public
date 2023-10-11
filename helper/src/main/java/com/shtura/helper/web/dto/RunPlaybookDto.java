package com.shtura.helper.web.dto;

import javax.validation.constraints.NotEmpty;

public class RunPlaybookDto {

    @NotEmpty(message = "{RunPlaybookDto.selectedAnsibleGroup}")
    String ansibleGroup;
    
    @NotEmpty(message = "{RunPlaybookDto.selectedAnsibleHost}")
    String ansibleHost;
    
    @NotEmpty(message = "{RunPlaybookDto.selectedPath}")
    String playbookName;
    
    String otherParam;

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

    public String getPlaybookName() {
        return playbookName;
    }

    public void setPlaybookName(String playbookName) {
        this.playbookName = playbookName;
    }

    public String getOtherParam() {
        return otherParam;
    }

    public void setOtherParam(String otherParam) {
        this.otherParam = otherParam;
    }

    @Override
    public String toString() {
        return "****************************** '" + this.ansibleGroup + "'   '" + this.ansibleHost + "'   '" + this.playbookName + "'   '" + this.otherParam + "' ******************************";
    }
}
