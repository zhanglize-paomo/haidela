package com.haidela.payment.pay.repaycustomer.controller;

import com.haidela.payment.pay.repaycustomer.domain.RepayCustomer;
import com.haidela.payment.pay.repaycustomer.service.RepayCustomerService;
import com.hfb.merchant.pay.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
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
        //根据日期查询当天成功的入账信息并计算总额
        List<RepayCustomer> repayCustomerList = service.findByTodayDate(DateUtil.getDate());
        Integer sucessAmount = 0;
        Integer failAmount = 0;
        for (int i = 0; i < repayCustomerList.size(); i++) {
            if (repayCustomerList.get(i).getStatus().equals("0000")) {
                sucessAmount += Integer.parseInt(repayCustomerList.get(i).getAmount());
            }else{
                failAmount += Integer.parseInt(repayCustomerList.get(i).getAmount());
            }
        }
        //单位为分,将分单位转换为元
        String sucessRmb = sucessAmount / 100 + "." + sucessAmount % 100 / 10 + sucessAmount % 100 % 10;
        String failRmb = failAmount / 100 + "." + failAmount % 100 / 10 + failAmount % 100 % 10;
        model.addAttribute("sucessAmount",sucessRmb);
        model.addAttribute("failRmb",failRmb);
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
