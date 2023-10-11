package com.shtura.helper.web.dto;

import javax.validation.constraints.NotEmpty;

public class EditUserEmailDto {
    
    @NotEmpty(message = "{EditAdminEmail.selectedLogin.IsEmpty}")
    String selectedLogin;

    @NotEmpty(message = "{EditAdminEmail.inputedAdminEmail.IsEmpty}")
    String inputedUserEmail;

    public String getInputedUserEmail() {
        return inputedUserEmail;
    }

    public void setInputedUserEmail(String inputedUserEmail) {
        this.inputedUserEmail = inputedUserEmail;
    }
    
    public String getSelectedLogin() {
        return selectedLogin;
    }

    public void setSelectedLogin(String selectedLogin) {
        this.selectedLogin = selectedLogin;
    }

    @Override
    public String toString() {
        return "******************   '" + this.inputedUserEmail + "'   '" + this.selectedLogin + "'   ******************";
    }
}
