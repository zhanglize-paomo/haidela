package com.haidela.payment.pay.configure.service;

import com.haidela.payment.pay.configure.domain.MerchantConfigure;
import com.haidela.payment.pay.configure.mapper.MerchantConfigureMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
}
