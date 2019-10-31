package com.haidela.payment.pay.repaycustomer.controller;

import com.haidela.payment.pay.repaycustomer.domain.RepayCustomer;
import com.haidela.payment.pay.repaycustomer.service.RepayCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 代付消息接收情况控制器层
 *
 *
 * @author zhanglize
 * @create 2019/10/15
 */
@Controller("RepayCustomerController")
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
    @ResponseBody
    @RequestMapping(value = "add",method = RequestMethod.POST)
    public int add(RepayCustomer customer){
        return service.add(customer);
    }

    /**
     * 清算代付金额信息并显示数据到前端页面
     *
     * @param repayCustomer
     * @return
     */
    @RequestMapping(value = "repay-add",method = RequestMethod.POST)
    public String repayAddPost(RepayCustomer repayCustomer, Model model) {
        service.add(repayCustomer);
        Map<String,String> map = service.getRepayAmount();
        model.addAttribute("sucessAmount",map.get("sucessRmb"));
        model.addAttribute("failRmb",map.get("failRmb"));
        return "/query";
    }


    /**
     * 根据交易流水号查询对应的代付交易对象的信息
     *
     * @param tranFlow  交易流水号
     * @return
     */
    @ResponseBody
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
    @ResponseBody
    @RequestMapping(value = "updateByStatus/{tranFlow}/{status}",method = RequestMethod.PUT)
    public int updateByStatus(@PathVariable String tranFlow,@PathVariable String status) {
        return service.updateByStatus(tranFlow,status);
    }

    /**
     * 查询代付中各个账户的信息
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "success",method = RequestMethod.GET)
    public Map<String,String> findRepayCustomer() {
        return service.findRepayCustomer();
    }

    /**
     * 查询代付中各个账户代付失败的信息
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "fail",method = RequestMethod.GET)
    public Map<String,String> findFailRepayCustomer() {
        return service.findFailRepayCustomer();
    }

}
