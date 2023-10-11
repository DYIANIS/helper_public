package com.shtura.helper.web.dto;

import javax.validation.constraints.NotEmpty;

public class TransferenceDto {

    String startCheckNumber;
    String endCheckNumber;
    String selectedStartSeller;
    @NotEmpty(message = "{TransferenceDto.getEndSeller.IsEmpty}")
    String selectedEndSeller;
    @NotEmpty(message = "{TransferenceDto.needRegistrars.IsEmpty}")
    String selectedRegistrar;
    String startTime;
    String endTime;
    @NotEmpty(message = "{TransferenceDto.needDate.IsEmpty}")
    String selectedDate;
    
    public String getStartCheckNumber() {
        return startCheckNumber;
    }

    public void setStartCheckNumber(String startCheckNumber) {
        this.startCheckNumber = startCheckNumber;
    }

    public String getEndCheckNumber() {
        return endCheckNumber;
    }

    public void setEndCheckNumber(String endCheckNumber) {
        this.endCheckNumber = endCheckNumber;
    }

    public String getSelectedStartSeller() {
        return selectedStartSeller;
    }

    public void setSelectedStartSeller(String selectedStartSeller) {
        this.selectedStartSeller = selectedStartSeller;
    }

    public String getSelectedEndSeller() {
        return selectedEndSeller;
    }

    public void setSelectedEndSeller(String selectedEndSeller) {
        this.selectedEndSeller = selectedEndSeller;
    }

    public String getSelectedRegistrar() {
        return selectedRegistrar;
    }

    public void setSelectedRegistrar(String selectedRegistrar) {
        this.selectedRegistrar = selectedRegistrar;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getSelectedDate() {
        return selectedDate;
    }

    public void setSelectedDate(String selectedDate) {
        this.selectedDate = selectedDate;
    }

    @Override
    public String toString() {
        return "*************** '" + this.startCheckNumber + "' '" + this.endCheckNumber + "' '" + this.selectedStartSeller + "' '" + this.selectedEndSeller + "' '" + this.selectedRegistrar + "' '" + this.startTime + "' '" + this.endTime + "' '" + this.selectedDate + "' **********************";
    }
}
