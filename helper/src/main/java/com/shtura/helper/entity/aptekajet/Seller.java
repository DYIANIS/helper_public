package com.shtura.helper.entity.aptekajet;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Продавцы")
public class Seller implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"Key\"")
    private Integer key;
    
    @Column(name = "Имя") //@Column(name = "Имя", nullable=false)
    private String name;
    
    @Column(name = "Код") //@Column(name = "Код", nullable=false)
    private String kod;
    
    @Column(name = "IsAdmin") //@Column(name = "IsAdmin", nullable=false)
    private Integer isAdmin;
    
    @Column(name = "NumCasher")
    private Integer numCaser;
    
    @Column(name = "IsActive") //@Column(name = "IsActive", nullable=false)
    private Integer isActive;
    
    @Column(name = "IsLoadSettingView")
    private Integer isLoadSettingView;
    
    @Column(name = "IsAdvancedFunctionality")
    private Integer isAdvancedFunctionality;
    
    @Column(name = "Employe_ID")
    private String employe_ID;
    
    public Seller() {
        
    }
    
    public Seller(String name, String kod, Integer isAdmin, Integer numCaser, Integer isActive, Integer isLoadSettingView, Integer isAdvancedFunctionality, String employe_ID) {
        this.name = name;
        this.kod = kod;
        this.isAdmin = isAdmin;
        this.numCaser = numCaser;
        this.isActive = isActive;
        this.isLoadSettingView = isLoadSettingView;
        this.isAdvancedFunctionality = isAdvancedFunctionality;
        this.employe_ID = employe_ID;
    }

    public Integer getKey() {
        return this.key;
    }
    public void setKey(Integer key) {
        this.key = key;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getKod() {
        return kod;
    }
    public void setKod(String kod) {
        this.kod = kod;
    }
    public Integer isAdmin() {
        return isAdmin;
    }
    public void setAdmin(Integer isAdmin) {
        this.isAdmin = isAdmin;
    }
    public Integer getNumCaser() {
        return numCaser;
    }
    public void setNumCaser(Integer numCaser) {
        this.numCaser = numCaser;
    }
    public Integer isActive() {
        return isActive;
    }
    public void setActive(Integer isActive) {
        this.isActive = isActive;
    }
    public Integer getIsLoadSettingView() {
        return isLoadSettingView;
    }
    public void setIsLoadSettingView(Integer isLoadSettingView) {
        this.isLoadSettingView = isLoadSettingView;
    }
    public Integer isAdvancedFunctionality() {
        return isAdvancedFunctionality;
    }
    public void setAdvancedFunctionality(Integer isAdvancedFunctionality) {
        this.isAdvancedFunctionality = isAdvancedFunctionality;
    }
    public String getEmploye_ID() {
        return employe_ID;
    }
    public void setEmploye_ID(String employe_ID) {
        this.employe_ID = employe_ID;
    }
    
    @Override
    public String toString() {
        return "*************** Key = " + this.key + "   Имя = " + this.name + "   Код = " + this.kod + "   isAdmin = " + this.isAdmin + "   is Activ = " + this.isActive + "   isLoad = " + this.isLoadSettingView + " **********************";
    }
}
