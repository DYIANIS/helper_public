package com.shtura.helper.entity.aptekajet;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Остатки")
public class Ostatki implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"Key\"")
    private Integer key;
    
    @Column(name = "Идентификатор") //@Column(name = "Имя", nullable=false)
    private String identifier;
    
    @Column(name = "Серия")
    private String series;
    
    @Column(name = "СрокГодности")
    private Timestamp expirationDate;
    
    @Column(name = "КоличествоВДробныхЕдиницах")
    private Integer quantityInFractionalUnits;
    
    @Column(name = "ДелимостьНаЧасти")
    private Integer divisibilityIntoParts;
    
    @Column(name = "Цена")
    private Double cost;
    
    @Column(name = "МинимальнаяЦена")
    private Double minimumCost;
    
    @Column(name = "ИнформацияДляПоиска")
    private String informationForSearch;
    
    @Column(name = "Uid")
    private String uID;
    
    @Column(name = "Nds_rate")
    private String ndsRate;

    @Column(name = "IncomeInvoiceDate")
    private Timestamp incomeInvoiceDate;
    
    @Column(name = "CostPrice")
    private Double costPrice;
    
    @Column(name = "Supply_code")
    private String supplyCode;
    
    @Column(name = "RPBeforeReduce")
    private Double rpBeforeReduce;
    
    @Column(name = "Supply_External_ID")
    private String supplyExternalID;
    
    @Column(name = "ROCPrice")
    private Double rocPrice;
    
    @Column(name = "SupplierPrice")
    private Double supplierPrice;
    
    @Column(name = "TaxRateNumber")
    private Double taxRateNumber;
    
    @Column(name = "PremiumPointsForUnit")
    private Double premiumPointsForUnit;
    
    @Column(name = "IsCommission")
    private Integer isCommission;

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        series = series;
    }

    public Timestamp getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Timestamp expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Integer getQuantityInFractionalUnits() {
        return quantityInFractionalUnits;
    }

    public void setQuantityInFractionalUnits(Integer quantityInFractionalUnits) {
        this.quantityInFractionalUnits = quantityInFractionalUnits;
    }

    public Integer getDivisibilityIntoParts() {
        return divisibilityIntoParts;
    }

    public void setDivisibilityIntoParts(Integer divisibilityIntoParts) {
        this.divisibilityIntoParts = divisibilityIntoParts;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Double getMinimumCost() {
        return minimumCost;
    }

    public void setMinimumCost(Double minimumCost) {
        this.minimumCost = minimumCost;
    }

    public String getInformationForSearch() {
        return informationForSearch;
    }

    public void setInformationForSearch(String informationForSearch) {
        this.informationForSearch = informationForSearch;
    }

    public String getuID() {
        return uID;
    }

    public void setuID(String uID) {
        this.uID = uID;
    }

    public String getNdsRate() {
        return ndsRate;
    }

    public void setNdsRate(String ndsRate) {
        this.ndsRate = ndsRate;
    }

    public Timestamp getIncomeInvoiceDate() {
        return incomeInvoiceDate;
    }

    public void setIncomeInvoiceDate(Timestamp incomeInvoiceDate) {
        this.incomeInvoiceDate = incomeInvoiceDate;
    }

    public Double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(Double costPrice) {
        this.costPrice = costPrice;
    }

    public String getSupplyCode() {
        return supplyCode;
    }

    public void setSupplyCode(String supplyCode) {
        this.supplyCode = supplyCode;
    }

    public Double getRpBeforeReduce() {
        return rpBeforeReduce;
    }

    public void setRpBeforeReduce(Double rpBeforeReduce) {
        this.rpBeforeReduce = rpBeforeReduce;
    }

    public String getSupplyExternalID() {
        return supplyExternalID;
    }

    public void setSupplyExternalID(String supplyExternalID) {
        this.supplyExternalID = supplyExternalID;
    }

    public Double getRocPrice() {
        return rocPrice;
    }

    public void setRocPrice(Double rocPrice) {
        this.rocPrice = rocPrice;
    }

    public Double getSupplierPrice() {
        return supplierPrice;
    }

    public void setSupplierPrice(Double supplierPrice) {
        this.supplierPrice = supplierPrice;
    }

    public Double getTaxRateNumber() {
        return taxRateNumber;
    }

    public void setTaxRateNumber(Double taxRateNumber) {
        this.taxRateNumber = taxRateNumber;
    }

    public Double getPremiumPointsForUnit() {
        return premiumPointsForUnit;
    }

    public void setPremiumPointsForUnit(Double premiumPointsForUnit) {
        this.premiumPointsForUnit = premiumPointsForUnit;
    }

    public Integer getIsCommission() {
        return isCommission;
    }

    public void setIsCommission(Integer isCommission) {
        this.isCommission = isCommission;
    }
    
    @Override
    public String toString() {
        return "*************** Key = " + this.key.toString() + "   Серия = " + this.series + "   СрокГодности = " + this.expirationDate.toString() + "   ДелимостьНаЧасти = " + this.divisibilityIntoParts.toString() + "   Цена = " + this.cost.toString()  + " **********************";
    }
}
