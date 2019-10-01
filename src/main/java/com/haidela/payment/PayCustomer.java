package com.haidela.payment;

/**
 * 客户消息信息
 *
 * @author zhanglize
 * @create 2019/9/29
 */
public class PayCustomer {

    /**
     * 商户号
     */
    private String MerchantNo;

    /**
     * 交易流水号 char(32)
     */
    private String tranFlow;

    /**F
     * 交易金额 char(16) 单位分
     */
    private String amount;

    /**
     * 支付类型 char(5)
     */
    private String payType;

    /**
     * 修改时间
     */
    private String modifyTime;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 状态
     */
    private String status;

    private String id;

    public String getMerchantNo() {
        return MerchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        MerchantNo = merchantNo;
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

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
