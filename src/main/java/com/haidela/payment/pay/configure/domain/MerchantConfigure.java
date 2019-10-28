package com.haidela.payment.pay.configure.domain;


/**
 * 个体商户配置对象
 *
 * @author zhanglize
 * @create 2019/10/10
 */
public class MerchantConfigure {
    /**
     * 主键
     */
    private Long id;

    /**
     * 交易金额
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
     * 时间差
     */
    private String timeDifference;

    /**
     * 修改时间
     */
    private String modifyTime;

    /**
     * 调用时间
     */
    private String callTime;

    /**
     * 状态,状态分为0，1，2中状态其中
     * 0为可用的状态
     * 1为已完成当日交易额度的状态
     * 2为冻结状态(目前不可进行操作)
     */
    private String status;

    /**
     * 单日总额
     */
    private String totalOneAmount;

    /**
     * 公司ID
     *
     */
    private String compID;

    /**
     * 公司名称
     *
     */
    private String companyName;

    public MerchantConfigure(Long id, String amount, String amountLimit, String payType, String merchantId, String startTime, String endTime, String createTime, String timeDifference, String modifyTime, String callTime, String status, String totalOneAmount, String compID, String companyName) {
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
        this.callTime = callTime;
        this.status = status;
        this.totalOneAmount = totalOneAmount;
        this.compID = compID;
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MerchantConfigure() {
        super();
    }

    public String getCompID() {
        return compID;
    }

    public void setCompID(String compID) {
        this.compID = compID;
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

    public String getCallTime() {
        return callTime;
    }

    public void setCallTime(String callTime) {
        this.callTime = callTime;
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