package com.haidela.payment.pay.configure.domain;

/**
 * 个体商户配置对象
 *
 *
 * @author zhanglize
 * @create 2019/10/10
 */
public class MerchantConfigure {

    /**
     * 主键
     */
    private int id;

    /**
     * 交易金额,单位分
     */
    private String amount;

    /**
     * 交易金额上限
     */
    private String amountLimit;

    /**
     * 支付类型
     */
    private String payType;

    /**
     * 商户id
     */
    private String merchantId;

    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 时差(同一商户在规定时间内不能被多次调用)
     */
    private String time_difference;

    /**
     * 修改时间
     */
    private String modify_time;

    /**
     * 状态
     */
    private String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getAmountLimit() {
        return amountLimit;
    }

    public void setAmountLimit(String amountLimit) {
        this.amountLimit = amountLimit;
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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getTime_difference() {
        return time_difference;
    }

    public void setTime_difference(String time_difference) {
        this.time_difference = time_difference;
    }

    public String getModify_time() {
        return modify_time;
    }

    public void setModify_time(String modify_time) {
        this.modify_time = modify_time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
