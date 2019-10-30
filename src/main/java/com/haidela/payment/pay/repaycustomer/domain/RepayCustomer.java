package com.haidela.payment.pay.repaycustomer.domain;

/**
 * 代付消息接收情况
 *
 *
 * @author zhanglize
 * @create 2019/10/15
 */
public class RepayCustomer {

    /**
     * 主键
     */
    private Long id;

    /**
     * 交易流水号
     */
    private String tranFlow;

    /**
     * 代付交易流水号
     */
    private String repayTranFlow;

    /**
     * 交易金额,单位分
     */
    private String amount;

    /**
     * 支付类型
     */
    private String payType;

    /**
     * 商户编号
     */
    private String merchantNo;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 创建日期(年月日)
     */
    private String createDate;

    /**
     * 修改时间
     */
    private String modifyTime;

    /**
     * 代付交易状态
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
    /**
     *  平台流水号
     */
    private String paySerialNo;


    public RepayCustomer(Long id, String tranFlow, String repayTranFlow, String amount, String payType, String merchantNo, String createTime, String createDate, String modifyTime, String status, String compID, String companyName, String paySerialNo) {
        this.id = id;
        this.tranFlow = tranFlow;
        this.repayTranFlow = repayTranFlow;
        this.amount = amount;
        this.payType = payType;
        this.merchantNo = merchantNo;
        this.createTime = createTime;
        this.createDate = createDate;
        this.modifyTime = modifyTime;
        this.status = status;
        this.compID = compID;
        this.companyName = companyName;
        this.paySerialNo = paySerialNo;
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

    public RepayCustomer() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTranFlow() {
        return tranFlow;
    }

    public void setTranFlow(String tranFlow) {
        this.tranFlow = tranFlow;
    }

    public String getRepayTranFlow() {
        return repayTranFlow;
    }

    public void setRepayTranFlow(String repayTranFlow) {
        this.repayTranFlow = repayTranFlow;
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

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
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

    public String getPaySerialNo() {
        return paySerialNo;
    }

    public void setPaySerialNo(String paySerialNo) {
        this.paySerialNo = paySerialNo;
    }

    @Override
    public String toString() {
        return "RepayCustomer{" +
                "id=" + id +
                ", tranFlow='" + tranFlow + '\'' +
                ", repayTranFlow='" + repayTranFlow + '\'' +
                ", amount='" + amount + '\'' +
                ", payType='" + payType + '\'' +
                ", merchantNo='" + merchantNo + '\'' +
                ", createTime='" + createTime + '\'' +
                ", createDate='" + createDate + '\'' +
                ", modifyTime='" + modifyTime + '\'' +
                ", status='" + status + '\'' +
                ", compID='" + compID + '\'' +
                ", companyName='" + companyName + '\'' +
                ", paySerialNo='" + paySerialNo + '\'' +
                '}';
    }
}
