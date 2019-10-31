package com.haidela.payment.pay.repaycustomer.service;

import com.haidela.payment.pay.configure.domain.MerchantConfigure;
import com.haidela.payment.pay.configure.service.MerchantConfigureService;
import com.haidela.payment.pay.individualcustomer.domain.IndividualCustomer;
import com.haidela.payment.pay.individualcustomer.service.IndividualCustomerService;
import com.haidela.payment.pay.repaycustomer.domain.RepayCustomer;
import com.haidela.payment.pay.repaycustomer.mapper.RepayCustomerMapper;
import com.haidela.payment.util.DateUtils;
import com.haidela.payment.util.HttpUtil2;
import com.haidela.payment.util.SnowflakeIdUtils;
import com.hfb.merchant.pay.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhanglize
 * @create 2019/10/15
 */
@Service
public class RepayCustomerService {

    private String TRAN_FLOW = "DF_";

    private RepayCustomerMapper mapper;
    private MerchantConfigureService configureService;
    private IndividualCustomerService individualCustomerService;

    @Autowired
    public void setMapper(RepayCustomerMapper mapper) {
        this.mapper = mapper;
    }

    @Autowired
    public void setConfigureService(MerchantConfigureService configureService) {
        this.configureService = configureService;
    }
    @Autowired
    public void setIndividualCustomerService(IndividualCustomerService individualCustomerService) {
        this.individualCustomerService = individualCustomerService;
    }

    /**
     * 新增代付消息接收情况
     *
     * @param customer
     * @return
     */
    public int add(RepayCustomer customer) {
        customer.setModifyTime(DateUtils.stringToDate());
        customer.setCreateTime(DateUtils.stringToDate());
        customer.setRepayTranFlow(TRAN_FLOW + customer.getTranFlow());
        customer.setCreateDate( DateUtil.getDate());
        customer.setId(new SnowflakeIdUtils().nextId());
        return mapper.add(customer);
    }

    public void localRepayPost(RepayCustomer repayCustomer) {
        String url = "http://localhost:8080/repay-customer/repay-add";
        Map<String,String> stringMap = new HashMap<>();
        stringMap.put("customer",repayCustomer.toString());
        HttpUtil2.doPost(url,stringMap,"utf-8");
    }

    /**
     * 根据交易流水号查询对应的代付交易对象的信息
     *
     * @param tranFlow 交易流水号
     * @return
     */
    public RepayCustomer findByTranFlow(String tranFlow) {
        return mapper.findByTranFlow(tranFlow);
    }

    /**
     * 根据交易流水号修改代付交易的状态
     *
     * @param tranFlow 交易流水号
     * @param status   交易状态
     * @return
     */
    public int updateByStatus(String tranFlow, String status) {
        return mapper.updateByStatus(tranFlow, status);
    }

    /**
     * 查询各个账户的代付成功情况信息
     *
     * @return
     */
    public Map<String, String> findRepayCustomer() {
        List<MerchantConfigure> configureList = configureService.finAllCustomer();
        Map<String, String> map = new HashMap<>();
        configureList.forEach(configure -> {
            List<RepayCustomer> customerList = findByMerchantNo(configure.getMerchantId());
            Integer amount = 0;
            if (customerList.size() != 0) {
                for (int i = 0; i < customerList.size(); i++) {
                    if (customerList.get(i).getStatus().equals("0000")) {
                        amount += Integer.parseInt(customerList.get(i).getAmount());
                    }
                }
            }
            IndividualCustomer customer = individualCustomerService.findMerchantNo(configure.getMerchantId());
            map.put(configure.getMerchantId() + "-" + "0000" + "-" + customer.getAccountName(),amount.toString());
        });
        Integer total = 0;
        for (Map.Entry<String,String> entry : map.entrySet()) {
            System.out.println("key = " + entry.getKey() + ", value = " + entry.getValue());
            total += Integer.parseInt(entry.getValue());
        }
        map.put("total",total.toString());
        return map;
    }
    /**
     * 查询代付中各个账户代付失败的信息
     *
     * @return
     */
    public Map<String,String> findFailRepayCustomer() {
        List<MerchantConfigure> configureList = configureService.finAllCustomer();
        Map<String, String> map = new HashMap<>();
        configureList.forEach(configure -> {
            List<RepayCustomer> customerList = findByMerchantNo(configure.getMerchantId());
            Integer amount = 0;
            if (customerList.size() != 0) {
                for (int i = 0; i < customerList.size(); i++) {
                    if (!customerList.get(i).getStatus().equals("0000")) {
                        amount += Integer.parseInt(customerList.get(i).getAmount());
                    }
                }
            }
            IndividualCustomer customer = individualCustomerService.findMerchantNo(configure.getMerchantId());
            map.put(configure.getMerchantId() + "-" + "0001" + "-" + customer.getAccountName(),amount.toString());
        });
        Integer total = 0;
        for (Map.Entry<String,String> entry : map.entrySet()) {
            System.out.println("key = " + entry.getKey() + ", value = " + entry.getValue());
            total += Integer.parseInt(entry.getValue());
        }
        map.put("total",total.toString());
        return map;
    }

    /**
     * 根据商户号查询对应的代付信息
     *
     * @param merchantNo
     * @return
     */
    public List<RepayCustomer> findByMerchantNo(String merchantNo) {
        return mapper.findByMerchantNo(merchantNo);
    }

    /**
     * 查询所有的代付账户信息情况
     *
     * @return
     */
    public List<RepayCustomer> findByAll() {
        return mapper.findByAll();
    }

    /**
     * 根据日期(年月日)查询到当天的代付记录
     *
     * @param date
     * @return
     */
    public List<RepayCustomer> findByTodayDate(String date) {
        return mapper.findByTodayDate(date);
    }

    /**
     * 获取当天的代付情况以及这段时间失败的代付金额信息
     *
     * @return
     */
    public Map<String, String> getRepayAmount() {
        Map<String,String> map = new HashMap<>();
        //根据日期查询当天的入账信息并计算总额
        List<RepayCustomer> repayCustomerList = findByTodayDate(DateUtil.getDate());
        Integer sucessAmount = 0;
        Integer failAmount = 0;
        for (int i = 0; i < repayCustomerList.size(); i++) {
            if (repayCustomerList.get(i).getStatus().equals("0000")) {
                sucessAmount += Integer.parseInt(repayCustomerList.get(i).getAmount());
            }else{
                failAmount += Integer.parseInt(repayCustomerList.get(i).getAmount());
            }
        }
        //单位为分,将分单位转换为元
        String sucessRmb = sucessAmount / 100 + "." + sucessAmount % 100 / 10 + sucessAmount % 100 % 10;
        String failRmb = failAmount / 100 + "." + failAmount % 100 / 10 + failAmount % 100 % 10;
        map.put("sucessRmb",sucessRmb);
        map.put("failRmb",failRmb);
        return map;
    }
}
