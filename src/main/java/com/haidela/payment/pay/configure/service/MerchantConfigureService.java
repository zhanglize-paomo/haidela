package com.haidela.payment.pay.configure.service;

import com.haidela.payment.pay.configure.domain.MerchantConfigure;
import com.haidela.payment.pay.configure.mapper.MerchantConfigureMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhanglize
 * @create 2019/10/10
 */
@Service
public class MerchantConfigureService {

    @Autowired
    private MerchantConfigureMapper mapper;

    public MerchantConfigure Sel(int id) {
        return mapper.Sel(id);
    }


    /**
     * 查询所有状态为1的个体商户信息
     *
     * @return
     */
    public List<MerchantConfigure> findByStstus() {
        return mapper.findByStstus();
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
}
