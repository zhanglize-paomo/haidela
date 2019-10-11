package com.haidela.payment.pay.paycustomer.domain;

/**
 * 客户交易流水信息
 *
 * @author zhanglize
 * @create 2019/10/10
 */
public class PayCustomer {

    /**
     * 主键
     */
    private String id;

    /**
     * 交易流水号
     */
    private String tranFlow;

    /**
     * 交易金额
     */
    private String amount;

    /**
     * 支付类型
     */
    private String payType;

    /**
     * 商户id
     */
    private String merchantId;

    /**
     * 买家id
     */
    private String buyerId;

    /**
     * 修改时间
     */
    private String modifyTime;

    /**
     * 创建日期
     */
    private String createDate;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 交易状态
     */
    private String status;

    /**
     * 公司id
     */
    private String compID;

    /**
     * 公司名称
     */
    private String companyName;

    public PayCustomer(String id, String tranFlow, String amount, String payType, String merchantId, String buyerId, String modifyTime, String createDate, String createTime, String status, String compID, String companyName) {
        this.id = id;
        this.tranFlow = tranFlow;
        this.amount = amount;
        this.payType = payType;
        this.merchantId = merchantId;
        this.buyerId = buyerId;
        this.modifyTime = modifyTime;
        this.createDate = createDate;
        this.createTime = createTime;
        this.status = status;
        this.compID = compID;
        this.companyName = companyName;
    }

    public PayCustomer() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTranFlow() {
        return tranFlow;
    }

    public void setTranFlow(String tranFlow) {
        this.tranFlow = tranFlow;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCompID() {
        return compID;
    }

    public void setCompID(String compID) {
        this.compID = compID;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
