package com.tracking.localization.entitys;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PACKAGE")
public class PackageEntity {

    @Id
    @GeneratedValue
    private Long id;
    private String senderTypeDocument;
    private String senderDocument;
    private String senderCompleteName;
    private String senderEmail;
    private String senderPhone;
    private String senderProduct;
    private String productWeight;
    private String productValue;
    private String targetAddress;
    private String reciverTypeDocument;
    private String reciverDocument;
    private String reciverCompleteName;
    private String reciverEmail;
    private String reciverPhone;

    public PackageEntity() {
    }

    public PackageEntity(String senderTypeDocument, String senderDocument, String senderCompleteName, String senderEmail,
                         String senderPhone, String senderProduct, String productWeight, String productValue,
                         String targetAddress, String reciverTypeDocument, String reciverDocument, String reciverCompleteName,
                         String reciverEmail, String reciverPhone) {
        this.senderTypeDocument = senderTypeDocument;
        this.senderDocument = senderDocument;
        this.senderCompleteName = senderCompleteName;
        this.senderEmail = senderEmail;
        this.senderPhone = senderPhone;
        this.senderProduct = senderProduct;
        this.productWeight = productWeight;
        this.productValue = productValue;
        this.targetAddress = targetAddress;
        this.reciverTypeDocument = reciverTypeDocument;
        this.reciverDocument = reciverDocument;
        this.reciverCompleteName = reciverCompleteName;
        this.reciverEmail = reciverEmail;
        this.reciverPhone = reciverPhone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSenderTypeDocument() {
        return senderTypeDocument;
    }

    public void setSenderTypeDocument(String senderTypeDocument) {
        this.senderTypeDocument = senderTypeDocument;
    }

    public String getSenderDocument() {
        return senderDocument;
    }

    public void setSenderDocument(String senderDocument) {
        this.senderDocument = senderDocument;
    }

    public String getSenderCompleteName() {
        return senderCompleteName;
    }

    public void setSenderCompleteName(String senderCompleteName) {
        this.senderCompleteName = senderCompleteName;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    public String getSenderPhone() {
        return senderPhone;
    }

    public void setSenderPhone(String senderPhone) {
        this.senderPhone = senderPhone;
    }

    public String getSenderProduct() {
        return senderProduct;
    }

    public void setSenderProduct(String senderProduct) {
        this.senderProduct = senderProduct;
    }

    public String getProductWeight() {
        return productWeight;
    }

    public void setProductWeight(String productWeight) {
        this.productWeight = productWeight;
    }

    public String getProductValue() {
        return productValue;
    }

    public void setProductValue(String productValue) {
        this.productValue = productValue;
    }

    public String getTargetAddress() {
        return targetAddress;
    }

    public void setTargetAddress(String targetAddress) {
        this.targetAddress = targetAddress;
    }

    public String getReciverTypeDocument() {
        return reciverTypeDocument;
    }

    public void setReciverTypeDocument(String reciverTypeDocument) {
        this.reciverTypeDocument = reciverTypeDocument;
    }

    public String getReciverDocument() {
        return reciverDocument;
    }

    public void setReciverDocument(String reciverDocument) {
        this.reciverDocument = reciverDocument;
    }

    public String getReciverCompleteName() {
        return reciverCompleteName;
    }

    public void setReciverCompleteName(String reciverCompleteName) {
        this.reciverCompleteName = reciverCompleteName;
    }

    public String getReciverEmail() {
        return reciverEmail;
    }

    public void setReciverEmail(String reciverEmail) {
        this.reciverEmail = reciverEmail;
    }

    public String getReciverPhone() {
        return reciverPhone;
    }

    public void setReciverPhone(String reciverPhone) {
        this.reciverPhone = reciverPhone;
    }
}
