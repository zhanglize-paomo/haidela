package com.haidela.payment.pay;

/**
 * @author zhanglize
 * @create 2019/9/30
 */
public class Merchant {

    /**
     * 商户编号
     */
    private String MerchantNo;

    /**
     * 额度
     */
    private int quota;

    /**
     * 时间段
     */
    private String timeSlot;

    /**
     * 时间  单位秒
     */
    private String time;

    public String getMerchantNo() {
        return MerchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        MerchantNo = merchantNo;
    }

    public int getQuota() {
        return quota;
    }

    public void setQuota(int quota) {
        this.quota = quota;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
