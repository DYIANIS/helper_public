package com.shtura.helper.web.dto;

import javax.validation.constraints.NotEmpty;

public class ConnectDto {

    @NotEmpty(message = "{ConnectDto.ipForConnect.IsEmpty}")
    String ipForConnect;
    
    @NotEmpty(message = "{ConnectDto.selectedUserDb.IsEmpty}")
    String selectedUserDb;
    
    String organization;
    
    String apteka;
    
    public String getIpForConnect() {
        return ipForConnect;
    }
    
    public void setIpForConnect(String ipForConnect) {
        this.ipForConnect = ipForConnect;
    }
    
    public String getSelectedUserDb() {
        return selectedUserDb;
    }

    public void setSelectedUserDb(String selectedUserDb) {
        this.selectedUserDb = selectedUserDb;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getApteka() {
        return apteka;
    }

    public void setApteka(String apteka) {
        this.apteka = apteka;
    }

    @Override
    public String toString() {
        return "******************************8888888888888888 '" + this.ipForConnect + "'   " + /*this.connectionIPAdress.toString() +*/ "'   '" + this.selectedUserDb + "' ******************************";
    }
}
