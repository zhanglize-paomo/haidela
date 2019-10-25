package com.haidela.payment.pay.paycustomer.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

/**
 * 客户交易流水信息
 *
 * @author zhanglize
 * @create 2019/10/10
 */
public class PayCustomer extends BaseRowModel {

    /**
     * 主键
     */
    private Long id;

    /**
     * 交易流水号
     */
    @ExcelProperty(value = {"交易流水号"}, index = 0)
    private String tranFlow;

    /**
     * 交易金额
     */
    @ExcelProperty(value = {"交易金额"}, index = 1)
    private String amount;

    /**
     * 支付类型
     */
    @ExcelProperty(value = {"支付类型"}, index = 2)
    private String payType;

    /**
     * 商户id
     */
    @ExcelProperty(value = {"商户ID"}, index = 3)
    private String merchantId;

    /**
     * 商户编号
     */
    @ExcelProperty(value = {"商户编号"}, index = 4)
    private String merchantNo;

    /**
     * 买家id
     */
    @ExcelProperty(value = {"买家ID"}, index = 5)
    private String buyerId;

    /**
     * 修改时间
     */
    @ExcelProperty(value = {"交易时间"}, index = 6)
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
    @ExcelProperty(value = {"交易状态"}, index = 7)
    private String status;

    /**
     * 公司id
     */
    @ExcelProperty(value = {"公司ID"}, index = 8)
    private String compID;

    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 平台流水号
     */
    private String paySerialNo;

    /**
     * 是否接收到消息,0未接受;1接收到消息
     */
    @ExcelProperty(value = {"是否接收到消息"}, index = 9)
    private String receiveMessages;

    public PayCustomer(Long id, String tranFlow, String amount, String payType, String merchantId, String merchantNo, String buyerId, String modifyTime, String createDate, String createTime, String status, String compID, String companyName, String paySerialNo, String receiveMessages) {
        this.id = id;
        this.tranFlow = tranFlow;
        this.amount = amount;
        this.payType = payType;
        this.merchantId = merchantId;
        this.merchantNo = merchantNo;
        this.buyerId = buyerId;
        this.modifyTime = modifyTime;
        this.createDate = createDate;
        this.createTime = createTime;
        this.status = status;
        this.compID = compID;
        this.companyName = companyName;
        this.paySerialNo = paySerialNo;
        this.receiveMessages = receiveMessages;
    }

    public String getPaySerialNo() {
        return paySerialNo;
    }

    public void setPaySerialNo(String paySerialNo) {
        this.paySerialNo = paySerialNo;
    }

    public String getReceiveMessages() {
        return receiveMessages;
    }

    public void setReceiveMessages(String receiveMessages) {
        this.receiveMessages = receiveMessages;
    }

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PayCustomer() {
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
