package com.yd.dby.app.entity;

import java.math.BigDecimal;
import java.util.Date;

public class YdUser {
    private Integer userId;

    private Integer userSex;

    private String userUsername;

    private String userPassword;

    private String userPaymentPassword;

    private String userNickname;

    private String userTruename;

    private String userAvatar;

    private String userMobile;

    private String userEmail;

    private String userAddress;

    private Date userBirthday;

    private Integer userGrade;

    private BigDecimal userMoney;

    private String userRole;

    private Integer userProvinceId;

    private Integer userCityId;

    private Integer userAreaId;

    private String userPca;

    private String userProvinceValue;

    private String userCityValue;

    private String userAreaValue;

    private BigDecimal userAccountBalance;

    private Integer userIntegration;

    private Integer userEmpiric;

    private Integer userTotalCoupon;

    private Integer userTotalBankcard;

    private Date userLoginTime;

    private Date userOldLoginTime;

    private Boolean userState;

    private Date userCreatedTime;

    private String userImToken;

    private Integer ctcSellNum;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserSex() {
        return userSex;
    }

    public void setUserSex(Integer userSex) {
        this.userSex = userSex;
    }

    public String getUserUsername() {
        return userUsername;
    }

    public void setUserUsername(String userUsername) {
        this.userUsername = userUsername == null ? null : userUsername.trim();
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword == null ? null : userPassword.trim();
    }

    public String getUserPaymentPassword() {
        return userPaymentPassword;
    }

    public void setUserPaymentPassword(String userPaymentPassword) {
        this.userPaymentPassword = userPaymentPassword == null ? null : userPaymentPassword.trim();
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname == null ? null : userNickname.trim();
    }

    public String getUserTruename() {
        return userTruename;
    }

    public void setUserTruename(String userTruename) {
        this.userTruename = userTruename == null ? null : userTruename.trim();
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar == null ? null : userAvatar.trim();
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile == null ? null : userMobile.trim();
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail == null ? null : userEmail.trim();
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress == null ? null : userAddress.trim();
    }

    public Date getUserBirthday() {
        return userBirthday;
    }

    public void setUserBirthday(Date userBirthday) {
        this.userBirthday = userBirthday;
    }

    public Integer getUserGrade() {
        return userGrade;
    }

    public void setUserGrade(Integer userGrade) {
        this.userGrade = userGrade;
    }

    public BigDecimal getUserMoney() {
        return userMoney;
    }

    public void setUserMoney(BigDecimal userMoney) {
        this.userMoney = userMoney;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole == null ? null : userRole.trim();
    }

    public Integer getUserProvinceId() {
        return userProvinceId;
    }

    public void setUserProvinceId(Integer userProvinceId) {
        this.userProvinceId = userProvinceId;
    }

    public Integer getUserCityId() {
        return userCityId;
    }

    public void setUserCityId(Integer userCityId) {
        this.userCityId = userCityId;
    }

    public Integer getUserAreaId() {
        return userAreaId;
    }

    public void setUserAreaId(Integer userAreaId) {
        this.userAreaId = userAreaId;
    }

    public String getUserPca() {
        return userPca;
    }

    public void setUserPca(String userPca) {
        this.userPca = userPca == null ? null : userPca.trim();
    }

    public String getUserProvinceValue() {
        return userProvinceValue;
    }

    public void setUserProvinceValue(String userProvinceValue) {
        this.userProvinceValue = userProvinceValue == null ? null : userProvinceValue.trim();
    }

    public String getUserCityValue() {
        return userCityValue;
    }

    public void setUserCityValue(String userCityValue) {
        this.userCityValue = userCityValue == null ? null : userCityValue.trim();
    }

    public String getUserAreaValue() {
        return userAreaValue;
    }

    public void setUserAreaValue(String userAreaValue) {
        this.userAreaValue = userAreaValue == null ? null : userAreaValue.trim();
    }

    public BigDecimal getUserAccountBalance() {
        return userAccountBalance;
    }

    public void setUserAccountBalance(BigDecimal userAccountBalance) {
        this.userAccountBalance = userAccountBalance;
    }

    public Integer getUserIntegration() {
        return userIntegration;
    }

    public void setUserIntegration(Integer userIntegration) {
        this.userIntegration = userIntegration;
    }

    public Integer getUserEmpiric() {
        return userEmpiric;
    }

    public void setUserEmpiric(Integer userEmpiric) {
        this.userEmpiric = userEmpiric;
    }

    public Integer getUserTotalCoupon() {
        return userTotalCoupon;
    }

    public void setUserTotalCoupon(Integer userTotalCoupon) {
        this.userTotalCoupon = userTotalCoupon;
    }

    public Integer getUserTotalBankcard() {
        return userTotalBankcard;
    }

    public void setUserTotalBankcard(Integer userTotalBankcard) {
        this.userTotalBankcard = userTotalBankcard;
    }

    public Date getUserLoginTime() {
        return userLoginTime;
    }

    public void setUserLoginTime(Date userLoginTime) {
        this.userLoginTime = userLoginTime;
    }

    public Date getUserOldLoginTime() {
        return userOldLoginTime;
    }

    public void setUserOldLoginTime(Date userOldLoginTime) {
        this.userOldLoginTime = userOldLoginTime;
    }

    public Boolean getUserState() {
        return userState;
    }

    public void setUserState(Boolean userState) {
        this.userState = userState;
    }

    public Date getUserCreatedTime() {
        return userCreatedTime;
    }

    public void setUserCreatedTime(Date userCreatedTime) {
        this.userCreatedTime = userCreatedTime;
    }

    public String getUserImToken() {
        return userImToken;
    }

    public void setUserImToken(String userImToken) {
        this.userImToken = userImToken == null ? null : userImToken.trim();
    }

    public Integer getCtcSellNum() {
        return ctcSellNum;
    }

    public void setCtcSellNum(Integer ctcSellNum) {
        this.ctcSellNum = ctcSellNum;
    }
}