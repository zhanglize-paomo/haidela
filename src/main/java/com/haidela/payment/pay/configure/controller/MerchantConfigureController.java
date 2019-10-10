package com.haidela.payment.pay.configure.controller;

import com.haidela.payment.pay.configure.domain.MerchantConfigure;
import com.haidela.payment.pay.configure.service.MerchantConfigureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 个体商户配置对象控制器层
 *
 * @author zhanglize
 * @create 2019/10/10
 */
@RestController
@RequestMapping("/merchant-configure")
public class MerchantConfigureController {


    private MerchantConfigureService service;
    @Autowired
    public void setService(MerchantConfigureService service) {
        this.service = service;
    }

    @RequestMapping("getUser/{id}")
    public MerchantConfigure GetUser(@PathVariable int id){
        return service.Sel(id);
    }

}
