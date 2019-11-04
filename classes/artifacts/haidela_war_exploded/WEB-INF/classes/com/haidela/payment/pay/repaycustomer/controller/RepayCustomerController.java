package com.haidela.payment.pay.repaycustomer.controller;

import com.haidela.payment.pay.repaycustomer.domain.RepayCustomer;
import com.haidela.payment.pay.repaycustomer.service.RepayCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
    @RequestMapping(value = "add",method = RequestMethod.POST)
    public int add(RepayCustomer customer){
        return service.add(customer);
    }

    /**
     * 根据交易流水号查询对应的代付交易对象的信息
     *
     * @param tranFlow  交易流水号
     * @return
     */
    @RequestMapping(value = "findByTranFlow/{tranFlow}",method = RequestMethod.GET)
    public RepayCustomer findByTranFlow(@PathVariable String tranFlow) {
        return service.findByTranFlow(tranFlow);
    }

    /**
     *
     * 根据交易流水号修改代付交易的状态
     *
     * @param tranFlow  交易流水号
     * @param status    交易状态
     * @return
     */
    @RequestMapping(value = "updateByStatus/{tranFlow}/{status}",method = RequestMethod.PUT)
    public int updateByStatus(@PathVariable String tranFlow,@PathVariable String status) {
        return service.updateByStatus(tranFlow,status);
    }

    /**
     * 查询代付中各个账户的信息
     *
     * @return
     */
    @RequestMapping(value = "success",method = RequestMethod.GET)
    public Map<String,String> findRepayCustomer() {
        return service.findRepayCustomer();
    }

    /**
     * 查询代付中各个账户代付失败的信息
     *
     * @return
     */
    @RequestMapping(value = "fail",method = RequestMethod.GET)
    public Map<String,String> findFailRepayCustomer() {
        return service.findFailRepayCustomer();
    }


}