package com.shtura.helper.entity.aptekajet;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import java.sql.Timestamp;

@Entity
@Table(name = "Покупатели")
public class Buyer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"Key\"")
    private Integer key;
    
    @Column(name = "Имя")
    private String name;
    
    @Column(name = "Код")
    private String kod;
    
    @Column(name = "ДатаРегистрации")
    private Timestamp dateRegistration;
    
    @Column(name = "ПроцентСкидки")
    private Double percentageDiscounts;
    
    @Column(name = "СуммаПокупок")
    private Double amountPurchases;
    
    @Column(name = "СуммаПрочихПокупок")
    private Double amountOtherPurchases;
    
    @Column(name = "IsEmployee")
    private Integer isEmployee;
    
    @Column(name = "IsActive")
    private Integer isActive;
    
    @Column(name = "_1C_Id")
    private String oneC_Id;
    
    @Column(name = "IsInsurance")
    private Integer isInsurance;
    
    @Column(name = "NoLimitDiscount")
    private Integer noLimitDiscount;
    
    @Column(name = "IsHesedRahamin")
    private Integer isHesedRahamin;
    
    @Column(name = "MarketingEvents_Guid")
    private String marketingEvents_Guid;
    
    @Column(name = "IsVitalurDiscount")
    private Integer isVitalurDiscount;

    public Integer getKey() {
        return key;
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

    public Timestamp getDateRegistration() {
        return dateRegistration;
    }

    public void setDateRegistration(Timestamp dateRegistration) {
        this.dateRegistration = dateRegistration;
    }

    public Double getPercentageDiscounts() {
        return percentageDiscounts;
    }

    public void setPercentageDiscounts(Double percentageDiscounts) {
        this.percentageDiscounts = percentageDiscounts;
    }

    public Double getAmountPurchases() {
        return amountPurchases;
    }

    public void setAmountPurchases(Double amountPurchases) {
        this.amountPurchases = amountPurchases;
    }

    public Double getAmountOtherPurchases() {
        return amountOtherPurchases;
    }

    public void setAmountOtherPurchases(Double amountOtherPurchases) {
        this.amountOtherPurchases = amountOtherPurchases;
    }

    public Integer getIsEmployee() {
        return isEmployee;
    }

    public void setIsEmployee(Integer isEmployee) {
        this.isEmployee = isEmployee;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public String getOneC_Id() {
        return oneC_Id;
    }

    public void setOneC_Id(String oneC_Id) {
        this.oneC_Id = oneC_Id;
    }

    public Integer getIsInsurance() {
        return isInsurance;
    }

    public void setIsInsurance(Integer isInsurance) {
        this.isInsurance = isInsurance;
    }

    public Integer getNoLimitDiscount() {
        return noLimitDiscount;
    }

    public void setNoLimitDiscount(Integer noLimitDiscount) {
        this.noLimitDiscount = noLimitDiscount;
    }

    public Integer getIsHesedRahamin() {
        return isHesedRahamin;
    }

    public void setIsHesedRahamin(Integer isHesedRahamin) {
        this.isHesedRahamin = isHesedRahamin;
    }

    public String getMarketingEvents_Guid() {
        return marketingEvents_Guid;
    }

    public void setMarketingEvents_Guid(String marketingEvents_Guid) {
        this.marketingEvents_Guid = marketingEvents_Guid;
    }

    public Integer getIsVitalurDiscount() {
        return isVitalurDiscount;
    }

    public void setIsVitalurDiscount(Integer isVitalurDiscount) {
        this.isVitalurDiscount = isVitalurDiscount;
    }
    
    @Override
    public String toString() {
        return "*************** Key = " + this.key + "   Имя = " + this.name + "   Код = " + this.kod + "   ДатаРегистрации = " + this.dateRegistration.toString() + "   ПроцентСкидки = " + this.percentageDiscounts + "   СуммаПокупок = " + this.amountPurchases + " **********************";
    }
}
