package com.haidela.payment.pay.payment;

/**
 * 异步消息通知对象
 *
 * @author zhanglize
 * @create 2019/10/9
 */
public class OrderPayment {

    /**
     * 商户编号  char(16)
     */
    private String  merchantNo;

    /**
     * 平台流水号 char(32)
     */
    private String  paySerialNo;

    /**
     * 备注字段  char(100)
     */
    private String  remark;

    /**
     * 返回码  char(6)
     */
    private String  rtnCode;

    /**
     * 响应信息 char(100)
     */
    private String  rtnMsg;

    /**
     * 清算日期  char(14)
     */
    private String  settDate;

    /**
     * 交易码  char(6)
     */
    private String  tranCode;

    /**
     * 交易流水号  char(32)
     */
    private String  tranFlow;

    /**
     * 接口版本号  char(10)
     */
    private String  version;

    /**
     * 交易金额 char(16)
     */
    private String  amount;

    /**
     * 前台通知地址   char(100)  平台会发前台通知到此地址上，报文同异步通知
     */
    private String  channelNo;

    /**
     * 商户编号  char(16)
     */
    private String  YUL1;

    /**
     * 终端类型  char(100)
     */
    private String  YUL2;

    /**
     * 用户IP  char(100)
     */
    private String  YUL3;

    /**
     * 扩展字段1  Char（100）
     */
    private String  ext1;

    /**
     * 扩展字段2  Char（100）
     */
    private String  ext2;

    /**
     * 签名sign  char(512)
     */
    private String sign;

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }

    public String getPaySerialNo() {
        return paySerialNo;
    }

    public void setPaySerialNo(String paySerialNo) {
        this.paySerialNo = paySerialNo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRtnCode() {
        return rtnCode;
    }

    public void setRtnCode(String rtnCode) {
        this.rtnCode = rtnCode;
    }

    public String getRtnMsg() {
        return rtnMsg;
    }

    public void setRtnMsg(String rtnMsg) {
        this.rtnMsg = rtnMsg;
    }

    public String getSettDate() {
        return settDate;
    }

    public void setSettDate(String settDate) {
        this.settDate = settDate;
    }

    public String getTranCode() {
        return tranCode;
    }

    public void setTranCode(String tranCode) {
        this.tranCode = tranCode;
    }

    public String getTranFlow() {
        return tranFlow;
    }

    public void setTranFlow(String tranFlow) {
        this.tranFlow = tranFlow;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getChannelNo() {
        return channelNo;
    }

    public void setChannelNo(String channelNo) {
        this.channelNo = channelNo;
    }

    public String getYUL1() {
        return YUL1;
    }

    public void setYUL1(String YUL1) {
        this.YUL1 = YUL1;
    }

    public String getYUL2() {
        return YUL2;
    }

    public void setYUL2(String YUL2) {
        this.YUL2 = YUL2;
    }

    public String getYUL3() {
        return YUL3;
    }

    public void setYUL3(String YUL3) {
        this.YUL3 = YUL3;
    }

    public String getExt1() {
        return ext1;
    }

    public void setExt1(String ext1) {
        this.ext1 = ext1;
    }

    public String getExt2() {
        return ext2;
    }

    public void setExt2(String ext2) {
        this.ext2 = ext2;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
