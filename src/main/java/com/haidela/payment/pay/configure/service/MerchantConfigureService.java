package com.haidela.payment.pay.configure.service;

import com.haidela.payment.pay.configure.mapper.MerchantConfigureMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


}
