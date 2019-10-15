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
@RequestMapping("/pay-customer")
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
    @RequestMapping("update-status/{tranFlow}/{status}")
    @PostMapping
    public int updateStatus(@PathVariable String tranFlow, @PathVariable String status){
        return service.updateStatus(tranFlow,status);
    }

    /**
     * 根据交易流水号修改该笔交易的异步消息接收的情况
     *
     * @param tranFlow     交易流水号
     * @param receiveMessages   消息
     * @return
     */
    @RequestMapping("update-receive-messages/{tranFlow}/{receiveMessages}")
    @PostMapping
    public int updateReceiveMessages(@PathVariable String tranFlow, @PathVariable String receiveMessages){
        return service.updateReceiveMessages(tranFlow,receiveMessages);
    }

    /**
     * 根据交易流水号查询该交易流水号是否存在
     *
     * @param tranFlow  交易流水号
     * @return
     */
    @RequestMapping("find-tranflow/{tranFlow}")
    @PostMapping
    public PayCustomer findByTranFlow(@PathVariable String tranFlow){
        return service.findByTranFlow(tranFlow);
    }

    /**
     * 根据id修改该条商户信息的订单信息
     *
     * @param id
     * @param paySerialNo  平台交易流水号
     * @return
     */
    @RequestMapping("pay-serial-no/{id}/{paySerialNo}")
    @PostMapping
    public int updateByPaySerialNo(@PathVariable Long id,@PathVariable String paySerialNo){
        return service.updateByPaySerialNo(id,paySerialNo);
    }

}
