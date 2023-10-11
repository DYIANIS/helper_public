package com.shtura.helper.web.dto;

import javax.validation.constraints.NotEmpty;

public class AddRoleToUserDto {

    @NotEmpty(message = "{AddRoleToUserDto.selectedLogin.IsEmpty}")
    String selectedLogin;
    
    @NotEmpty(message = "{AddRoleToUserDto.selectedRole.IsEmpty}")
    String selectedRole;
    
    public String getSelectedLogin() {
        return selectedLogin;
    }
    
    public void setSelectedLogin(String selectedLogin) {
        this.selectedLogin = selectedLogin;
    }
    
    public String getSelectedRole() {
        return selectedRole;
    }
    
    public void setSelectedRole(String selectedRole) {
        this.selectedRole = selectedRole;
    }
    
    @Override
    public String toString() {
        return "******************************8888888888888888 '" + this.selectedLogin + "'   '" +  this.selectedRole + "' ******************************";
    }
}
