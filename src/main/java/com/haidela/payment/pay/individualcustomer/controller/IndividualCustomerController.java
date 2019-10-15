package com.haidela.payment.pay.individualcustomer.controller;

import com.haidela.payment.pay.individualcustomer.domain.IndividualCustomer;
import com.haidela.payment.pay.individualcustomer.service.IndividualCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 商户进件个体商户信息控制器
 *
 *
 * @author zhanglize
 * @create 2019/10/4
 */
@RestController("individualCustomerController")
@RequestMapping("/individual-customer")
public class IndividualCustomerController {

    private IndividualCustomerService service;

    @Autowired
    public void setService(IndividualCustomerService service) {
        this.service = service;
    }

    /**
     * 根据请求参数获取到签名信息
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(path = "/sign")
    public Map<String,String> getSign(HttpServletRequest request, HttpServletResponse response ) {
        return service.getSign(request,response);
    }

    /**
     * 新增商户进件个体商户信息
     *
     * @param customer
     * @return
     */
    @RequestMapping("add")
    @PostMapping
    public int add(IndividualCustomer customer){
        return service.add(customer);
    }

    /**
     * 根据请求参数获取到签名信息并获取到商户的进件信息
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(path = "/other-sign")
    public Map<String,String> otherGetSign(HttpServletRequest request, HttpServletResponse response ) {
        return service.otherGetSign(request,response);
    }



    /**
     * 商户D0开通接口获取签名信息
     *
     * @return
     */
    @RequestMapping(path = "open-withdraw")
    public Map<String,String> openWithDraw() {
        return service.openWithDraw();
    }


}
