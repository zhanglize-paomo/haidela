package com.haidela.payment.pay.configure.domain;

public class MerchantConfigure {
    private Integer id;

    private String amount;

    private String amountLimit;

    private String payType;

    private String merchantId;

    private String startTime;

    private String endTime;

    private String createTime;

    private String timeDifference;

    private String modifyTime;

    private String status;

    private String totalOneAmount;

    public MerchantConfigure(Integer id, String amount, String amountLimit, String payType, String merchantId, String startTime, String endTime, String createTime, String timeDifference, String modifyTime, String status, String totalOneAmount) {
        this.id = id;
        this.amount = amount;
        this.amountLimit = amountLimit;
        this.payType = payType;
        this.merchantId = merchantId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.createTime = createTime;
        this.timeDifference = timeDifference;
        this.modifyTime = modifyTime;
        this.status = status;
        this.totalOneAmount = totalOneAmount;
    }

    public MerchantConfigure() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount == null ? null : amount.trim();
    }

    public String getAmountLimit() {
        return amountLimit;
    }

    public void setAmountLimit(String amountLimit) {
        this.amountLimit = amountLimit == null ? null : amountLimit.trim();
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType == null ? null : payType.trim();
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId == null ? null : merchantId.trim();
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime == null ? null : startTime.trim();
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime == null ? null : endTime.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public String getTimeDifference() {
        return timeDifference;
    }

    public void setTimeDifference(String timeDifference) {
        this.timeDifference = timeDifference == null ? null : timeDifference.trim();
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime == null ? null : modifyTime.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getTotalOneAmount() {
        return totalOneAmount;
    }

    public void setTotalOneAmount(String totalOneAmount) {
        this.totalOneAmount = totalOneAmount == null ? null : totalOneAmount.trim();
    }
}