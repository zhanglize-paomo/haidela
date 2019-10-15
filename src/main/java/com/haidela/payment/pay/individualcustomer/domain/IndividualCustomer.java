package com.haidela.payment.pay.individualcustomer.domain;

import java.io.Serializable;

/**
 * 商户进件个体商户信息
 *
 * @author zhanglize
 * @create 2019/10/4
 */
public class IndividualCustomer implements Serializable {


    /**
     * 主键id
     */
    private Long id;

    /**
     * 商户编号
     */
    private String merchantNo;

    /**
     * 交易币种
     */
    private String currency;
    /**
     * 商户名称
     */
    private String merchantName;

    /**
     * 商户简称
     */
    private String	merchantShortName;

    /**
     * 行业类别（mcc码）
     */
    private String industrId;

    /**
     * 省份（地区CODE码）
     */
    private String province;

    /**
     * 城市（地区CODE码）
     */
    private String city;

    /**
     * 国家（国家CODE码）
     */
    private String county;

    /**
     * 电话号
     */
    private String tel;
    /**
     * 邮箱   加密
     */
    private String email;
    /**
     * 法人手机号  加密
     */
    private String customerPhone;

    /**
     * 负责人
     */
    private String principal;
    /**
     * 负责人手机号  加密
     */
    private String principalMobile;
    /**
     * 负责人证件号   加密
     */
    private String idCode;

    /**
     * 银行卡号  加密
     */
    private String accountCode;

    /**
     * 开户银行 ID (取分支行联行号表.xlsx)
     */
    private String bankId;

    /**
     * 开户人
     */
    private String accountName;

    /**
     * 联行号 (取分支行联行号表.xlsx)
     */
    private String contactLine;

    /**
     * 银行名称 (取分支行联行号表.xlsx)
     */
    private String bankName;

    /**
     * 开户支行名称 (取分支行联行号表.xlsx)
     */
    private String bankBranchName;

    /**
     * 开户支行所在省 (取分支行联行号表.xlsx 中 省份ID)
     */
    private String bankProvince;

    /**
     * 开户支行所在市 (取分支行联行号表.xlsx 中 城市ID)
     */
    private String bankCity;

    /**
     * 持卡人身份证号  加密
     */
    private String idCard;

    /**
     * 持卡人手机号  加密
     */
    private String bankTel;

    /**
     * 法人地址
     */
    private String address;

    /**
     * 持卡人地址
     */
    private String bankAddress;

    /**
     *
     * 结算费率（信用卡手续费费率 （微信支付宝小额贷记卡 ） （单笔交易≤1000元）） 例 千分之 3 直接传3
     */
    private String billRate;

    /**
     * 借记卡手续费费率 （小额借记卡） （单笔交易≤1000元） 可空 按billRate费率计算 （开通银联多费率支付的机构必须上送）
     */
    private String billRate1;

    /**
     * 信用卡手续费费率 （大额贷记卡）（单笔交易>1000元）可空 按billRate费率计算 （开通银联多费率支付的机构必须上送）
     */
    private String billRate2;

    /**
     * 借记卡手续费费率 （大额借记卡）（单笔交易>1000元） 可空 按billRate费率计算 （开通银联多费率支付的机构必须上送）
     */
    private String billRate3;

    /**
     * 营业执照号
     */
    private String businessLicense;

    public IndividualCustomer(Long id, String merchantNo, String currency, String merchantName, String merchantShortName, String industrId, String province, String city, String county, String tel, String email, String customerPhone, String principal, String principalMobile, String idCode, String accountCode, String bankId, String accountName, String contactLine, String bankName, String bankBranchName, String bankProvince, String bankCity, String idCard, String bankTel, String address, String bankAddress, String billRate, String billRate1, String billRate2, String billRate3, String businessLicense) {
        this.id = id;
        this.merchantNo = merchantNo;
        this.currency = currency;
        this.merchantName = merchantName;
        this.merchantShortName = merchantShortName;
        this.industrId = industrId;
        this.province = province;
        this.city = city;
        this.county = county;
        this.tel = tel;
        this.email = email;
        this.customerPhone = customerPhone;
        this.principal = principal;
        this.principalMobile = principalMobile;
        this.idCode = idCode;
        this.accountCode = accountCode;
        this.bankId = bankId;
        this.accountName = accountName;
        this.contactLine = contactLine;
        this.bankName = bankName;
        this.bankBranchName = bankBranchName;
        this.bankProvince = bankProvince;
        this.bankCity = bankCity;
        this.idCard = idCard;
        this.bankTel = bankTel;
        this.address = address;
        this.bankAddress = bankAddress;
        this.billRate = billRate;
        this.billRate1 = billRate1;
        this.billRate2 = billRate2;
        this.billRate3 = billRate3;
        this.businessLicense = businessLicense;
    }

    public IndividualCustomer() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getBillRate1() {
        return billRate1;
    }

    public void setBillRate1(String billRate1) {
        this.billRate1 = billRate1;
    }

    public String getBillRate2() {
        return billRate2;
    }

    public void setBillRate2(String billRate2) {
        this.billRate2 = billRate2;
    }

    public String getBillRate3() {
        return billRate3;
    }

    public void setBillRate3(String billRate3) {
        this.billRate3 = billRate3;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getMerchantShortName() {
        return merchantShortName;
    }

    public void setMerchantShortName(String merchantShortName) {
        this.merchantShortName = merchantShortName;
    }

    public String getIndustrId() {
        return industrId;
    }

    public void setIndustrId(String industrId) {
        this.industrId = industrId;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public String getPrincipalMobile() {
        return principalMobile;
    }

    public void setPrincipalMobile(String principalMobile) {
        this.principalMobile = principalMobile;
    }

    public String getIdCode() {
        return idCode;
    }

    public void setIdCode(String idCode) {
        this.idCode = idCode;
    }


    public String getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getContactLine() {
        return contactLine;
    }

    public void setContactLine(String contactLine) {
        this.contactLine = contactLine;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankBranchName() {
        return bankBranchName;
    }

    public void setBankBranchName(String bankBranchName) {
        this.bankBranchName = bankBranchName;
    }

    public String getBankProvince() {
        return bankProvince;
    }

    public void setBankProvince(String bankProvince) {
        this.bankProvince = bankProvince;
    }

    public String getBankCity() {
        return bankCity;
    }

    public void setBankCity(String bankCity) {
        this.bankCity = bankCity;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getBankTel() {
        return bankTel;
    }

    public void setBankTel(String bankTel) {
        this.bankTel = bankTel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBankAddress() {
        return bankAddress;
    }

    public void setBankAddress(String bankAddress) {
        this.bankAddress = bankAddress;
    }

    public String getBillRate() {
        return billRate;
    }

    public void setBillRate(String billRate) {
        this.billRate = billRate;
    }

    public String getBusinessLicense() {
        return businessLicense;
    }

    public void setBusinessLicense(String businessLicense) {
        this.businessLicense = businessLicense;
    }



}
