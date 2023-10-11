package com.shtura.helper.web.dto;

import javax.validation.constraints.NotEmpty;

public class DisableOrEnableUserDto {

    @NotEmpty(message = "{DisableOrEnableUserDto.selectedUser.IsEmpty}")
    String selectedLogin;
    
    @NotEmpty(message = "{DisableOrEnableUserDto.selectedBanned.IsEmpty}")
    String selectedBanned;
    
    public String getSelectedLogin() {
        return selectedLogin;
    }
    
    public void setSelectedLogin(String selectedLogin) {
        this.selectedLogin = selectedLogin;
    }
    
    public String getSelectedBanned() {
        return this.selectedBanned;
    }
    
    public void setSelectedBanned(String selectedBanned) {
        this.selectedBanned = selectedBanned;
    }
    
    @Override
    public String toString() {
        return "****************************** '" + this.selectedLogin + "'   '" +  this.selectedBanned + "' ******************************";
    }
}
