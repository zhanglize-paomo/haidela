package com.haidela.payment.pay.configure.service;

import com.haidela.payment.pay.configure.domain.MerchantConfigure;
import com.haidela.payment.pay.configure.mapper.MerchantConfigureMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 个体商户配置
 *
 * @author zhanglize
 * @create 2019/10/10
 */
@Service
public class MerchantConfigureService {

    @Autowired
    private MerchantConfigureMapper mapper;


    /**
     * 轮询池定时任务,每天晚上12:00对于轮询池中的状态,以及单日的交易金额总量进行清0
     *
     */
    @Scheduled(cron = "0 0 0 * * ? *")
    public void task(){
        //查询所有状态为完成状态的1所对应的个体商户配置信息
        List<MerchantConfigure> configureList = findByStstus("1");
        configureList.forEach(merchantConfigure -> {
            merchantConfigure.setTotalOneAmount("0");
            merchantConfigure.setStatus("0");
            update(merchantConfigure.getId(),merchantConfigure.getTotalOneAmount(),merchantConfigure.getStatus());
        });
        //查询所有状态为未完成状态0所对应的个体工商户信息
        List<MerchantConfigure> list = findByStstus("1");
        list.forEach(merchantConfigure -> {
            merchantConfigure.setTotalOneAmount("0");
            merchantConfigure.setStatus("0");
            update(merchantConfigure.getId(),merchantConfigure.getTotalOneAmount(),merchantConfigure.getStatus());
        });
    }

    /**
     * 根据id修改个体工商户配置的基本信息
     *
     * @param id    主键
     * @param totalOneAmount 单日总额
     * @param status  状态
     * @return
     */
    public int update(Long id, String totalOneAmount, String status) {
        return mapper.update(id,totalOneAmount,status);
    }

    /**
     * 根据状态查询所需要的个体工商户的信息
     *
     * @return
     * @param status  状态
     */
    public List<MerchantConfigure> findByStstus(String status) {
        return mapper.findByStstus(status);
    }

    /**
     * 查询公司下所有可用的商户id信息
     *
     *
     * @param payType 支付类型
     * @param status  状态
     * @param compID  公司ID
     * @return
     */
    public List<MerchantConfigure> findByStstusCompId(String status, String compID,String payType) {
        return mapper.findByStstusCompId(status,compID,payType);
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
     * @param id   ID
     * @param status 状态
     * @return
     */
    public int updateStatus(Long id, String status) {
        return mapper.updateStatus(id,status);
    }
}
