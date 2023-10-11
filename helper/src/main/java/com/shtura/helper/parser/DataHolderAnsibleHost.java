package com.shtura.helper.parser;

public class DataHolderAnsibleHost {

    private String hostName;
    private String hostNameValue;
    
    public DataHolderAnsibleHost() {
        
    }
    
    public DataHolderAnsibleHost(String hostName, String hostNameValue) {
        this.hostName = hostName;
        this.hostNameValue = hostNameValue;
        
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getHostNameValue() {
        return hostNameValue;
    }

    public void setHostNameValue(String hostNameValue) {
        this.hostNameValue = hostNameValue;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((hostName == null) ? 0 : hostName.hashCode());
        result = prime * result + ((hostNameValue == null) ? 0 : hostNameValue.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DataHolderAnsibleHost other = (DataHolderAnsibleHost) obj;
        if (hostName == null) {
            if (other.hostName != null)
                return false;
        } else if (!hostName.equals(other.hostName))
            return false;
        if (hostNameValue == null) {
            if (other.hostNameValue != null)
                return false;
        } else if (!hostNameValue.equals(other.hostNameValue))
            return false;
        return true;
    }
    
    @Override
    public String toString() {
        return "**************************   '" + this.hostName + "'   '" + this.hostNameValue + "'   **************************";
    }
}
