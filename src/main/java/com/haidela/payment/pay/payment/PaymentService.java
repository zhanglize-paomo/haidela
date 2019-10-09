package com.haidela.payment.pay.payment;


import com.haidela.payment.common.Config;
import com.haidela.payment.pay.Merchant;
import com.haidela.payment.pay.pay.PayCustomer;
import com.haidela.payment.pay.pay.PayService;
import com.haidela.payment.util.ResponseUtil;
import com.hfb.mer.sdk.secret.CertUtil;
import com.hfb.merchant.pay.util.DateUtil;
import com.hfb.merchant.pay.util.ParamUtil;
import com.hfb.merchant.pay.util.http.Httpz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author zhanglize
 * @create 2019/9/29
 */
@Service
public class PaymentService extends HttpServlet {

    private static final long serialVersionUID = 1L;
    //    private static final Logger logger = LoggerFactory.getLogger(PaymentService.class);
    private static final String TAG = "【统一支付商户系统demo】-{统一支付}-";

    private PayService payService;

    @Autowired
    public void setPayService(PayService payService) {
        this.payService = payService;
    }

    /**
     * 客户支付交易请求报文(落雨轩)
     *
     * @param request
     * @param response
     * @param customer 客户消息信息
     */
    public String payment(HttpServletRequest request, ServletResponse response, PayCustomer customer) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String msg = "处理成功";
//        String redirectPath = "result.jsp";
        Map<String, String> resultMap = null;
        String payUrl = "";
        try {
            // 利用treeMap对参数按key值进行排序
            Map<String, String> transMap = ResponseUtil.getParamMap(request);

            //交易流水号	tranFlow	char(32)	必输	客服提供	交易流水号
            //交易金额	amount	char(16)	必输	客服提供	单位：分
            // 支付类型	payType	char(5)	必输	客服提供	1：微信，2：支付宝，3：银联
            //买家 ID	buyerId	char(100)	必输	客户提供	买家在商城的唯一编号

            String payType = transMap.get("payType");
            /**
             * 备注  Char（100）
             */
            String remark = "客户支付交易";

            /**
             * 用户IP  char(100)
             * 支持匹配用户的IP规则
             */
            String YUL3 = "4234242424";
            /**
             * 订单有效时间
             */
            Long valid = 3600L;

            /**
             * 交易币种 char(3)
             */
            String currency = "CNY";
            /**
             * 业务代码 char(5)
             */
            String bizType = "16";
            /**
             * 商品名称  char(100)
             */
            String goodsName = "商品名称";
            /**
             * 轮询池,将客户端的随机选取商户,并在某一些时间内不能重复选取某个商户
             * 个体工商户id(我们自己的)
             */
            String goodsInfo = "873190924119746279";
            //String goodsNum = "1";
            String merchantNo = "S20190927084578"; //商户编号
            String version = Config.getInstance().getVersion();
            String bindId = "YSM201909271637141884536731670";    //入驻ID
            String channelNo = Config.getInstance().getChannelNo();
//            String notifyUrl = Config.getInstance().getNotifyUrl();
            String notifyUrl = "https://cashier.hefupal.com/paygate/v1/smpay";
            String tranCode = "YS1003";
            String tranDate = DateUtil.getDate();
            String tranTime = DateUtil.getTime();
            String buyerName = "213213";
            String contact = "213131233";
            //String cardType = "01";
            String ext1 = "324242424";
            String ext2 = "873190924119746279";
            String YUL1 = "1241242424";
            String YUL2 = "ANDROID";
            // 组织交易报文
            transMap.put("merchantNo", merchantNo);
            transMap.put("YUL1", YUL1);
            transMap.put("YUL2", YUL2);
            transMap.put("ext1", ext1);
            transMap.put("ext2", ext2);
            //transMap.put("goodsNum", goodsNum);
            transMap.put("goodsInfo", goodsInfo);
            //transMap.put("cardType", cardType);
            transMap.put("notifyUrl", notifyUrl);
            transMap.put("goodsName", goodsName);
            //transMap.put("buyerId", buyerId);
            transMap.put("bindId", bindId);
            transMap.put("bizType", bizType);
            transMap.put("currency", currency);
            transMap.put("valid", valid.toString());
            transMap.put("YUL3", YUL3);
            transMap.put("remark", remark);
            transMap.put("version", version);
            transMap.put("channelNo", channelNo);
            transMap.put("tranCode", tranCode);
            transMap.put("tranFlow", customer.getTranFlow());
            transMap.put("tranDate", tranDate);
            transMap.put("tranTime", tranTime);
            transMap.put("payType", customer.getPayType());
            transMap.put("amount", customer.getAmount());
            // 敏感信息加密
            transMap.put("buyerName", CertUtil.getInstance().encrypt(buyerName));
            transMap.put("contact", CertUtil.getInstance().encrypt(contact));

//            /**
//             * 签名sign  char(512)
//             */
//             String sign;
            /**
             * 数字签名根据算法进行加密
             *
             */
            // 组织签名字符串
            String signMsg = ParamUtil.getSignMsg(transMap);
            // 签名
            String sign = CertUtil.getInstance().sign(signMsg);
//            String sign = MD5.md5(transMap.toString());
            // 将签名放入交易map中
            transMap.put("sign", sign);

            // 发送扫码请求报文
//            logger.info(TAG + "请求报文：" + transMap);
            String asynMsg = new Httpz().post(Config.getInstance().getPaygateReqUrl(), transMap);
//            logger.info(TAG + "返回报文：" + asynMsg);
            // 解析返回
            resultMap = ResponseUtil.parseResponse(asynMsg);
//            logger.info("请求结果返回解析数据：" + resultMap);
            // 当支付类型payType为24或者25时，返回qrCodeURL的地址使用POST请求
            payUrl = resultMap.get("qrCodeURL");
            if ("24".equals(payType) || "25".equals(payType)) {
                if ("".equals(payUrl) || payUrl == null || "null".equals(payUrl)) {
                    if (resultMap.get("rtnMsg") != null) {
                        msg = resultMap.get("rtnMsg").toString();
                    }
                    request.setAttribute("resultMap", resultMap);
                } else {
                    request.setAttribute("action", payUrl);
//                    redirectPath = "webPayUrl.jsp";
                }
            } else {
                if (resultMap.get("rtnMsg") != null) {
                    msg = resultMap.get("rtnMsg").toString();
                }
                request.setAttribute("resultMap", resultMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
//            redirectPath = "error.jsp";
        } finally {
            request.setAttribute("errorMsg", msg);
//            request.getRequestDispatcher(redirectPath).forward(request, response);
        }

        return payUrl;
    }

    /**
     * 轮询池
     * 1.每个商户存在限制额度
     * 2.在某个时间不能同时进行多次的操作
     * 3.如果存在每个商户的额度到达限制后,该商户直接停用,即从集合中将该数据删除
     *
     * @param amount 交易金额
     * @return
     */
    private String getMerchantNo(String amount) {
        List<Merchant> list = new ArrayList<>();

        //往集合中添加数据
        Merchant merchant = new Merchant();
        //商户编号
        merchant.setMerchantNo("401500011562");
        //额度
        merchant.setQuota(50000);
        //时间
        merchant.setTime("5");
        //时间段
        merchant.setTimeSlot(String.valueOf(System.currentTimeMillis()));
        list.add(merchant);
        //如果商户的额度全部置为0,则没有商户可用,返回空回去
        if (list.size() == 0) {
            return null;
        }
        try {
            //程序暂停10秒钟
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //随机从数组中选取对应的商户
        Random rand = new Random();
        Merchant chant = list.get(rand.nextInt(list.size()));
        //判断该商户是否在指定时间段内存在多次调用
        //获取当前时间的毫秒数
        long time = System.currentTimeMillis();
        long merchatTimeLong = Long.parseLong(merchant.getTimeSlot());
        long timeDifference = time - merchatTimeLong;
        //判断商户的额度是否为0
        if (chant.getQuota() == 0) {
            //将该商户的数据移出
            list.remove(chant);
        } else {
            //时间差大于相隔时间的时候以及商户的额度大于输入的金额
            if (timeDifference > Long.parseLong(chant.getTime())) {
                if (chant.getQuota() > Integer.parseInt(amount)) {
                    //将该数据进行更新并删除原数据
                    list.remove(chant);
                    chant.setQuota(merchant.getQuota() - Integer.parseInt(amount));
                    list.add(chant);
                    return chant.getMerchantNo();
                }
            } else {
                //否则重新选取相应的商
                getMerchantNo(amount);
            }
        }
        return null;
    }


    public String getImgurl(HttpServletRequest request, ServletResponse response, PayCustomer customer) throws ServletException, IOException {
        return payment(request, response, customer);//保返回给我们的支付图片
    }

    /**
     * 其他客户支付交易请求报文
     * <p>
     * 入驻ID:YSM201908081719455501620025977
     * 商户ID：873190924119746279
     * 提供信息例如：
     * 商户号：401500011562   第三方支付公司的编号
     * <p>
     * <p>
     * md5的key(商户进件使用的密钥)：adc2fbfb4654ed95b28dfe0a0cb03da6
     * 加密的公钥信息publicKey(publicKey是商户进件使用的密钥)：MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDFf6krDWQUDUzKj+K+WvML2EyZLagKaJ5YTeCoBNhx/WpD1Vh2j/
     * wQ9G3RC/tpUcmE7szr/vgdEVHkOfk6mGpeHapS6QE4enJ/CVaTPM573uI8VGWBek9v/E6HaVxRV0Hs8ZsvHAKopqYNDZRKhIrlLUrrkFD2KXJgIiRPQALeMQIDAQAB
     *
     * @param request
     * @param response
     * @param customer
     * @returnf
     */
    public String getOtherImgurl(HttpServletRequest request, HttpServletResponse response, PayCustomer customer) throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");
        String msg = "处理成功";
//        String redirectPath = "result.jsp";
        Map<String, String> resultMap = null;
        String payType = customer.getPayType();
        String payUrl = "";
        try {
            // 利用treeMap对参数按key值进行排序
            Map<String, String> transMap = ResponseUtil.getParamMap(request);
            /**
             * 备注  Char（100）
             */
            String remark = "客户支付交易";

            /**
             * 用户IP  char(100)
             * 支持匹配用户的IP规则
             */
            String YUL3 = "4234242424";
            /**
             * 订单有效时间
             */
            Long valid = 3600L;

            /**
             * 交易币种 char(3)
             */
            String currency = "CNY";
            /**
             * 业务代码 char(5)
             */
            String bizType = "12";
            /**
             * 商品名称  char(100)
             */
            String goodsName = "商品名称";
            String goodsInfo = "873190924119746279";
            String goodsNum = "1";
            /**
             * 买家ID  char(100)  买家在商城的唯一编号
             */
            String buyerId = "324242424";

            /**
             * 轮询池,将客户端的随机选取商户,并在某一些时间内不能重复选取某个商户
             *
             */
            String merchantNo = "S20190927084578"; //商户编号
            String version = Config.getInstance().getVersion();
            String bindId = "YSM201908081719455501620025977";    //入驻ID
            String channelNo = Config.getInstance().getChannelNo();
            String notifyUrl = Config.getInstance().getNotifyUrl();
            String tranCode = "YS1003";
            String tranDate = DateUtil.getDate();
            String tranTime = DateUtil.getTime();
            String buyerName = "213213";
            String contact = "213131233";
            String cardType = "01";
            String ext1 = "324242424";
            String ext2 = "873190924119746279";
            String YUL1 = "1241242424";
            String YUL2 = "ANDROID";
            // 组织交易报文
            transMap.put("merchantNo", merchantNo);
            transMap.put("YUL1", YUL1);
            transMap.put("YUL2", YUL2);
            transMap.put("ext1", ext1);
            transMap.put("ext2", ext2);
            transMap.put("goodsNum", goodsNum);
            transMap.put("goodsInfo", goodsInfo);
            transMap.put("cardType", cardType);
            transMap.put("notifyUrl", notifyUrl);
            transMap.put("goodsName", goodsName);
            transMap.put("buyerId", buyerId);
            transMap.put("bindId", bindId);
            transMap.put("bizType", bizType);
            transMap.put("currency", currency);
            transMap.put("valid", valid.toString());
            transMap.put("YUL3", YUL3);
            transMap.put("remark", remark);
            transMap.put("version", version);
            transMap.put("channelNo", channelNo);
            transMap.put("tranCode", tranCode);
            transMap.put("tranFlow", customer.getTranFlow());
            transMap.put("tranDate", tranDate);
            transMap.put("tranTime", tranTime);
            transMap.put("payType", customer.getPayType());
            transMap.put("amount", customer.getAmount());
            // 敏感信息加密
            transMap.put("buyerName", CertUtil.getInstance().encrypt(buyerName));
            transMap.put("contact", CertUtil.getInstance().encrypt(contact));
            /**
             * 数字签名根据算法进行加密
             *
             */
            // 组织签名字符串
            String signMsg = ParamUtil.getSignMsg(transMap);
            // 签名
            String sign = CertUtil.getInstance().sign(signMsg);
//            String sign = MD5.putPairsSequenceAndTogether(transMap);
            // 将签名放入交易map中
            transMap.put("sign", sign);
            // 发送扫码请求报文
//            logger.info(TAG + "请求报文：" + transMap);
            String asynMsg = new Httpz().post(Config.getInstance().getPaygateReqUrl(), transMap);
//            logger.info(TAG + "返回报文：" + asynMsg);
            //公钥解密
//            byte[] bytes = RSAUtils.decryptByPublicKey(Base64.decode(publicKey),publicKey);

            // 解析返回
            resultMap = ResponseUtil.parseResponse(asynMsg);
//            logger.info("请求结果返回解析数据：" + resultMap);
            // 当支付类型payType为24或者25时，返回qrCodeURL的地址使用POST请求
            payUrl = resultMap.get("qrCodeURL");
            if ("24".equals(payType) || "25".equals(payType)) {
                if ("".equals(payUrl) || payUrl == null || "null".equals(payUrl)) {
                    if (resultMap.get("rtnMsg") != null) {
                        msg = resultMap.get("rtnMsg").toString();
                    }

                    request.setAttribute("resultMap", resultMap);
                } else {
                    request.setAttribute("action", payUrl);
//                    redirectPath = "webPayUrl.jsp";
                }
            } else {
                if (resultMap.get("rtnMsg") != null) {
                    msg = resultMap.get("rtnMsg").toString();
                }
                request.setAttribute("resultMap", resultMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
//            redirectPath = "error.jsp";
        } finally {
            request.setAttribute("errorMsg", msg);
//            request.getRequestDispatcher(redirectPath).forward(request, response);
        }

        return payUrl;
    }


    /**
     * 异步消息通知接口
     * 通知我们订单处理的结果是成功还是失败,其他的状态均视为交易进行中
     *
     * @param request
     * @param response
     * @return
     */
    public String orderPayment(HttpServletRequest request, HttpServletResponse response) {
        response.setCharacterEncoding("utf-8");
        try {
            TreeMap<String, String> transMap = new TreeMap<String, String>();
            Enumeration<String> enu = request.getParameterNames();
            String t = null;
            while (enu.hasMoreElements()) {
                t = enu.nextElement();
                transMap.put(t, request.getParameter(t));
            }
//            logger.info(TAG + "返回数据：" + transMap);
            String merchantNo = (String) transMap.get("merchantNo");
            // 获取签名
            String sign = (String) transMap.get("sign");
            sign = sign.replaceAll(" ", "+");
            transMap.remove("sign");
            // 验签
            String transData = ParamUtil.getSignMsg(transMap);
            boolean result = false;
            try {
                CertUtil.getInstance().verify(transData, sign);
                result = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (!result) {
                System.out.println("商户编号为:" + merchantNo + "验签失败");
//                logger.info(TAG + "商户编号为:" + merchantNo + "验签失败");
                throw new Exception("商户编号为:" + merchantNo + "验签失败");
            } else {
                //判断返回的码是否是成功的信息
                String rtnCode = request.getParameter("rtnCode");
                /**
                 * 判断商户是否存在
                 *
                 */
                if (rtnCode.equals("S")) { //成功
                    /**
                     * 调用代付的接口,向第三方发起请求
                     */
                    PayCustomer payCustomer = new PayCustomer();
                    payCustomer.setAmount(request.getParameter("amount"));
                    payCustomer.setTranFlow(request.getParameter("tranFlow"));
                    payCustomer.setPayType(request.getParameter("payType"));
                    payCustomer.setMerchantNo(request.getParameter("merchantNo"));
                    payCustomer.setCreateTime(LocalDateTime.now().toString());
                    payCustomer.setId(UUID.randomUUID().toString());
                    payCustomer.setStatus("交易成功");
                    payService.dfPay(request, response, payCustomer);
                }else{
                    /**
                     * 1.判断该商户在数据库中是否存在
                     * 2.如果存在将数据进行修改
                     * 3.如果不存在将该数据添加到数据库中,将数据状态修改为失败或者交易中
                     */

                }
            }
//            logger.info(TAG + "商户编号为:" + merchantNo + "验签成功");
        } catch (Exception e) {
            System.out.println("处理异常:" + e);
//            logger.info(TAG + "处理异常", e);
        }
        return null;
    }
}