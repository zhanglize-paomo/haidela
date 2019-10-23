package com.haidela.payment.pay.repaycustomer;

import com.haidela.payment.pay.paycustomer.domain.PayCustomer;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 实时代付
 *
 * @author zhanglize
 * @create 2019/9/30
 */
@RestController("payController")
@RequestMapping("/pay")
public class PayController {


    private PayService payService;

    @Resource
    public void setPayService(PayService payService) {
        this.payService = payService;
    }


    /**
     * 客户实时代付交易请求报文
     *
     * @return
     */
    @RequestMapping(path = "/pay")
    public Map<String, String> DfPay(HttpServletRequest request, HttpServletResponse response) {
        /**
         * 1.第三方支付调用我们的接口,告诉我们第三方支付的货款已经到账
         * 2.我们随即调用我们的支付功能,将货款转入我们的银行账号中
         * 3.然后转入成功后我们需要给第三方返回一个调用成功的信息否则二通知第三方调用失败
         */
        Map<String, String> result = new HashMap<String, String>();
        String tranFlow = request.getParameter("tranFlow");//流水号
        if (null == tranFlow || tranFlow.equals("")) {
            result.put("code", "2001");
            result.put("msg", "流水号不可为空");
            return result;
        }
        String merchantNo = request.getParameter("merchantNo");//商户号
        PayCustomer payCustomer = new PayCustomer();
        payCustomer.setTranFlow(tranFlow);
        payCustomer.setMerchantNo(merchantNo);
        boolean string = false;
        try {
            string = payService.dfPay(request, response,payCustomer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (string == true) {
            result.put("code", "200");
            result.put("msg", "代付成功");
        } else {
            result.put("code", "9999");
            result.put("msg", "代付失败，请重试");
        }
        return result;
    }


    /**
     * 客户人工代付交易请求报文
     *
     * @return
     */
    @RequestMapping(path = "/other-pay")
    public Map<String, String> otherDfPay(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> result = new HashMap<String, String>();
        String tranFlow = request.getParameter("tranFlow");//流水号
        if (null == tranFlow || tranFlow.equals("")) {
            result.put("code", "2001");
            result.put("msg", "流水号不可为空");
            return result;
        }
        String merchantNo = request.getParameter("merchantNo");//商户号
        String amount = request.getParameter("amount");//金额
        PayCustomer payCustomer = new PayCustomer();
        payCustomer.setTranFlow(tranFlow);
        payCustomer.setMerchantNo(merchantNo);
        payCustomer.setAmount(amount);
        boolean string = false;
        try {
            string = payService.otherDfPay(request, response,payCustomer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (string == true) {
            result.put("code", "200");
            result.put("msg", "代付成功");
        } else {
            result.put("code", "9999");
            result.put("msg", "代付失败，请重试");
        }
        return result;
    }


    /**
     * 获取当日实时余额
     *
     * @return
     */
    @RequestMapping(path = "/balance/{merchantNo}")
    public Map<String,Object> getQueryBalance(@PathVariable String merchantNo) {
      return payService.getQueryBalance(merchantNo);
    }

    /**
     * 查询所有的商户所对应的余额信息
     *
     * @return
     */
    @RequestMapping(path = "/all/balance")
    public List<Map<String,String>> getAllQueryBalance() {
        return payService.getAllQueryBalance();
    }



    @RequestMapping(path = "/hello")
    public Map<String,String> DfPay() {
        Map<String,String> map = new HashMap<>();
        map.put("1","1");
        map.put("2","2");
        return map;
    }

}
