package com.haidela.payment.pay.payment;

/**
 * @author zhanglize
 * @create 2019/9/29
 */
public class Payment {

    /**
     * 商户编号  char(16)
     */
    private String merchantNo;

    /**
     * 接口版本号  char(16)
     */
    private String version;

    /**
     * 渠道编号 char(10)
     */
    private String channelNo;

    /**
     * 交易码 char(6)
     */
    private String tranCode;

    /**
     * 交易日期 char(8)  交易日期：yyyyMMdd
     */
    private String tranDate;

    /**
     * 交易时间 char(8) 交易时间：HHmmss
     */
    private String tranTime;

    /**
     * 交易币种 char(3)
     */
    private String currency;

    /**
     * 入驻 char(32)
     */
    private String bindId;

    /**
     * 银行编号 char(10)
     */
    private String bankId;

    /**
     * 支付卡种 Char（2）
     */
    private String cardType;

    /**
     * 结果通知地址 char(100)
     */
    private String notifyUrl;

    /**
     * 业务代码 char(5)
     */
    private String bizType;

    /**
     * 商品名称  char(100)
     */
    private String goodsName;

    /**
     * 商品信息  char(100)
     */
    private String goodsInfo;

    /**
     * 支付网址
     */
    private String referer;

    /**
     * 商品数量  char(100)
     */
    private String goodsNum;

    /**
     * 买家姓名  char(256)
     */
    private String buyerName;

    /**
     * 买家联系方式  char(256)  手机号或者邮箱（加密传输）
     */
    private String contact;

    /**
     * 买家ID  char(100)  买家在商城的唯一编号
     */
    private String buyerId;

    /**
     * 订单有效时间
     */
    private Long valid;

    /**
     * 备注  Char（100）
     */
    private String remark;

    /**
     * 扩展字段1  Char（100）
     */
    private String ext1;

    /**
     * 扩展字段2  Char（100）
     */
    private String ext2;

    /**
     * 前台通知地址   char(100)  平台会发前台通知到此地址上，报文同异步通知
     */
    private String YUL1;

    /**
     * 终端类型  char(100)
     */
    private String YUL2;

    /**
     * 用户IP  char(100)
     */
    private String YUL3;

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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getChannelNo() {
        return channelNo;
    }

    public void setChannelNo(String channelNo) {
        this.channelNo = channelNo;
    }

    public String getTranCode() {
        return tranCode;
    }

    public void setTranCode(String tranCode) {
        this.tranCode = tranCode;
    }

    public String getTranDate() {
        return tranDate;
    }

    public void setTranDate(String tranDate) {
        this.tranDate = tranDate;
    }

    public String getTranTime() {
        return tranTime;
    }

    public void setTranTime(String tranTime) {
        this.tranTime = tranTime;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getBindId() {
        return bindId;
    }

    public void setBindId(String bindId) {
        this.bindId = bindId;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsInfo() {
        return goodsInfo;
    }

    public void setGoodsInfo(String goodsInfo) {
        this.goodsInfo = goodsInfo;
    }

    public String getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(String goodsNum) {
        this.goodsNum = goodsNum;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public Long getValid() {
        return valid;
    }

    public void setValid(Long valid) {
        this.valid = valid;
    }

    public String getReferer() {
        return referer;
    }

    public void setReferer(String referer) {
        this.referer = referer;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
