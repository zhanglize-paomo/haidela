package com.haidela.payment.pay.payment;


import com.haidela.payment.pay.paycustomer.domain.PayCustomer;
import com.haidela.payment.util.MD5;
import com.haidela.payment.util.SecuritySHA1Utils;
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
     * 生成加密的数据信息
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(path = "/md5")
    public String md5(HttpServletRequest request, HttpServletResponse response) {
        String tranFlow = request.getParameter("tranFlow");
        Map<String, String> map = new HashMap<>();
        map.put("tranFlow", request.getParameter("tranFlow"));
        map.put("compID", request.getParameter("compID"));
        map.put("amount", request.getParameter("amount"));
        map.put("payType", request.getParameter("payType"));
        map.put("buyerId", request.getParameter("buyerId"));
        String str = MD5.getSignContent(map, "", "");
        //将数据进行拼接
        String secret = "9989639630683" + str;
        String md5 = "";
        //首先将数据进行sha1算法
        try {
            String security = MD5.md5(SecuritySHA1Utils.shaEncode(secret));
            //将流水号进行md5加密处理
            md5 = MD5.md5(security.trim() + MD5.md5(tranFlow).trim());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return md5;
    }


    /**
     * 客户支付交易请求报文(落雨轩)
     *
     * @return
     */
    @RequestMapping(path = "/payment")
    @ResponseBody
    public Map<String, String> payment(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> result = new HashMap<String, String>();
        result.put("code", "0");//成功
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
        if (null == tranFlow || tranFlow.equals("")) {
            result.put("code", "2001");
            result.put("msg", "流水号不可为空");
            return result;
        }
        String compID = request.getParameter("compID"); //公司id
        String amount = request.getParameter("amount");//交易金额
        String payType = request.getParameter("payType");//支付类型
        String buyerId = request.getParameter("buyerId");//买家id
        String digest = request.getParameter("digest"); //加密数据信息
        Map<String, String> map = new HashMap<>();
        map.put("tranFlow", request.getParameter("tranFlow"));
        map.put("compID", request.getParameter("compID"));
        map.put("amount", request.getParameter("amount"));
        map.put("payType", request.getParameter("payType"));
        map.put("buyerId", request.getParameter("buyerId"));
        String str = MD5.getSignContent(map, "", "");
        try {
            //将数据进行拼接
            String secret = "9989639630683" + str;
            //首先将数据进行sha1算法
            String security = MD5.md5(SecuritySHA1Utils.shaEncode(secret));
            //将流水号进行md5加密处理
            String md5 = MD5.md5(tranFlow);
            //然后将所有的数据进行md5加密
            if (!digest.equals(MD5.md5(security.trim() + md5.trim()))) {
                result.put("code", "2002");
                result.put("msg", "数据加密异常,请重试");
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        PayCustomer payCustomer = new PayCustomer();
        payCustomer.setTranFlow(tranFlow);
        payCustomer.setBuyerId(buyerId);
        payCustomer.setAmount(amount);
        payCustomer.setPayType(payType);
        payCustomer.setCompID(compID);
        try {
            result = paymentService.getImgurl(request, response, payCustomer);
            if (("订单流水号已经存在").equals(result.get("msg"))) {
                result.remove("merchantId");
                result.put("payUrl", "");
                return result;
            } else if (result.get("merchantId") == null || result.get("merchantId").equals("")) {
                result.remove("merchantId");
                result.put("payUrl", "");
                return result;
            } else if (result.get("payUrl").equals("null")|| result.get("payUrl").equals("") || result.get("payUrl") == null) {
                result.remove("merchantId");
                result.put("payUrl", "");
                result.put("code", "9999");
                result.put("msg", "支付失败，请重试");
                return result;
            } else {
                result.put("code", "0000");
                result.remove("merchantId");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        result.put("msg", "success");
        return result;
    }

    /**
     * 客户支付交易测试接口
     *
     * @return
     */
    @RequestMapping(path = "/other-payment")
    @ResponseBody
    public Map<String, String> otherPayment(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> result = new HashMap<String, String>();
        result.put("code", "0");//成功
        String tranFlow = request.getParameter("tranFlow");//流水号
        if (null == tranFlow || tranFlow.equals("")) {
            result.put("code", "2001");
            result.put("msg", "流水号不可为空");
            return result;
        }
        String merchantId = request.getParameter("merchantId"); //个人商户id
        if(merchantId == null || merchantId.equals("")){
            result.put("code", "2002");
            result.put("msg", "个人商户id不能为空");
            return result;
        }
        request.getParameter("");
        String compID = request.getParameter("compID"); //公司id
        String amount = request.getParameter("amount");//交易金额
        String payType = request.getParameter("payType");//支付类型
        String buyerId = request.getParameter("buyerId");//买家id
        String digest = request.getParameter("digest"); //加密数据信息
        Map<String, String> map = new HashMap<>();
        map.put("tranFlow", request.getParameter("tranFlow"));
        map.put("compID", request.getParameter("compID"));
        map.put("amount", request.getParameter("amount"));
        map.put("payType", request.getParameter("payType"));
        map.put("buyerId", request.getParameter("buyerId"));
        String str = MD5.getSignContent(map, "", "");
        try {
            //将数据进行拼接
            String secret = "9989639630683" + str;
            //首先将数据进行sha1算法
            String security = MD5.md5(SecuritySHA1Utils.shaEncode(secret));
            //将流水号进行md5加密处理
            String md5 = MD5.md5(tranFlow);
            //然后将所有的数据进行md5加密
            if (!digest.equals(MD5.md5(security.trim() + md5.trim()))) {
                result.put("code", "2002");
                result.put("msg", "数据加密异常,请重试");
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        PayCustomer payCustomer = new PayCustomer();
        payCustomer.setTranFlow(tranFlow);
        payCustomer.setBuyerId(buyerId);
        payCustomer.setAmount(amount);
        payCustomer.setPayType(payType);
        payCustomer.setCompID(compID);
        payCustomer.setMerchantId(merchantId);
        try {
            result = paymentService.getOtherImgurl(request, response, payCustomer);
            if (("订单流水号已经存在").equals(result.get("msg"))) {
                result.remove("merchantId");
                result.put("payUrl", "");
                return result;
            } else if (result.get("merchantId") == null || result.get("merchantId").equals("")) {
                result.remove("merchantId");
                result.put("payUrl", "");
                return result;
            } else if (result.get("payUrl").equals("null")|| result.get("payUrl").equals("") || result.get("payUrl") == null) {
                result.remove("merchantId");
                result.put("payUrl", "");
                result.put("code", "9999");
                result.put("msg", "支付失败，请重试");
                return result;
            } else {
                result.put("code", "0000");
                result.remove("merchantId");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        result.put("msg", "success");
        return result;
    }


    /**
     * 测试异步消息通知接口
     * 通知我们订单处理的结果是成功还是失败,其他的状态均视为交易进行中
     *
     * @return
     */
    @RequestMapping(path = "/other-order-payment")
    public Map<String, String> otherOrderPayment(HttpServletRequest request, HttpServletResponse response) {

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
        String string = null;
        try {
            string = paymentService.otherOrderPayment(request, response);
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



}
