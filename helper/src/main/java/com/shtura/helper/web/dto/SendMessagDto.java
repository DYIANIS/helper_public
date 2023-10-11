package com.shtura.helper.web.dto;

import javax.validation.constraints.NotEmpty;

public class SendMessagDto {

    @NotEmpty(message = "{SendMessagDto.textareaField}")
    String textareaField;

    @NotEmpty(message = "{SendMessagDto.selectedAnsibleGroup}")
    String ansibleGroup;
    
    @NotEmpty(message = "{SendMessagDto.selectedAnsibleHost}")
    String ansibleHost;
    
    public String getTextareaField() {
        return textareaField;
    }

    public void setTextareaField(String textareaField) {
        this.textareaField = textareaField;
    }

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
