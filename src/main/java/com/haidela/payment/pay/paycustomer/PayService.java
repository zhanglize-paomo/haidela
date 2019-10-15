package com.haidela.payment.pay.paycustomer;


import com.haidela.payment.pay.paycustomer.domain.PayCustomer;
import com.hfb.merchant.df.model.DfPay;
import com.hfb.merchant.df.sercret.CertUtil;
import com.hfb.merchant.df.util.ModelPayUtil;
import com.hfb.merchant.pay.util.DateUtil;
import com.hfb.merchant.pay.util.ParamUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;
import java.util.TreeMap;

/**
 * 实时代付
 *
 * @author zhanglize
 * @create 2019/9/30
 */

@Service
public class PayService {

    private static final Logger logger = LoggerFactory.getLogger(PayService.class);
    private static final String TAG = "【统一支付商户系统demo】-{统一交易异步通知}-";

    /**
     * 实时代付
     *
     * @param request
     * @param response
     * @param payCustomer
     * @return
     */
    public String dfPay(HttpServletRequest request, HttpServletResponse response, PayCustomer payCustomer) throws Exception {

        //商户号
        String merchantNo = "S20190927084578";

        //测试商户的公钥私钥这两个文件在本项目的src目录下certs中

//        // 私钥文件路径
//        //windows系统的文件信息
//        String privateKey = PayService.class.getResource("/").getPath() + "cert/CS20190927084578_20190927201246553.pfx";
//        // 公钥文件路径
//        //windows系统的文件信息
//        String publicKey = PayService.class.getResource("/").getPath() + "cert/SS20190927084578_20190927201246553.cer";

        // 私钥文件路径
        //Linux系统的文件信息
        //TODO 通过读取配置文件获取到相应的文件信息
        String privateKey = "/home/CS20190927084578_20190927201246553.pfx";
        // 公钥文件路径
        //Linux系统的文件信息
        //TODO 通过读取配置文件获取到相应的文件信息
        String publicKey = "/home/SS20190927084578_20190927201246553.cer";
        // 密钥密码
        String KeyPass = "666666";

        /**
         * 接口版本号
         */
        String version = "v1";
        /**
         * 渠道编号
         */
        String channelNo = "04";

        /**
         * 加密
         */
        //收款账号(银行账号)
        String accNo = "6228410784520592175";
        //收款账户名
        String accName = "m";
        /**
         * 交易码
         */
        String tranCode  = "1001";
        /**
         * 交易币种
         */
        String currency  = "RMB";
        //交易流水号
        String tranFlow = payCustomer.getTranFlow();
        //交易日期
        String tranDate = DateUtil.getDate();
        //交易时间
        String tranTime = DateUtil.getTime();
        //账户联行号
        String bankAgentId = "103537530208";
        //收款行名称
        String bankName = "中国农业银行";
        //交易金额
        String amount = payCustomer.getAmount();
        //摘要
        String remark = "测试代付";
//        //扩展字段
//        String ext1 = "1";
//        //扩展字段
//        String ext2 = "2";
//        //预留字段
//        String yUL1 = "1";
//        //预留字段
//        String yUL2 = "2";
//        //预留字段
//        String yUL3 = "3";
        //后台通知地址
        String NOTICEURL = "http://182.92.192.208:8080/order-payment";
        /**
         * 切换正式环境商户号需要到正式环境商户后台 安全中心--证书管理 功能中下载正式并启用商户证书秘钥替换掉DEMO中的证书秘钥
         进入证书管理页面下载证步骤
         1、点击  生成授权码按钮 生成授权码并复制
         2、点击 授权码后的生成新证书按钮跳转到证书生成页面
         3、输入上一步操作的授权码、商户号、密钥(证书生成页面密钥及为私钥证书密码)等相关信息
         4、点击提交可下载公私钥证书
         5、下载完证书后回到证书管理列表，在列表上查看刚才下载的证书
         6、点击启用操作 启用刚才下载的证书
         */
        // 加密工具类的创建
        CertUtil certUtil = new CertUtil(publicKey, privateKey, KeyPass, true);

        //对数据进行封装
        DfPay dfPay = new DfPay(merchantNo, tranFlow, tranDate, tranTime, accNo, accName, bankAgentId, bankName, amount, remark, null, null, null, null, null, NOTICEURL);

        // 对发送的信息，进行加密，加签，发送至合付宝平台，并对返回的信息内容进行解析，验签操作
        Map<String, String> map = ModelPayUtil.sendModelPay(certUtil, dfPay, "https://cashier.hefupal.com/paygate/v1/dfpay");

        //如果后台通知地址为null
        if (NOTICEURL != null) {
            //调用异步消息通知
            service(request, response);
        }
        return map.toString();
    }

    /**
     * 调用异步消息通知
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        try {
            TreeMap<String, String> transMap = new TreeMap<String, String>();
            Enumeration<String> enu = request.getParameterNames();
            String t = null;
            while (enu.hasMoreElements()) {
                t = enu.nextElement();
                transMap.put(t, request.getParameter(t));
            }
            logger.info(TAG + "返回数据：" + transMap);
            String merchantNo = (String) transMap.get("merchantNo");
            // 获取签名
            String sign = (String) transMap.get("sign");
            sign = sign.replaceAll(" ", "+");
            transMap.remove("sign");
            // 验签
            String transData = ParamUtil.getSignMsg(transMap);
            boolean result = false;
            try {
                com.hfb.mer.sdk.secret.CertUtil.getInstance().verify(transData, sign);
                result = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (!result) {
                logger.info(TAG + "商户编号为:" + merchantNo + "验签失败");
                throw new Exception("商户编号为:" + merchantNo + "验签失败");
            }
            logger.info(TAG + "商户编号为:" + merchantNo + "验签成功");

        } catch (Exception e) {
            logger.info(TAG + "处理异常", e);
        }
    }

}
