package com.haidela.payment.util;

import com.haidela.payment.pay.configure.domain.MerchantConfigure;
import com.haidela.payment.pay.configure.service.MerchantConfigureService;
import com.haidela.payment.pay.individualcustomer.domain.IndividualCustomer;
import com.haidela.payment.pay.individualcustomer.service.IndividualCustomerService;
import com.haidela.payment.pay.paycustomer.domain.PayCustomer;
import com.haidela.payment.pay.paycustomer.service.PayCustomerService;
import com.haidela.payment.pay.payment.PaymentService;
import com.haidela.payment.pay.repaycustomer.PayService;
import com.haidela.payment.pay.repaycustomer.service.RepayCustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhanglize
 * @create 2019/10/18
 */
@Component
public class ScheduledTasks {

    private MerchantConfigureService configureService;
    private RepayCustomerService repayCustomerService;
    private PayCustomerService payCustomerService;
    private PaymentService paymentService;
    private IndividualCustomerService individualCustomerService;
    private PayService payService;
    private Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);
    @Autowired
    public void setIndividualCustomerService(IndividualCustomerService individualCustomerService) {
        this.individualCustomerService = individualCustomerService;
    }
    @Autowired
    public void setPayService(PayService payService) {
        this.payService = payService;
    }

    @Autowired
    public void setPaymentService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @Autowired
    public void setRepayCustomerService(RepayCustomerService repayCustomerService) {
        this.repayCustomerService = repayCustomerService;
    }

    @Autowired
    public void setPayCustomerService(PayCustomerService payCustomerService) {
        this.payCustomerService = payCustomerService;
    }

    @Autowired
    public void setConfigureService(MerchantConfigureService configureService) {
        this.configureService = configureService;
    }

    /**
     * 轮询池定时任务,每天晚上00:00对于轮询池中的状态,以及单日的交易金额总量进行清0
     */
    @Scheduled(cron = "0 0 0 * * ?")
//    @Scheduled(cron = "1 * * * * ?")
    public void pollTask() {
        logger.info("轮询池定时任务 :" + DateUtils.stringToDate());
        //查询所有状态为完成状态的1所对应的个体商户配置信息
        List<MerchantConfigure> configureList = configureService.findByStstus("1");
        configureList.forEach(merchantConfigure -> {
            merchantConfigure.setTotalOneAmount("0");
            merchantConfigure.setStatus("0");
            configureService.update(merchantConfigure.getId(), merchantConfigure.getTotalOneAmount(), merchantConfigure.getStatus());
        });
        //查询所有状态为未完成状态0所对应的个体工商户信息
        List<MerchantConfigure> list = configureService.findByStstus("0");
        list.forEach(merchantConfigure -> {
            merchantConfigure.setTotalOneAmount("0");
            merchantConfigure.setStatus("0");
            configureService.update(merchantConfigure.getId(), merchantConfigure.getTotalOneAmount(), merchantConfigure.getStatus());
        });
    }

    /**
     * 定时交易,客户交易信息,判断该条客户的信息是否在五分钟之内,如果超过五分钟没有收到下游客户信息
     * 将该笔订单的信息置为交易失败的状态
     */
//    @Scheduled(cron = "1 * * * * ?")
    @Scheduled(cron = "0 0/5 * * * ?")
    public void payCustomerTask() {
        logger.info("客户交易定时任务 :" + DateUtils.stringToDate());
        //查询所有订单消息的交易中的状态
        List<PayCustomer> customerList = payCustomerService.findByStatus("交易中");
        customerList.forEach(payCustomer -> {
            long minutes = 0L;
            try {
                //判断该笔订单的交易时间
                String createTime = payCustomer.getCreateTime().trim();
                String regex = "(.{2})";
                createTime = createTime.replaceAll(regex, "$1:");
                createTime = createTime.substring(0, createTime.length() - 4);
                //获取当前时间的HH：mm:ss
                String time = DateUtils.nowTime();
                DateFormat df = new SimpleDateFormat("HH:mm");
                Date d1 = df.parse(time);
                Date d2 = df.parse(createTime);
                long diff = d1.getTime() - d2.getTime();// 这样得到的差值是微秒级别
                minutes = diff / (1000 * 60);
            } catch (ParseException e) {
                logger.info("抱歉，时间日期解析出错");
            }
            if (minutes != 0L) {
                if (minutes > 5) {
                    //将该笔客户交易流水信息置为交易失败
                    payCustomerService.updateStatus(payCustomer.getTranFlow(), "交易失败");
                    //并发送消息给下游客户
                    String pathUrl = IpUtil.getCustomerUrl(payCustomer);
                    if (pathUrl != null || pathUrl.equals("")) {
                        int num = 0;
                        Map<String, String> map = new HashMap<>();
                        map.put("rtnCode", "0001");
                        map.put("tranFlow", payCustomer.getTranFlow());
                        map.put("rtnMsg", "交易失败");
                        map.put("amount", payCustomer.getAmount());
                        //发送消息给下游客户
                        paymentService.doPostOrGet(pathUrl, map, num, payCustomer.getTranFlow());
                    }
                }
            }
        });
    }



    /**
     * 代付失败后,轮询进行代付将代付失败的款项重新发起代付,默认每次代付100元
     */
    @Scheduled(cron = "1 * * * * ?")
    public void otherDfPayTask() {
        logger.info("轮询代付 :" + DateUtils.stringToDate());
        //商户进件信息
        List<IndividualCustomer> customerList = individualCustomerService.findAllMerchant();
        customerList.forEach(customer->{
            String merchantNo = customer.getMerchantNo();
            String tranFlow = String.valueOf(Timestamp.valueOf(LocalDateTime.now()).getTime());
            String amount = "10000";
            try {
                payService.otherDfPay(merchantNo,tranFlow,amount);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }

}
