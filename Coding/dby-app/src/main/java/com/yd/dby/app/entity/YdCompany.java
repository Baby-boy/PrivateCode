package com.yd.dby.app.entity;

public class YdCompany {
    private Integer companyId;

    private Integer storeId;

    private Integer userId;

    private String companyName;

    private String companyAddress;

    private String companyBusinessLicence;

    private String companyOrganizationCode;

    private String companyBankAccount;

    private String legalPersonPositive;

    private String legalPersonNegative;

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress == null ? null : companyAddress.trim();
    }

    public String getCompanyBusinessLicence() {
        return companyBusinessLicence;
    }

    public void setCompanyBusinessLicence(String companyBusinessLicence) {
        this.companyBusinessLicence = companyBusinessLicence == null ? null : companyBusinessLicence.trim();
    }

    public String getCompanyOrganizationCode() {
        return companyOrganizationCode;
    }

    public void setCompanyOrganizationCode(String companyOrganizationCode) {
        this.companyOrganizationCode = companyOrganizationCode == null ? null : companyOrganizationCode.trim();
    }

    public String getCompanyBankAccount() {
        return companyBankAccount;
    }

    public void setCompanyBankAccount(String companyBankAccount) {
        this.companyBankAccount = companyBankAccount == null ? null : companyBankAccount.trim();
    }

    public String getLegalPersonPositive() {
        return legalPersonPositive;
    }

    public void setLegalPersonPositive(String legalPersonPositive) {
        this.legalPersonPositive = legalPersonPositive == null ? null : legalPersonPositive.trim();
    }

    public String getLegalPersonNegative() {
        return legalPersonNegative;
    }

    public void setLegalPersonNegative(String legalPersonNegative) {
        this.legalPersonNegative = legalPersonNegative == null ? null : legalPersonNegative.trim();
    }
}