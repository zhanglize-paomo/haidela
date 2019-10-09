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

}
