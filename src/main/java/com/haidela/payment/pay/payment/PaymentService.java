package com.haidela.payment.pay.payment;


import com.haidela.payment.common.Config;
import com.haidela.payment.pay.configure.domain.MerchantConfigure;
import com.haidela.payment.pay.configure.service.MerchantConfigureService;
import com.haidela.payment.pay.paycustomer.PayService;
import com.haidela.payment.pay.paycustomer.domain.PayCustomer;
import com.haidela.payment.pay.paycustomer.service.PayCustomerService;
import com.haidela.payment.pay.repaycustomer.service.RepayCustomerService;
import com.haidela.payment.util.*;
import com.hfb.mer.sdk.secret.CertUtil;
import com.hfb.merchant.pay.util.DateUtil;
import com.hfb.merchant.pay.util.ParamUtil;
import com.hfb.merchant.pay.util.http.Httpz;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

/**
 * @author zhanglize
 * @create 2019/9/29
 */
@Service
public class PaymentService extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(PaymentService.class);
    private static final String TAG = "{统一支付}-";
    private static PayCustomerService customerService;
    private PayService payService;
    private MerchantConfigureService configureService;
    private RepayCustomerService repayCustomerService;


    @Autowired
    public void setRepayCustomerService(RepayCustomerService repayCustomerService) {
        this.repayCustomerService = repayCustomerService;
    }

    @Autowired
    public void setCustomerService(PayCustomerService customerService) {
        this.customerService = customerService;
    }

    @Autowired
    public void setPayService(PayService payService) {
        this.payService = payService;
    }

    @Autowired
    public void setConfigureService(MerchantConfigureService configureService) {
        this.configureService = configureService;
    }

    /**
     * 判断下游消息是否为空,如果为空,每隔15秒发送一次请求,
     * 发送40次请求消息,总共估计10分钟
     *
     * @param str      下游消息信息
     * @param pathUrl  请求地址信息
     * @param data     数据信息
     * @param num      次数
     * @param tranFlow 订单号信息
     */
    private static void sendMessage(String str, String pathUrl, Map<String, String> data, int num, String tranFlow) {
        if (("").equals(str) || str == null) {
            if (num != 20) {
                num += 1;
                try {
                    Thread.sleep(5000);
                    doPostOrGet(pathUrl, data, num, tranFlow);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                //默认设置为已经接收返回消息
                customerService.updateReceiveMessages(tranFlow, "1");
            }
        } else {
            //根据订单号信息将该订单的接收消息信息置为已经接收
            customerService.updateReceiveMessages(tranFlow, "1");
        }
    }

    /**
     * 以post方式调用对方接口方法
     *
     * @param pathUrl  请求地址信息
     * @param data     数据信息
     * @param num      数量
     * @param tranFlow 订单号信息
     */
    private static String doPostOrGet(String pathUrl, Map<String, String> data, int num, String tranFlow) {
        String str = HttpUtil2.doPost(pathUrl, data, "utf-8");
        sendMessage(str, pathUrl, data, num, tranFlow);
        return str;
    }

    /**
     * 客户支付交易请求报文(落雨轩)
     *
     * @param request
     * @param response
     * @param customer 客户消息信息
     */
    public Map<String, String> payment(HttpServletRequest request, ServletResponse response, PayCustomer customer) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String msg = "处理成功";
//        String redirectPath = "result.jsp";
        Map<String, String> resultMap = null;
        Map<String, String> map = new HashMap<>();
        String payUrl = "";
        // 利用treeMap对参数按key值进行排序
        TreeMap<String, String> transMap = ResponseUtil.getParamMap(request);
        try {
            //根据订单流水号判断该流水号是否存在
            if (customerService.findByTranFlow(customer.getTranFlow()) != null) {
                map.put("code", "5006");
                map.put("msg", "订单流水号已经存在");
                map.put("merchantId", "");
                return map;
            }
            String payType = transMap.get("payType");
            String remark = "客户支付交易"; //备注  Char（100）
            String YUL3 = IpUtil.getRandomIp(); //用户IP  char(100) 支持匹配用户的IP规则
            Long valid = 3600L; //订单有效时间
            String currency = "CNY"; //交易币种 char(3)
            String bizType = "16"; //业务代码 char(5)
            String goodsName = "商品名称";  //商品名称  char(100)
            /**
             * 轮询池,将客户端的随机选取商户,并在某一些时间内不能重复选取某个商户
             * 个体工商户id(我们自己的)
             */
            Map<String, String> goodsInfo = getMerchantNo(request.getParameter("amount"), request.getParameter("compID"), payType);
            if (goodsInfo.get("merchantId") == null || goodsInfo.get("merchantId").equals("")) {
                return goodsInfo;
            }
            //String goodsNum = "1";
            String merchantNo = "S20190927084578"; //商户编号
            String version = Config.getInstance().getVersion();
            String bindId = "YSM201909271637141884536731670";    //入驻ID
            String channelNo = Config.getInstance().getChannelNo();
            /**
             * 异步消息通知的请求地址信息
             */
            String notifyUrl = "http://182.92.192.208:8080/order-payment";
            String tranCode = "YS1003";
            String tranDate = DateUtil.getDate();
            String tranTime = DateUtil.getTime();
            String buyerName = "213213";
            String contact = "213131233";
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
            String merchantId = goodsInfo.get("merchantId");
            transMap.put("goodsInfo", merchantId);
            //根据商户id修改商户配置对象中该商户调用的时间信息
            configureService.updateMerchantId(merchantId);
            map.put("merchantId", goodsInfo.get("merchantId"));
            transMap.put("notifyUrl", notifyUrl);
            transMap.put("goodsName", goodsName);
            transMap.put("buyerId", customer.getBuyerId());
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
            transMap.put("compID", customer.getCompID());
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
            // 将签名放入交易map中
            transMap.put("sign", sign);
            //将该订单号的信息存入到我们的数据库中
            addPayCustomer(transMap);
            // 发送扫码请求报文
            logger.info(TAG+"发送请求报文给上游：" + transMap);
            String asynMsg = new Httpz().post(Config.getInstance().getPaygateReqUrl(), transMap);
            logger.info(TAG+"上游返回报文信息：" + asynMsg);
            // 解析返回
            resultMap = ResponseUtil.parseResponse(asynMsg);
            logger.info(TAG+"请求结果返回解析数据：" + resultMap);
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
                }
            } else {
                if (resultMap.get("rtnMsg") != null) {
                    msg = resultMap.get("rtnMsg").toString();
                }
                request.setAttribute("resultMap", resultMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            request.setAttribute("errorMsg", msg);
        }
        logger.info("商户编号：" + map.get("merchantId"));
        map.put("payUrl", payUrl);
        return map;
    }

    /**
     * 将该订单号的信息存入到我们的数据库中
     *
     * @param transMap
     */
    private int addPayCustomer(Map<String, String> transMap) {
        PayCustomer payCustomer = new PayCustomer();
        payCustomer.setMerchantId(transMap.get("merchantNo"));
        payCustomer.setMerchantNo(transMap.get("goodsInfo"));
        payCustomer.setAmount(transMap.get("amount"));
        payCustomer.setBuyerId(transMap.get("buyerId"));
        payCustomer.setCompID(transMap.get("compID"));
        if (transMap.get("companyName") != null) {
            payCustomer.setCompanyName(transMap.get("companyName"));
        }
        payCustomer.setTranFlow(transMap.get("tranFlow"));
        payCustomer.setStatus("交易中");
        payCustomer.setPayType(transMap.get("payType"));
        payCustomer.setModifyTime(DateUtils.stringToDate());
        payCustomer.setCreateTime(transMap.get("tranTime"));
        payCustomer.setCreateDate(transMap.get("tranDate"));
        return customerService.add(payCustomer);
    }

    /**
     * 轮询池
     * 1.每个商户存在限制额度
     * 2.在某个时间不能同时进行多次的操作
     * 3.如果存在每个商户的额度到达限制后,该商户直接停用,即从集合中将该数据删除
     *
     * @param payType 支付类型
     * @param amount  交易金额
     * @param compID  公司id
     * @return
     */
    public Map<String, String> getMerchantNo(String amount, String compID, String payType) {
        //获取到所有状态为0的个体商户配置信息
        List<MerchantConfigure> configureList = configureService.findByStstusCompId("0", compID, payType);
        Map<String, String> result = new HashMap<>();
        if (configureList.size() == 0) {
            result.put("code", "5001");
            result.put("msg", "支付失败,请使用其他支付方式");
            result.put("merchantId", "");
            return result;
        }
        //随机选取一个商户号的信息
        MerchantConfigure configure = getRandom(configureList);
        //判断现在是否在支付时间
        if (judgeTime(configure) != true) {
            result.put("code", "5002");
            result.put("msg", "支付失败,已过支付时间");
            result.put("merchantId", "");
            return result;
        }
        //判断该商户的支付类型今日是否已经达到上限
        if (judgeLimit(configure) != true) {
            result.put("code", "5001");
            result.put("msg", "支付失败,请使用其他支付类型");
            result.put("merchantId", "");
            return result;
        }
        /**
         * 判断该商户在某段时间内是否被重复调用
         */
        //获取商户的调用时间
        //获取当前时间的毫秒数
        long nowTime = System.currentTimeMillis();
        //获取调用时间
        long callTime = Timestamp.valueOf(configure.getCallTime()).getTime();
        long timeDifference = nowTime - callTime;
        //判断时间差是否大于相隔的时间以及商户的额度是否大于输入的金额
        if (timeDifference > Long.parseLong(configure.getTimeDifference())) {
            //获取商户的的总金额以及商户的单日总额
            if (Integer.parseInt(configure.getAmountLimit()) - Integer.parseInt(configure.getTotalOneAmount()) > Integer.parseInt(amount)) {
                /**
                 * 根据商户id判断该商户在最新3三笔交易中是否存在连续交易失败,
                 * 如果存在该商户置为状态为1,表示今日不可使用,重新选取商户
                 */
                List<PayCustomer> customerList = customerService.findByThree(configure.getMerchantId());
                int num = (int) customerList.stream().filter(payCustomer -> payCustomer.getStatus().equals("交易中")).count();
                if (num == customerList.size()) {
                    //将该商户置为1
                    configureService.updateMerchantIdPayType(configure.getMerchantId(), payType, "1");
                    //重新选取对应的商户
                    getMerchantNo(amount, compID, payType);
                }
                //根据商户id修改该条商户的调用时间
                configureService.updateMerchantId(configure.getMerchantId());
                result.put("code", "5000");
                result.put("msg", "5000");
                result.put("merchantId", configure.getMerchantId());
                return result;
            } else {
                //否则重新选取相应的商户
                getMerchantNo(amount, compID, payType);
            }
            configure.getAmountLimit();
            configure.getTotalOneAmount();
        } else {
            //否则重新选取相应的商户
            getMerchantNo(amount, compID, payType);
        }
        return null;
    }

    /**
     * 判断该商户的支付类型今日是否已经达到上限
     *
     * @param configure
     * @return
     */
    private boolean judgeLimit(MerchantConfigure configure) {
        if (configure.getTotalOneAmount().equals(configure.getAmountLimit())) {
            //根据id修改该条数据为1,表示今日已完成
            configureService.updateStatus(configure.getId(), "1");
            return false;
        }
        return true;
    }

    /**
     * 判断现在是否在支付时间
     *
     * @param configure
     */
    private boolean judgeTime(MerchantConfigure configure) {
        String time = DateUtils.nowTime();
        //判断当前时间是否在支付时间范围内
        if (time.compareTo(configure.getStartTime()) > 0 && time.compareTo(configure.getEndTime()) < 0) {
            return true;
        }
        return false;
    }

    /**
     * 随机选取一个商户号
     *
     * @param configureList
     * @return
     */
    private MerchantConfigure getRandom(List<MerchantConfigure> configureList) {
        Random rand = new Random();
        MerchantConfigure configure = configureList.get(rand.nextInt(configureList.size()));
        return configure;
    }

    public Map<String, String> getImgurl(HttpServletRequest request, ServletResponse response, PayCustomer customer) throws ServletException, IOException {
        return payment(request, response, customer);//保返回给我们的支付图片
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
        //获取到下游客户的请求地址信息
        String customerUrl = getCustomerUrl(request);
        response.setCharacterEncoding("utf-8");
        TreeMap<String, String> transMap = new TreeMap<String, String>();
        String transData = null;
        try {
            Enumeration<String> enu = request.getParameterNames();
            String t;
            while (enu.hasMoreElements()) {
                t = enu.nextElement();
                transMap.put(t, request.getParameter(t));
            }
            logger.info("异步消息通知返回数据：" + transMap);
            String merchantNo = (String) transMap.get("merchantNo");
            // 获取签名
            String sign = (String) transMap.get("sign");
            sign = sign.replaceAll(" ", "+");
            transMap.remove("sign");
            // 验签
            transData = ParamUtil.getSignMsg(transMap);
            boolean result = true;
            try {
                CertUtil.getInstance().verify(transData, sign);
                result = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (!result) {
                logger.info("异步消息商户编号为:" + merchantNo + "验签失败");
                throw new Exception("异步消息商户编号为:" + merchantNo + "验签失败");
            } else {
                //判断返回的码是否是成功的信息
                String rtnCode = request.getParameter("rtnCode");
                Map<String, String> map = new HashMap<>();
                map.put("rtnCode", rtnCode);
                map.put("tranFlow", request.getParameter("tranFlow"));
                map.put("rtnMsg", request.getParameter("rtnMsg"));
                map.put("amount", request.getParameter("amount"));
                String str = MD5.getSignContent(map, "", "");
                //将数据进行拼接
                String secret = "9989639630683" + str;
                String digest = "";
                //首先将数据进行sha1算法
                try {
                    String security = MD5.md5(SecuritySHA1Utils.shaEncode(secret));
                    //将流水号进行md5加密处理
                    digest = MD5.md5(security.trim() + MD5.md5(request.getParameter("tranFlow")).trim());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                map.put("digest", digest);
                logger.info(digest + "发送给下游客户的地址栏数据信息:" + map.toString());
                if (("0000").equals(rtnCode)) { //成功
                    //根据客户流水单号信息,修改该笔交易的状态为完成交易完成的状态
                    String status = "交易完成";
                    customerService.updateStatus(request.getParameter("tranFlow"), status);
                    //根据订单号获取到客户交易流水信息
                    PayCustomer payCustomer = customerService.findByTranFlow(request.getParameter("tranFlow"));
                    //TODO 将该订单的平台流水号存入到数据库中
                    payCustomer.setPaySerialNo(request.getParameter("paySerialNo"));
                    logger.info("平台流水号:" + payCustomer.getMerchantNo());
                    //TODO 根据id修改该条商户信息的订单信息
                    //根据id修改该条商户信息的订单信息
                    customerService.updateByPaySerialNo(payCustomer.getId(), payCustomer.getPaySerialNo());
                    //根据商户号以及商户类型获取商户的配置对象
                    MerchantConfigure configure = configureService.findByMerchantNo(payCustomer.getMerchantNo(), payCustomer.getPayType());
                    //修改商户配置信息的当日收款总额
                    configure.setTotalOneAmount(String.valueOf(Integer.parseInt(configure.getTotalOneAmount()) + Integer.parseInt(payCustomer.getAmount())));
                    configureService.update(configure.getId(), configure.getTotalOneAmount(), "0");
                    //TODO 根据请求地址向我们的下游客户发送报文请求信息,交易完成的信息
                    if (customerUrl != null || !customerUrl.equals("")) {
                        int num = 0;
                        doPostOrGet(customerUrl, map, num, request.getParameter("tranFlow"));
                    }
                    /**
                     * 调用代付的接口,向第三方发起请求
                     */
                    logger.info("调用代付的接口,向第三方发起请求:==================================");
                    payService.dfPay(request, response, payCustomer);
                } else {
                    //根据客户流水单号信息,修改该笔交易的状态为完成交易完成的状态
                    String status = "交易失败";
                    customerService.updateStatus(request.getParameter("tranFlow"), status);
                    //TODO 根据请求地址向我们的下游客户发送报文请求信息,交易失败的信息
                    if (customerUrl != null || !customerUrl.equals("")) {
                        int num = 0;
                        doPostOrGet(customerUrl, map, num, request.getParameter("tranFlow"));
                    }
                }
            }
            logger.info("商户编号为:" + merchantNo + "验签成功");
        } catch (Exception e) {
            System.out.println("处理异常:" + e);
            logger.info("处理异常", e);
        }
        System.out.println(transData);
        return transData;
    }

    /**
     * 获取到下游客户的请求地址信息
     *
     * @param request
     * @return
     */
    private String getCustomerUrl(HttpServletRequest request) {
        String customerUrl = "";
        //根据交易流水号判断公司的id
        PayCustomer customer = customerService.findByTranFlow(request.getParameter("tranFlow"));
        if (customer.getCompID().equals("2789")) {
//          生产环境  http://payment.ilachang.com/paymentSystem/forthAPI/callback/hyPay
            customerUrl = "http://payment.ilachang.com/paymentSystem/forthAPI/callback/hyPay";
//            customerUrl = "http://61.222.80.172:8787/paymentSystem/forthAPI/callback/hyPay";
        } else if (customer.getCompID().equals("4189")) {
            customerUrl = "http://gate.dfzf6666.com/PlugOrderCallbackNotify21.ashx";
        }
        return customerUrl;
    }

    /**
     * 客户支付交易测试接口
     *
     * @return
     */
    public Map<String, String> getOtherImgurl(HttpServletRequest request, HttpServletResponse response, PayCustomer customer) throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");
        String msg = "处理成功";
        Map<String, String> resultMap = null;
        Map<String, String> map = new HashMap<>();
        String payUrl = "";
        // 利用treeMap对参数按key值进行排序
        TreeMap<String, String> transMap = ResponseUtil.getParamMap(request);
        try {
            //根据订单流水号判断该流水号是否存在
            if (customerService.findByTranFlow(customer.getTranFlow()) != null) {
                map.put("code", "5006");
                map.put("msg", "订单流水号已经存在");
                map.put("merchantId", "");
                return map;
            }
            String payType = transMap.get("payType");
            String remark = "客户支付交易"; //备注  Char（100）
            String YUL3 = IpUtil.getRandomIp(); //用户IP  char(100) 支持匹配用户的IP规则
            Long valid = 3600L; //订单有效时间
            String currency = "CNY"; //交易币种 char(3)
            String bizType = "16"; //业务代码 char(5)
            String goodsName = "商品名称";  //商品名称  char(100)
            String goodsInfo = customer.getMerchantId();
            String merchantNo = "S20190927084578"; //商户编号
            String version = Config.getInstance().getVersion();
            String bindId = "YSM201909271637141884536731670";    //入驻ID
            String channelNo = Config.getInstance().getChannelNo();
            String notifyUrl = "http://182.92.192.208:8080/other-order-payment";
            String tranCode = "YS1003";
            String tranDate = DateUtil.getDate();
            String tranTime = DateUtil.getTime();
            String buyerName = "213213";
            String contact = "213131233";
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
            transMap.put("goodsInfo", goodsInfo);
            //根据商户id修改商户配置对象中该商户调用的时间信息
            configureService.updateMerchantId(goodsInfo);
            map.put("merchantId", goodsInfo);
            transMap.put("notifyUrl", notifyUrl);
            transMap.put("goodsName", goodsName);
            transMap.put("buyerId", customer.getBuyerId());
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
            transMap.put("compID", customer.getCompID());
            // 敏感信息加密
            transMap.put("buyerName", CertUtil.getInstance().encrypt(buyerName));
            transMap.put("contact", CertUtil.getInstance().encrypt(contact));
            // 组织签名字符串
            String signMsg = ParamUtil.getSignMsg(transMap);
            // 签名
            String sign = CertUtil.getInstance().sign(signMsg);
            // 将签名放入交易map中
            transMap.put("sign", sign);
            //将该订单号的信息存入到我们的数据库中
            addPayCustomer(transMap);
            // 发送扫码请求报文
            logger.info(TAG+"发送请求报文给上游：" + transMap);
            String asynMsg = new Httpz().post(Config.getInstance().getPaygateReqUrl(), transMap);
            logger.info(TAG+"返回报文：" + asynMsg);
            // 解析返回
            resultMap = ResponseUtil.parseResponse(asynMsg);
            logger.info(TAG+"请求结果返回解析数据：" + resultMap);
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
                }
            } else {
                if (resultMap.get("rtnMsg") != null) {
                    msg = resultMap.get("rtnMsg").toString();
                }
                request.setAttribute("resultMap", resultMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            request.setAttribute("errorMsg", msg);
        }
        logger.info("商户编号：" + map.get("merchantId"));
        map.put("payUrl", payUrl);
        return map;
    }

    /**
     * 测试异步消息通知接口
     * 通知我们订单处理的结果是成功还是失败,其他的状态均视为交易进行中
     *
     * @return
     */
    public String otherOrderPayment(HttpServletRequest request, HttpServletResponse response) {
        logger.info("测试异步消息通知接口:==================================");
        //获取到下游客户的请求地址信息
        String customerUrl = getCustomerUrl(request);
        response.setCharacterEncoding("utf-8");
        TreeMap<String, String> transMap = new TreeMap<String, String>();
        String transData = null;
        try {
            Enumeration<String> enu = request.getParameterNames();
            String t;
            while (enu.hasMoreElements()) {
                t = enu.nextElement();
                transMap.put(t, request.getParameter(t));
            }
            logger.info("返回数据：" + transMap);
            String merchantNo = (String) transMap.get("merchantNo");
            // 获取签名
            String sign = (String) transMap.get("sign");
            sign = sign.replaceAll(" ", "+");
            transMap.remove("sign");
            // 验签
            transData = ParamUtil.getSignMsg(transMap);
            boolean result = true;
            try {
                CertUtil.getInstance().verify(transData, sign);
                result = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (!result) {
                logger.info("商户编号为:" + merchantNo + "验签失败");
                throw new Exception("商户编号为:" + merchantNo + "验签失败");
            } else {
                //判断返回的码是否是成功的信息
                String rtnCode = request.getParameter("rtnCode");
                Map<String, String> map = new HashMap<>();
                map.put("rtnCode", rtnCode);
                map.put("tranFlow", request.getParameter("tranFlow"));
                map.put("rtnMsg", request.getParameter("rtnMsg"));
                map.put("amount", request.getParameter("amount"));
                String str = MD5.getSignContent(map, "", "");
                //将数据进行拼接
                String secret = "9989639630683" + str;
                String digest = "";
                //首先将数据进行sha1算法
                try {
                    String security = MD5.md5(SecuritySHA1Utils.shaEncode(secret));
                    //将流水号进行md5加密处理
                    digest = MD5.md5(security.trim() + MD5.md5(request.getParameter("tranFlow")).trim());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                map.put("digest", digest);
                logger.info(digest + "测试下游客户请求地址信息:" + map.toString());
                if (("0000").equals(rtnCode)) { //成功
                    //根据客户流水单号信息,修改该笔交易的状态为完成交易完成的状态
                    String status = "交易完成";
                    customerService.updateStatus(request.getParameter("tranFlow"), status);
                    //根据订单号获取到客户交易流水信息
                    PayCustomer payCustomer = customerService.findByTranFlow(request.getParameter("tranFlow"));
                    //TODO 将该订单的平台流水号存入到数据库中
                    payCustomer.setPaySerialNo(request.getParameter("paySerialNo"));
                    logger.info("平台流水号:" + payCustomer.getPaySerialNo());
                    //TODO 根据id修改该条商户信息的订单信息
                    //根据id修改该条商户信息的订单信息
                    customerService.updateByPaySerialNo(payCustomer.getId(), payCustomer.getPaySerialNo());
                    //根据商户号以及商户类型获取商户的配置对象
                    MerchantConfigure configure = configureService.findByMerchantNo(payCustomer.getMerchantNo(), payCustomer.getPayType());
                    //修改商户配置信息的当日收款总额
                    configure.setTotalOneAmount(String.valueOf(Integer.parseInt(configure.getTotalOneAmount()) + Integer.parseInt(payCustomer.getAmount())));
                    configureService.update(configure.getId(), configure.getTotalOneAmount(), "0");
                    //TODO 根据请求地址向我们的下游客户发送报文请求信息,交易完成的信息
//                    if (customerUrl != null || !customerUrl.equals("")) {
//                        int num = 0;
//                        doPostOrGet(customerUrl, map, num, request.getParameter("tranFlow"));
//                    }
                    /**
                     * 调用代付的接口,向第三方发起请求
                     */
                    logger.info("调用代付的接口,向第三方发起请求:==================================");
                    payService.dfPay(request, response, payCustomer);
                } else {
                    //根据客户流水单号信息,修改该笔交易的状态为完成交易完成的状态
                    String status = "交易失败";
                    customerService.updateStatus(request.getParameter("tranFlow"), status);
                    //TODO 根据请求地址向我们的下游客户发送报文请求信息,交易失败的信息
                    if (customerUrl != null || !customerUrl.equals("")) {
                        int num = 0;
                        doPostOrGet(customerUrl, map, num, request.getParameter("tranFlow"));
                    }
                }
            }
            logger.info("商户编号为:" + merchantNo + "验签成功");
        } catch (Exception e) {
            System.out.println("处理异常:" + e);
            logger.info("处理异常", e);
        }
        System.out.println(transData);
        return transData;
    }

    /**
     * 代付交易通知地址
     *
     * @return
     */
    public Map<String, String> orderRepay(HttpServletRequest request, HttpServletResponse response) {
        TreeMap<String, String> transMap = new TreeMap<String, String>();
        Enumeration<String> enu = request.getParameterNames();
        String t;
        while (enu.hasMoreElements()) {
            t = enu.nextElement();
            transMap.put(t, request.getParameter(t));
        }
        logger.info("代付交易通知地址:" + transMap.toString());
        repayCustomerService.updateByStatus(transMap.get("tranFlow"), transMap.get("rtnCode"));
        return transMap;
    }
}