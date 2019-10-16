package com.haidela.payment.pay.repaycustomer.controller;

import com.haidela.payment.pay.repaycustomer.domain.RepayCustomer;
import com.haidela.payment.pay.repaycustomer.service.RepayCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 代付消息接收情况控制器层
 *
 *
 * @author zhanglize
 * @create 2019/10/15
 */
@RestController("RepayCustomerController")
@RequestMapping("/repay-customer")
public class RepayCustomerController {

    private RepayCustomerService service;

    @Autowired
    public void setService(RepayCustomerService service) {
        this.service = service;
    }

    /**
     * 新增代付消息接收情况
     *
     * @param customer
     * @return
     */
    @RequestMapping("add")
    @PostMapping
    public int add(RepayCustomer customer){
        return service.add(customer);
    }
}
