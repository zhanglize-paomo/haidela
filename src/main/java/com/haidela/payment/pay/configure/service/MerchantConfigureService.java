package com.haidela.payment.pay.configure.service;

import com.haidela.payment.pay.configure.domain.MerchantConfigure;
import com.haidela.payment.pay.configure.mapper.MerchantConfigureMapper;
import com.haidela.payment.pay.individualcustomer.domain.IndividualCustomer;
import com.haidela.payment.pay.individualcustomer.service.IndividualCustomerService;
import com.haidela.payment.pay.paycustomer.domain.PayCustomer;
import com.haidela.payment.pay.paycustomer.service.PayCustomerService;
import com.haidela.payment.util.DateUtils;
import com.haidela.payment.util.SnowflakeIdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 个体商户配置
 *
 * @author zhanglize
 * @create 2019/10/10
 */
@Service
public class MerchantConfigureService {

    private PayCustomerService payCustomerService;
    private IndividualCustomerService individualCustomerService;
    @Autowired
    private MerchantConfigureMapper mapper;

    @Autowired
    public void setPayCustomerService(PayCustomerService payCustomerService) {
        this.payCustomerService = payCustomerService;
    }

    @Autowired
    public void setIndividualCustomerService(IndividualCustomerService individualCustomerService) {
        this.individualCustomerService = individualCustomerService;
    }

    /**
     * 根据id修改个体工商户配置的基本信息
     *
     * @param id             主键
     * @param totalOneAmount 单日总额
     * @param status         状态
     * @return
     */
    public int update(Long id, String totalOneAmount, String status) {
        return mapper.update(id, totalOneAmount, status);
    }

    /**
     * 根据状态查询所需要的个体工商户的信息
     *
     * @param status 状态
     * @return
     */
    public List<MerchantConfigure> findByStstus(String status) {
        return mapper.findByStstus(status);
    }

    /**
     * 查询公司下所有可用的商户id信息
     *
     * @param payType 支付类型
     * @param status  状态
     * @param compID  公司ID
     * @return
     */
    public List<MerchantConfigure> findByStstusCompId(String status, String compID, String payType) {
        return mapper.findByStstusCompId(status, compID, payType);
    }

    /**
     * 根据商户id修改该条商户的调用时间
     *
     * @param merchantId
     * @return
     */
    public int updateMerchantId(String merchantId) {
        return mapper.updateMerchantId(merchantId);
    }


    /**
     * 根据id修改该条商户信息
     *
     * @param id     ID
     * @param status 状态
     * @return
     */
    public int updateStatus(Long id, String status) {
        return mapper.updateStatus(id, status);
    }

    /**
     * 根据商户编号以及支付类型获取到对应的配置对象信息
     *
     * @param merchantNo
     * @param payType
     * @return
     */
    public MerchantConfigure findByMerchantNo(String merchantNo, String payType) {
        return mapper.findByMerchantNo(merchantNo, payType);
    }

    /**
     * 根据该商户的id修改该商户的支付类型的状态
     *
     * @param merchantNo
     * @param payType
     * @param status
     * @return
     */
    public int updateMerchantIdPayType(String merchantNo, String payType, String status) {
        return mapper.updateMerchantIdPayType(merchantNo, payType, status);
    }

    /**
     * 查询每个商户对应的金额信息
     *
     * @return
     */
    public Map<String, String> findByCustomer() {
        Map<String, String> map = new HashMap<>();
        //查询所有的商户信息
        List<MerchantConfigure> configureList = finAllCustomer();
        //获取到每个商户交易成功的数据信息
        configureList.forEach(configure -> {
            List<PayCustomer> payCustomerList = payCustomerService.findByMerchantNo(configure.getMerchantId());
            Integer amount = 0;
            for (int i = 0; i < payCustomerList.size(); i++) {
                if (payCustomerList.get(i).getStatus().equals("交易完成")) {
                    amount += Integer.parseInt(payCustomerList.get(i).getAmount());
                }
            }
            IndividualCustomer customer = individualCustomerService.findMerchantNo(configure.getMerchantId());
            map.put(configure.getMerchantId() + ":" + customer.getAccountName(), amount.toString());
        });
        Integer total = 0;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println("key = " + entry.getKey() + ", value = " + entry.getValue());
            total += Integer.parseInt(entry.getValue());
        }
        map.put("total", total.toString());
        return map;
    }

    /**
     * 查询所有的商户信息
     *
     * @return
     */
    public List<MerchantConfigure> finAllCustomer() {
        return mapper.finAllCustomer();
    }

    /**
     * 根据id查询出对应的配置信息
     *
     * @param id
     * @return
     */
    public MerchantConfigure findById(Long id) {
        return mapper.findById(id);
    }

    /**
     * 新增个体商户配置信息
     *
     * @param configure
     * @return
     */
    public int insert(MerchantConfigure configure) {
        configure.setId(new SnowflakeIdUtils().nextId());
        configure.setTotalOneAmount("0");
        configure.setCallTime(DateUtils.stringToDate());
        configure.setCreateTime(DateUtils.stringToDate());
        configure.setModifyTime(DateUtils.stringToDate());
        return mapper.insert(configure);
    }

    /**
     * 根据id删除对应的数据信息
     *
     * @param id
     * @return
     */
    public int toDelete(Long id) {
        return mapper.toDelete(id);
    }
}
