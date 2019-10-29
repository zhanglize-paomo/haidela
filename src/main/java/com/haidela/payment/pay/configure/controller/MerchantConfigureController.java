package com.haidela.payment.pay.configure.controller;


import com.haidela.payment.pay.configure.domain.MerchantConfigure;
import com.haidela.payment.pay.configure.service.MerchantConfigureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 个体商户配置对象控制器层
 *
 * @author zhanglize
 * @create 2019/10/10
 */
@Controller("merchantConfigureController")
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
    @ResponseBody
    @RequestMapping(value = "{status}",method = RequestMethod.GET)
    public List<MerchantConfigure> findByStstus(@PathVariable String status){
        return service.findByStstus(status);
    }


    /**
     * 根据商户id修改该条商户的调用时间
     *
     * @param merchantId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "merchantId/{merchantId}",method = RequestMethod.PUT)
    public int updateMerchantId(@PathVariable String merchantId){
        return service.updateMerchantId(merchantId);
    }

    /**
     * 根据商户id修改该条商户的调用时间
     *
     * @param merchantId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "merchantId/{merchantId}/{payType}",method = RequestMethod.GET)
    public MerchantConfigure findByMerchantNo(@PathVariable String merchantId,@PathVariable String payType){
        return service.findByMerchantNo(merchantId,payType);
    }

    /**
     * 查询所有的商户交易成功
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public Map<String,String> findByCustomer(){
        return service.findByCustomer();
    }


    @RequestMapping("/toEdit")
    public String toEdit(Model model, Long id) {
        MerchantConfigure  configure = service.findById(id);
        model.addAttribute("configure", configure);
        return "user/userEdit";
    }


    @RequestMapping(value = "/toDelete")
    public String toDelete(Long id) {
        service.toDelete(id);
        return "/configure";
    }


    @RequestMapping("/all")
    public String findByAll(Model model) {
        List<MerchantConfigure> configureList = service.finAllCustomer();
        model.addAttribute("configureList", configureList);
        return "/configure";
    }

    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public String insert(MerchantConfigure configure) {
        service.insert(configure);
        return "/configure";
    }

    @RequestMapping(value = "/configure/insert")
    public String insert() {
        return "/configureAdd";
    }

    @RequestMapping("/jump")
    public String findByJump() {
        return "/configure";
    }
}
