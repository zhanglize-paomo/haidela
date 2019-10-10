package com.haidela.payment.pay.payment;


import com.haidela.payment.pay.pay.PayCustomer;
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
