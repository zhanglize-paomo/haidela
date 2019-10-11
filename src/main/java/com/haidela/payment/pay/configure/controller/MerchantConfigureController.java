package com.haidela.payment.pay.configure.controller;


import com.haidela.payment.pay.configure.domain.MerchantConfigure;
import com.haidela.payment.pay.configure.service.MerchantConfigureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 个体商户配置对象控制器层
 *
 * @author zhanglize
 * @create 2019/10/10
 */
@RestController("merchantConfigureController")
@RequestMapping("/merchant-configure")
public class MerchantConfigureController {


    private MerchantConfigureService service;
    @Autowired
    public void setService(MerchantConfigureService service) {
        this.service = service;
    }


    /**
     * 查询所有状态为1的个体商户信息
     *
     * @return
     */
    @RequestMapping()
    public List<MerchantConfigure> findByStstus(){
        return service.findByStstus();
    }


    /**
     * 根据商户id修改该条商户的调用时间
     *
     * @param merchantId
     * @return
     */
    @RequestMapping("merchantId/{merchantId}")
    public int updateMerchantId(@PathVariable String merchantId){
        return service.updateMerchantId(merchantId);
    }

}
