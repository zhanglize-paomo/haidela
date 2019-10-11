package com.haidela.payment.pay.payment;


import com.haidela.payment.pay.paycustomer.domain.PayCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 客户支付交易请求报文
 *
 *
 * @author zhanglize
 * @create 2019/9/29
 */
@RestController
public class PaymentController {

    private PaymentService paymentService;

    @Autowired
    public void setPaymentService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    /**
     * 异步消息通知接口
     * 通知我们订单处理的结果是成功还是失败,其他的状态均视为交易进行中
     *
     * @return
     */
    @RequestMapping(path = "/order-payment")
    public Map<String, String> orderPayment(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> result = new HashMap<String, String>();
        String merchantNo = request.getParameter("merchantNo");//商户编号
        if (null == merchantNo || merchantNo.equals("")) {
            result.put("code", "3001");
            result.put("msg", "商户编号不可为空");
            return result;
        }
        String version = request.getParameter("version");//接口版本号
        if (null == version || version.equals("")) {
            result.put("code", "3002");
            result.put("msg", "接口版本号不可为空");
            return result;
        }
        String channelNo = request.getParameter("channelNo");//渠道编号
        if (null == channelNo || channelNo.equals("")) {
            result.put("code", "3003");
            result.put("msg", "渠道编号不可为空");
            return result;
        }
        String tranCode = request.getParameter("tranCode");//交易码
        if (null == tranCode || tranCode.equals("")) {
            result.put("code", "3004");
            result.put("msg", "交易码不可为空");
            return result;
        }
        String amount = request.getParameter("amount");//交易金额
        if (null == amount || amount.equals("")) {
            result.put("code", "3005");
            result.put("msg", "交易金额不可为空");
            return result;
        }
        String paySerialNo = request.getParameter("paySerialNo");//平台流水号
        if (null == paySerialNo || paySerialNo.equals("")) {
            result.put("code", "3006");
            result.put("msg", "渠道编号不可为空");
            return result;
        }

        String sign = request.getParameter("sign");//签名
        if (null == sign || sign.equals("")) {
            result.put("code", "3007");
            result.put("msg", "签名不可为空");
            return result;
        }

//        String tranFlow = request.getParameter("tranFlow");//交易流水号
//        if (null == tranFlow || tranFlow.equals("")) {
//            result.put("code", "3005");
//            result.put("msg", "交易流水号不可为空");
//            return result;
//        }
//
//        String tranSerialNum = request.getParameter("tranSerialNum");//交易流水号
//        if (null == tranSerialNum || tranSerialNum.equals("")) {
//            result.put("code", "3006");
//            result.put("msg", "交易流水号不可为;
//            return result;
//        }
        String string = null;
        try {
            string = paymentService.orderPayment(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (string != null) {
            result.put("code", "200");
            result.put("msg", "异步消息接收成功");
        } else {
            result.put("code", "9999");
            result.put("msg", "异步消息接收失败");
        }
        return result;
    }

    /**
     * 客户支付交易请求报文(落雨轩)
     *
     * @return
     */
    @RequestMapping(path = "/payment")
    @ResponseBody
    public Map<String,String> payment(HttpServletRequest request, HttpServletResponse response ){
        Map<String,String> result = new HashMap<String, String>();
        result.put("code","0");//成功
        //支付图片的url
        String imgUrl="";
        /**
         * 1获取客户端的请求参数，校验不可为空
         *
         *
         * 2、调用 获取二维码请求地址
         *
         * 3、返回图片
         *
         */
        String tranFlow = request.getParameter("tranFlow");//流水号
        if(null == tranFlow || tranFlow.equals("")){
            result.put("code","2001");
            result.put("msg","流水号不可为空");
            return result;
        }
        String amount = request.getParameter("amount");//交易金额
        String payType = request.getParameter("payType");//支付类型
        PayCustomer payCustomer = new PayCustomer();
        payCustomer.setTranFlow(tranFlow);
        payCustomer.setBuyerId(request.getParameter("buyerId"));//买家id
        payCustomer.setAmount(amount);
        payCustomer.setPayType(payType);
        try {
            imgUrl =  paymentService.getImgurl(request,response,payCustomer);
            result.put("imgUrl",imgUrl);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("code","9999");
            result.put("msg","支付失败，请重试");
        }
        result.put("msg","success");
        return result;
    }

    /**
     * 客户支付交易请求报文
     *
     * 入驻ID:YSM201908081719455501620025977
     * 商户ID：873190924119746279
     * 提供信息例如：
     * 商户号：401530011651
     * md5的key：adc2fbfb4654ed95b28dfe0a0cb03da6
     * 加密的公钥信息publicKey：MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDFf6krDWQUDUzKj+K+WvML2EyZLagKaJ5YTeCoBNhx/WpD1Vh2j/
     * wQ9G3RC/tpUcmE7szr/vgdEVHkOfk6mGpeHapS6QE4enJ/CVaTPM573uI8VGWBek9v/E6HaVxRV0Hs8ZsvHAKopqYNDZRKhIrlLUrrkFD2KXJgIiRPQALeMQIDAQAB
     *
     *
     * @return
     */
    @RequestMapping(path = "/other-payment")
    @ResponseBody
    public Map<String,String> otherPayment(HttpServletRequest request, HttpServletResponse response ){
        Map<String,String> result = new HashMap<String, String>();
        result.put("code","0");//成功
        //支付图片的url
        String imgUrl="";
        /**
         * 1获取客户端的请求参数，校验不可为空
         * 2、调用 获取二维码请求地址
         * 3、返回图片
         */
        String tranFlow = request.getParameter("tranFlow");//流水号
        if(null == tranFlow || tranFlow.equals("")){
            result.put("code","2001");
            result.put("msg","流水号不可为空");
            return result;
        }
        String amount = request.getParameter("amount");//交易金额
        String payType = request.getParameter("payType");//支付类型
        PayCustomer payCustomer = new PayCustomer();
        payCustomer.setTranFlow(tranFlow);
        payCustomer.setAmount(amount);
        payCustomer.setPayType(payType);
        try {
            imgUrl =  paymentService.getOtherImgurl(request,response,payCustomer);
            result.put("imgUrl",imgUrl);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("code","9999");
            result.put("msg","支付失败，请重试");
        }
        result.put("msg","success");
        return result;
    }


}
