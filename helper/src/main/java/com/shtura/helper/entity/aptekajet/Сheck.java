package com.shtura.helper.entity.aptekajet;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.shtura.helper.dao.jdbc.HibernateSessionFactoryUtil;

@Entity
@Table(name = "Чеки")
public class Сheck implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"Key\"")
    private Integer key;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Продавец_Key") //@Column(name = "Имя", nullable=false)
    private Seller seller;
    
    @Column(name = "Регистратор")
    private String registrar;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Покупатель_Key")
    private Buyer buyer;
    
    @Column(name = "ВремяОткрытия")
    private Timestamp openingDateTime;
    
    @Column(name = "ВремяЗакрытия")
    private Timestamp closingDateTime;
    
    @Column(name = "Номер")
    private String number;
    
    @Column(name = "КОплате")
    private Double kPay;
    
    @Column(name = "Получено")
    private Double take;
    
    @Column(name = "Шаг")
    private Integer step;
    
    @Column(name = "IsMovDiscounted")
    private Integer isMovDiscounted;
    
    @Column(name = "DiscountedId")
    private String discountedId;
    
    @Column(name = "IsReserve")
    private Integer isReserve;
    
    @Column(name = "PaymentCard")
    private Double paymentCard;
    
    @Column(name = "IdUid")
    private String idUid;
    
    @Column(name = "IsCanceled")
    private Integer isCanceled;
    
    @Column(name = "IsReturn")
    private Integer isReturn;
    
    @Column(name = "KeyPayCheck")
    private Integer keyPayCheck;
    
    @Column(name = "jsTitanCheck")
    private String jsTitanCheck;
    
    @Column(name = "jsiRetailPosCheck")
    private String jsiRetailPosCheck;
    
    @Column(name = "IsJsonCreated")
    private Integer isJsonCreated;
    
    @Column(name = "DateJsonCreated")
    private Timestamp dateJsonCreated;
    
    @Column(name = "ChequeID")
    private String chequeID;
    
    @Column(name = "TypeSend_ID")
    private Integer typeSend_ID;
    
    @Column(name = "InsuranceFIO")
    private String insuranceFIO;
    
    @Column(name = "InsuranceSeries")
    private String insuranceSeries;
    
    @Column(name = "InsuranceNumber")
    private String insuranceNumber;
    
    @Column(name = "InsuranceMedDoc")
    private String insuranceMedDoc;
    
    @Column(name = "JSONDateRecreate")
    private Timestamp jSONDateRecreate;

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller sellerId) {
        this.seller = sellerId;
    }

    public String getRegistrar() {
        return registrar;
    }

    public void setRegistrar(String registrar) {
        this.registrar = registrar;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyerId) {
        this.buyer = buyerId;
    }

    public Timestamp getOpeningDateTime() {
        return openingDateTime;
    }

    public void setOpeningTime(Timestamp openingDateTime) {
        this.openingDateTime = openingDateTime;
    }

    public Timestamp getClosingTime() {
        return closingDateTime;
    }

    public void setClosingDateTime(Timestamp closingDateTime) {
        this.closingDateTime = closingDateTime;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Double getkPay() {
        return kPay;
    }

    public void setkPay(Double kPay) {
        this.kPay = kPay;
    }

    public Double getTake() {
        return take;
    }

    public void setTake(Double take) {
        this.take = take;
    }

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }

    public Integer getIsMovDiscounted() {
        return isMovDiscounted;
    }

    public void setIsMovDiscounted(Integer isMovDiscounted) {
        this.isMovDiscounted = isMovDiscounted;
    }

    public String getDiscountedId() {
        return discountedId;
    }

    public void setDiscountedId(String discountedId) {
        this.discountedId = discountedId;
    }

    public Integer getIsReserve() {
        return isReserve;
    }

    public void setIsReserve(Integer isReserve) {
        this.isReserve = isReserve;
    }

    public Double getPaymentCard() {
        return paymentCard;
    }

    public void setPaymentCard(Double paymentCard) {
        this.paymentCard = paymentCard;
    }

    public String getIdUid() {
        return idUid;
    }

    public void setIdUid(String idUid) {
        this.idUid = idUid;
    }

    public Integer getIsCanceled() {
        return isCanceled;
    }

    public void setIsCanceled(Integer isCanceled) {
        this.isCanceled = isCanceled;
    }

    public Integer getIsReturn() {
        return isReturn;
    }

    public void setIsReturn(Integer isReturn) {
        this.isReturn = isReturn;
    }

    public Integer getKeyPayCheck() {
        return keyPayCheck;
    }

    public void setKeyPayCheck(Integer keyPayCheck) {
        this.keyPayCheck = keyPayCheck;
    }

    public String getJsTitanCheck() {
        return jsTitanCheck;
    }

    public void setJsTitanCheck(String jsTitanCheck) {
        this.jsTitanCheck = jsTitanCheck;
    }

    public String getJsiRetailPosCheck() {
        return jsiRetailPosCheck;
    }

    public void setJsiRetailPosCheck(String jsiRetailPosCheck) {
        this.jsiRetailPosCheck = jsiRetailPosCheck;
    }

    public Integer getIsJsonCreated() {
        return isJsonCreated;
    }

    public void setIsJsonCreated(Integer isJsonCreated) {
        this.isJsonCreated = isJsonCreated;
    }

    public Timestamp getDateJsonCreated() {
        return dateJsonCreated;
    }

    public void setDateJsonCreated(Timestamp dateJsonCreated) {
        this.dateJsonCreated = dateJsonCreated;
    }

    public String getChequeID() {
        return chequeID;
    }

    public void setChequeID(String chequeID) {
        this.chequeID = chequeID;
    }

    public Integer getTypeSend_ID() {
        return typeSend_ID;
    }

    public void setTypeSend_ID(Integer typeSend_ID) {
        this.typeSend_ID = typeSend_ID;
    }

    public String getInsuranceFIO() {
        return insuranceFIO;
    }

    public void setInsuranceFIO(String insuranceFIO) {
        this.insuranceFIO = insuranceFIO;
    }

    public String getInsuranceSeries() {
        return insuranceSeries;
    }

    public void setInsuranceSeries(String insuranceSeries) {
        this.insuranceSeries = insuranceSeries;
    }

    public String getInsuranceNumber() {
        return insuranceNumber;
    }

    public void setInsuranceNumber(String insuranceNumber) {
        this.insuranceNumber = insuranceNumber;
    }

    public String getInsuranceMedDoc() {
        return insuranceMedDoc;
    }

    public void setInsuranceMedDoc(String insuranceMedDoc) {
        this.insuranceMedDoc = insuranceMedDoc;
    }

    public Timestamp getjSONDateRecreate() {
        return jSONDateRecreate;
    }

    public void setjSONDateRecreate(Timestamp jSONDateRecreate) {
        this.jSONDateRecreate = jSONDateRecreate;
    }    
    
    //HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Сheck").list();
    public static List<String> listUniqueNames() {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().createNamedQuery(
              "Чеки.listUniqueNames", String.class)
              .getResultList();
    }
    
    @Override
    public String toString() {
        return "*************** Key = " + this.key.toString() + "  Продавец_Key = " + this.seller.getKey() + "   Регистратор = " + this.registrar + "   Покупатель_Key = " + this.buyer.getKey() + "   ВремяОткрытия = " + this.openingDateTime.toString() + "   ВремяЗакрытия = " + this.closingDateTime.toString() + "   Номер = " + this.number + " **********************";
    }
}
