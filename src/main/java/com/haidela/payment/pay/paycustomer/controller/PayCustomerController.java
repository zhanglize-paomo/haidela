package com.haidela.payment.pay.paycustomer.controller;

/**
 * @author zhanglize
 * @create 2019/10/10
 */

import com.haidela.payment.pay.paycustomer.domain.PayCustomer;
import com.haidela.payment.pay.paycustomer.service.PayCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @RequestMapping(value = "add",method = RequestMethod.POST)
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
    @RequestMapping(value = "update-status/{tranFlow}/{status}",method = RequestMethod.POST)
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
    @RequestMapping(value = "update-receive-messages/{tranFlow}/{receiveMessages}",method = RequestMethod.POST)
    public int updateReceiveMessages(@PathVariable String tranFlow, @PathVariable String receiveMessages){
        return service.updateReceiveMessages(tranFlow,receiveMessages);
    }

    /**
     * 根据交易流水号查询该交易流水号是否存在
     *
     * @param tranFlow  交易流水号
     * @return
     */
    @RequestMapping(value = "find-tranflow/{tranFlow}",method = RequestMethod.POST)
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
    @RequestMapping(value = "pay-serial-no/{id}/{paySerialNo}",method = RequestMethod.POST)
    public int updateByPaySerialNo(@PathVariable Long id,@PathVariable String paySerialNo){
        return service.updateByPaySerialNo(id,paySerialNo);
    }

    @RequestMapping(value = "payCustomerDetail",method = RequestMethod.GET)
    public List<PayCustomer> pagePayCustomerDetail(@RequestParam String startTime, @RequestParam String endTime, @RequestParam String compID,
                                                   @RequestParam String customerId, @RequestParam String typeStr, @RequestParam String tranFlow){
        return service.pagePayCustomerDetail(startTime,endTime,compID,customerId,typeStr,tranFlow);
    }


}
