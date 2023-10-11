package com.shtura.helper.entity.aptekajet;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ПозицииЧеков")
public class CheckPosition implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"Key\"")
    private Integer key;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Чек_Key")
    private Сheck check;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Остаток_Key")
    private Ostatki ostatki;
    
    @Column(name = "КоличествоВДробныхЕдиницах")
    private Integer quantityInFractionalUnits;
    
    @Column(name = "Цена")
    private Double cost;
    
    @Column(name = "Перенесено")
    private Integer postponed;
    
    @Column(name = "IsDiscountGoods")
    private Integer isDiscountGoods;
    
    @Column(name = "Section")
    private Integer section;
    
    @Column(name = "IsUseGoodMinReserv")
    private Integer isUseGoodMinReserv;
    
    @Column(name = "SumCard")
    private Double sumCard;
    
    @Column(name = "SumCert")
    private Double sumCert;
    
    @Column(name = "IsSocialDiscount")
    private Integer isSocialDiscount;
    
    @Column(name = "IsEmployeeDiscount")
    private Integer isEmployeeDiscount;
    
    @Column(name = "IsTianshiDiscount")
    private Integer isTianshiDiscount;
    
    @Column(name = "IsOldDiscountCardKazinca")
    private Integer isOldDiscountCardKazinca;
    
    @Column(name = "PriceWithoutDiscount")
    private Double PriceWithoutDiscount;
    
    @Column(name = "PriceWithDiscount")
    private Double priceWithDiscount;
    
    @Column(name = "NominalPercentDiscount")
    private Double NominalPercentDiscount;
    
    @Column(name = "IsVitalurDiscount")
    private Integer IsVitalurDiscount;
    
    @Column(name = "IsInsurancePay")
    private Integer IsInsurancePay;
    
    @Column(name = "PercentPremium")
    private Double PercentPremium;
    
    @Column(name = "SumPremium")
    private Double SumPremium;
    
    @Column(name = "Premium_Guid")
    private String premiumGuid;
    
    @Column(name = "MarketingEvents_Guid")
    private String marketingEventsGuid;
    
    @Column(name = "RulesExecutionMarketingEvents_Guid")
    private String rulesExecutionMarketingEventsGuid;
    
    @Column(name = "MedCardId")
    private String medCardId;
    
    @Column(name = "PatientId")
    private String PatientId;
    
    @Column(name = "PrescriptionId")
    private String prescriptionId;
    
    @Column(name = "QtyDose")
    private Double qtyDose;
    
    @Column(name = "AllDoseForSale")
    private Double allDoseForSale;
    
    @Column(name = "AllDoseSold")
    private Double allDoseSold;
    
    @Column(name = "IsFullSaleDose")
    private Integer isFullSaleDose;
    
    @Column(name = "KeyPayPositionCheck")
    private Integer keyPayPositionCheck;
    
    @Column(name = "MedicationCode")
    private String medicationCode;
    
    @Column(name = "PatientFio")
    private String patientFio;
    
    @Column(name = "DatePrescription")
    private Timestamp datePrescription;
    
    @Column(name = "MedicationDescription")
    private String medicationDescription;

    @Column(name = "RecipeType")
    private Integer recipeType;

    @Column(name = "AlgoritmRoundId")
    private Integer algoritmRoundId;

    @Column(name = "PriceFractionalPart")
    private Double priceFractionalPart;
    
    @Column(name = "MedicationCode_ReallySale")
    private String medicationCodeReallySale;
    
    @Column(name = "PositionsUid")
    private String positionsUid;
    
    @Column(name = "Annotation")
    private String annotation;
    
    @Column(name = "SourceOfSale")
    private Integer sourceOfSale;
    
    @Column(name = "Signature")
    private Integer signature;
    
    @Column(name = "ManualAttribute")
    private String manualAttribute;
    
    @Column(name = "KeyPositionSourceOfSale")
    private Integer keyPositionSourceOfSale;
    
    @Column(name = "UsedCardCode")
    private String usedCardCode;
    
    @Column(name = "CardType_Guid")
    private String cardTypeGuid;
    
    @Column(name = "Person_ID")
    private String personID;
    
    @Column(name = "SumPoints")
    private Double sumPoints;
    
    @Column(name = "Employe_ID")
    private String employeID;
    
    @Column(name = "TypeRegistrationOfEmploye")
    private Integer typeRegistrationOfEmploye;

    @Column(name = "ManualMoved")
    private Integer manualMoved;

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public Сheck getCheck() {
        return check;
    }

    public void setCheck(Сheck check) {
        this.check = check;
    }

    public Ostatki getOstatki() {
        return ostatki;
    }

    public void setOstatki(Ostatki ostatki) {
        this.ostatki = ostatki;
    }

    public Integer getQuantityInFractionalUnits() {
        return quantityInFractionalUnits;
    }

    public void setQuantityInFractionalUnits(Integer quantityInFractionalUnits) {
        this.quantityInFractionalUnits = quantityInFractionalUnits;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Integer getPostponed() {
        return postponed;
    }

    public void setPostponed(Integer postponed) {
        this.postponed = postponed;
    }

    public Integer getIsDiscountGoods() {
        return isDiscountGoods;
    }

    public void setIsDiscountGoods(Integer isDiscountGoods) {
        this.isDiscountGoods = isDiscountGoods;
    }

    public Integer getSection() {
        return section;
    }

    public void setSection(Integer section) {
        this.section = section;
    }

    public Integer getIsUseGoodMinReserv() {
        return isUseGoodMinReserv;
    }

    public void setIsUseGoodMinReserv(Integer isUseGoodMinReserv) {
        this.isUseGoodMinReserv = isUseGoodMinReserv;
    }

    public Double getSumCard() {
        return sumCard;
    }

    public void setSumCard(Double sumCard) {
        this.sumCard = sumCard;
    }

    public Double getSumCert() {
        return sumCert;
    }

    public void setSumCert(Double sumCert) {
        this.sumCert = sumCert;
    }

    public Integer getIsSocialDiscount() {
        return isSocialDiscount;
    }

    public void setIsSocialDiscount(Integer isSocialDiscount) {
        this.isSocialDiscount = isSocialDiscount;
    }

    public Integer getIsEmployeeDiscount() {
        return isEmployeeDiscount;
    }

    public void setIsEmployeeDiscount(Integer isEmployeeDiscount) {
        this.isEmployeeDiscount = isEmployeeDiscount;
    }

    public Integer getIsTianshiDiscount() {
        return isTianshiDiscount;
    }

    public void setIsTianshiDiscount(Integer isTianshiDiscount) {
        this.isTianshiDiscount = isTianshiDiscount;
    }

    public Integer getIsOldDiscountCardKazinca() {
        return isOldDiscountCardKazinca;
    }

    public void setIsOldDiscountCardKazinca(Integer isOldDiscountCardKazinca) {
        this.isOldDiscountCardKazinca = isOldDiscountCardKazinca;
    }

    public Double getPriceWithoutDiscount() {
        return PriceWithoutDiscount;
    }

    public void setPriceWithoutDiscount(Double priceWithoutDiscount) {
        PriceWithoutDiscount = priceWithoutDiscount;
    }

    public Double getPriceWithDiscount() {
        return priceWithDiscount;
    }

    public void setPriceWithDiscount(Double priceWithDiscount) {
        this.priceWithDiscount = priceWithDiscount;
    }

    public Double getNominalPercentDiscount() {
        return NominalPercentDiscount;
    }

    public void setNominalPercentDiscount(Double nominalPercentDiscount) {
        NominalPercentDiscount = nominalPercentDiscount;
    }

    public Integer getIsVitalurDiscount() {
        return IsVitalurDiscount;
    }

    public void setIsVitalurDiscount(Integer isVitalurDiscount) {
        IsVitalurDiscount = isVitalurDiscount;
    }

    public Integer getIsInsurancePay() {
        return IsInsurancePay;
    }

    public void setIsInsurancePay(Integer isInsurancePay) {
        IsInsurancePay = isInsurancePay;
    }

    public Double getPercentPremium() {
        return PercentPremium;
    }

    public void setPercentPremium(Double percentPremium) {
        PercentPremium = percentPremium;
    }

    public Double getSumPremium() {
        return SumPremium;
    }

    public void setSumPremium(Double sumPremium) {
        SumPremium = sumPremium;
    }

    public String getPremiumGuid() {
        return premiumGuid;
    }

    public void setPremiumGuid(String premiumGuid) {
        this.premiumGuid = premiumGuid;
    }

    public String getMarketingEventsGuid() {
        return marketingEventsGuid;
    }

    public void setMarketingEventsGuid(String marketingEventsGuid) {
        this.marketingEventsGuid = marketingEventsGuid;
    }

    public String getRulesExecutionMarketingEventsGuid() {
        return rulesExecutionMarketingEventsGuid;
    }

    public void setRulesExecutionMarketingEventsGuid(String rulesExecutionMarketingEventsGuid) {
        this.rulesExecutionMarketingEventsGuid = rulesExecutionMarketingEventsGuid;
    }

    public String getMedCardId() {
        return medCardId;
    }

    public void setMedCardId(String medCardId) {
        this.medCardId = medCardId;
    }

    public String getPatientId() {
        return PatientId;
    }

    public void setPatientId(String patientId) {
        PatientId = patientId;
    }

    public String getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(String prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public Double getQtyDose() {
        return qtyDose;
    }

    public void setQtyDose(Double qtyDose) {
        this.qtyDose = qtyDose;
    }

    public Double getAllDoseForSale() {
        return allDoseForSale;
    }

    public void setAllDoseForSale(Double allDoseForSale) {
        this.allDoseForSale = allDoseForSale;
    }

    public Double getAllDoseSold() {
        return allDoseSold;
    }

    public void setAllDoseSold(Double allDoseSold) {
        this.allDoseSold = allDoseSold;
    }

    public Integer getIsFullSaleDose() {
        return isFullSaleDose;
    }

    public void setIsFullSaleDose(Integer isFullSaleDose) {
        this.isFullSaleDose = isFullSaleDose;
    }

    public Integer getKeyPayPositionCheck() {
        return keyPayPositionCheck;
    }

    public void setKeyPayPositionCheck(Integer keyPayPositionCheck) {
        this.keyPayPositionCheck = keyPayPositionCheck;
    }

    public String getMedicationCode() {
        return medicationCode;
    }

    public void setMedicationCode(String medicationCode) {
        this.medicationCode = medicationCode;
    }

    public String getPatientFio() {
        return patientFio;
    }

    public void setPatientFio(String patientFio) {
        this.patientFio = patientFio;
    }

    public Timestamp getDatePrescription() {
        return datePrescription;
    }

    public void setDatePrescription(Timestamp datePrescription) {
        this.datePrescription = datePrescription;
    }

    public String getMedicationDescription() {
        return medicationDescription;
    }

    public void setMedicationDescription(String medicationDescription) {
        this.medicationDescription = medicationDescription;
    }

    public Integer getRecipeType() {
        return recipeType;
    }

    public void setRecipeType(Integer recipeType) {
        this.recipeType = recipeType;
    }

    public Integer getAlgoritmRoundId() {
        return algoritmRoundId;
    }

    public void setAlgoritmRoundId(Integer algoritmRoundId) {
        this.algoritmRoundId = algoritmRoundId;
    }

    public Double getPriceFractionalPart() {
        return priceFractionalPart;
    }

    public void setPriceFractionalPart(Double priceFractionalPart) {
        this.priceFractionalPart = priceFractionalPart;
    }

    public String getMedicationCodeReallySale() {
        return medicationCodeReallySale;
    }

    public void setMedicationCodeReallySale(String medicationCodeReallySale) {
        this.medicationCodeReallySale = medicationCodeReallySale;
    }

    public String getPositionsUid() {
        return positionsUid;
    }

    public void setPositionsUid(String positionsUid) {
        this.positionsUid = positionsUid;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    public Integer getSourceOfSale() {
        return sourceOfSale;
    }

    public void setSourceOfSale(Integer sourceOfSale) {
        this.sourceOfSale = sourceOfSale;
    }

    public Integer getSignature() {
        return signature;
    }

    public void setSignature(Integer signature) {
        this.signature = signature;
    }

    public String getManualAttribute() {
        return manualAttribute;
    }

    public void setManualAttribute(String manualAttribute) {
        this.manualAttribute = manualAttribute;
    }

    public Integer getKeyPositionSourceOfSale() {
        return keyPositionSourceOfSale;
    }

    public void setKeyPositionSourceOfSale(Integer keyPositionSourceOfSale) {
        this.keyPositionSourceOfSale = keyPositionSourceOfSale;
    }

    public String getUsedCardCode() {
        return usedCardCode;
    }

    public void setUsedCardCode(String usedCardCode) {
        this.usedCardCode = usedCardCode;
    }

    public String getCardTypeGuid() {
        return cardTypeGuid;
    }

    public void setCardTypeGuid(String cardTypeGuid) {
        this.cardTypeGuid = cardTypeGuid;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public Double getSumPoints() {
        return sumPoints;
    }

    public void setSumPoints(Double sumPoints) {
        this.sumPoints = sumPoints;
    }

    public String getEmployeID() {
        return employeID;
    }

    public void setEmployeID(String employeID) {
        this.employeID = employeID;
    }

    public Integer getTypeRegistrationOfEmploye() {
        return typeRegistrationOfEmploye;
    }

    public void setTypeRegistrationOfEmploye(Integer typeRegistrationOfEmploye) {
        this.typeRegistrationOfEmploye = typeRegistrationOfEmploye;
    }

    public Integer getManualMoved() {
        return manualMoved;
    }

    public void setManualMoved(Integer manualMoved) {
        this.manualMoved = manualMoved;
    }
    
    @Override
    public String toString() {
        return "*************** Key = " + this.key.toString() + "   Чек_Key = " + this.check.getKey() + "   Остаток_Key = " + this.ostatki.getKey() + "   Цена = " + this.cost.toString() + "   MedCardId = " + this.medCardId  + " **********************";
    }
}
