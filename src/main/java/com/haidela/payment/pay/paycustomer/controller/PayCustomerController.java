package com.haidela.payment.pay.paycustomer.controller;

/**
 * @author zhanglize
 * @create 2019/10/10
 */

import com.haidela.payment.pay.paycustomer.domain.PayCustomer;
import com.haidela.payment.pay.paycustomer.service.PayCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 客户交易流水信息
 *
 * @author zhanglize
 * @create 2019/10/10
 */
@RestController("payCustomerController")
@RequestMapping("/paycustomer")
public class PayCustomerController {

    private PayCustomerService service;

    @Autowired
    public void setService(PayCustomerService service) {
        this.service = service;
    }

    /**
     * 新增客户交易流水信息
     *
     * @param customer
     * @return
     */
    @RequestMapping("add")
    @PostMapping
    public int add(PayCustomer customer){
        return service.add(customer);
    }

    /**
     * 根据交易流水号修改该条交易的交易状态
     *
     * @param tranFlow  交易流水号
     * @param status   交易状态
     * @return
     */
    @RequestMapping("updatestatus/{tranFlow}/{status}")
    @PostMapping
    public int updateStatus(@PathVariable String tranFlow, @PathVariable String status){
        return service.updateStatus(tranFlow,status);
    }



}
