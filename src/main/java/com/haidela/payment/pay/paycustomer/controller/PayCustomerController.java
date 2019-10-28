package com.haidela.payment.pay.paycustomer.controller;

/**
 * @author zhanglize
 * @create 2019/10/10
 */

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.haidela.payment.pay.paycustomer.domain.PayCustomer;
import com.haidela.payment.pay.paycustomer.service.PayCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 客户交易流水信息
 *
 * @author zhanglize
 * @create 2019/10/10
 */
@Controller("payCustomerController")
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
    @ResponseBody
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public int add(PayCustomer customer) {
        return service.add(customer);
    }

    /**
     * 根据交易流水号修改该条交易的交易状态
     *
     * @param tranFlow 交易流水号
     * @param status   交易状态
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "update-status/{tranFlow}/{status}", method = RequestMethod.POST)
    public int updateStatus(@PathVariable String tranFlow, @PathVariable String status) {
        return service.updateStatus(tranFlow, status);
    }

    /**
     * 根据交易流水号修改该笔交易的异步消息接收的情况
     *
     * @param tranFlow        交易流水号
     * @param receiveMessages 消息
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "update-receive-messages/{tranFlow}/{receiveMessages}", method = RequestMethod.POST)
    public int updateReceiveMessages(@PathVariable String tranFlow, @PathVariable String receiveMessages) {
        return service.updateReceiveMessages(tranFlow, receiveMessages);
    }

    /**
     * 根据交易流水号查询该交易流水号是否存在
     *
     * @param tranFlow 交易流水号
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "find-tranflow/{tranFlow}", method = RequestMethod.POST)
    public PayCustomer findByTranFlow(@PathVariable String tranFlow) {
        return service.findByTranFlow(tranFlow);
    }

    /**
     * 根据id修改该条商户信息的订单信息
     *
     * @param id
     * @param paySerialNo 平台交易流水号
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "pay-serial-no/{id}/{paySerialNo}", method = RequestMethod.POST)
    public int updateByPaySerialNo(@PathVariable Long id, @PathVariable String paySerialNo) {
        return service.updateByPaySerialNo(id, paySerialNo);
    }

    @RequestMapping(value = "payCustomerDetail", method = RequestMethod.GET)
    public String pagePayCustomerDetail(@RequestParam(required = false) String startTime,
                                        @RequestParam(required = false) String endTime,
                                        @RequestParam(required = false) String compID,
                                        @RequestParam(required = false) String customerId,
                                        @RequestParam(required = false) String typeStr,
                                        @RequestParam(required = false) String tranFlow,
                                        @RequestParam(defaultValue = "1") Integer pageNum,
                                        @RequestParam(defaultValue = "10") Integer pageSize,
                                        Model model
    ) {
        //引入分页查询，使用PageHelper分页功能在查询之前传入当前页，然后多少记录
        PageHelper.startPage(pageNum, pageSize);
        //startPage后紧跟的这个查询就是分页查询
        List<PayCustomer> customerList =  service.pagePayCustomerDetail(startTime, endTime, compID, customerId, typeStr, tranFlow);
        //使用PageInfo包装查询结果，只需要将pageInfo交给页面就可以
        PageInfo pageInfo = new PageInfo<PayCustomer>(customerList, 5);
        Integer startPage = 0;
        Integer endPage = 0;
        model.addAttribute("pageInfo", pageInfo);
        //获得当前页
        model.addAttribute("pageNum", pageInfo.getPageNum());
        //获得一页显示的条数
        model.addAttribute("pageSize", pageInfo.getPageSize());
        model.addAttribute("size", pageInfo.getSize());
        model.addAttribute("startRow", pageInfo.getStartRow());
        model.addAttribute("endRow", pageInfo.getEndRow());
        //获得总页数
        model.addAttribute("totalPages", pageInfo.getPages());
        //是否是第一页
        model.addAttribute("isFirstPage", pageInfo.isIsFirstPage());
        //是否是最后一页
        model.addAttribute("isLastPage", pageInfo.isIsLastPage());
        //总共多少页
        model.addAttribute("pages", pageInfo.getPages());
        model.addAttribute("prePage", pageInfo.getPrePage());
        model.addAttribute("nextPage", pageInfo.getNextPage());
        model.addAttribute("isLastPage", pageInfo.isIsLastPage());
        model.addAttribute("hasPreviousPage", pageInfo.isHasPreviousPage());
        model.addAttribute("hasNextPage", pageInfo.isHasNextPage());
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("detailList",customerList);
        return "/query";
    }

    /**
     * 导出Excel表格
     *
     * @param startTime  开始时间
     * @param endTime    结束时间
     * @param compID     公司ID
     * @param customerId
     * @param typeStr
     * @param tranFlow
     */
    @ResponseBody
    @RequestMapping(value = "/pay/export", method = RequestMethod.GET)
    public void exportPayCustomerDetail(@RequestParam(required = false) String startTime,
                                        @RequestParam(required = false) String endTime,
                                        @RequestParam(required = false) String compID,
                                        @RequestParam(required = false) String customerId,
                                        @RequestParam(required = false) String typeStr,
                                        @RequestParam(required = false) String tranFlow, HttpServletResponse response
    ) {
        service.exportPayCustomerDetail(startTime, endTime, compID, customerId, typeStr, tranFlow, response);
    }


    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "/index";
    }

}
