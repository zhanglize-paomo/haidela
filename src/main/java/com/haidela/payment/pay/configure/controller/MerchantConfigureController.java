package com.haidela.payment.pay.configure.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.haidela.payment.pay.configure.domain.MerchantConfigure;
import com.haidela.payment.pay.configure.service.MerchantConfigureService;
import com.haidela.payment.util.DateUtils;
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
    @RequestMapping(value = "{status}", method = RequestMethod.GET)
    public List<MerchantConfigure> findByStstus(@PathVariable String status) {
        return service.findByStstus(status);
    }


    /**
     * 根据商户id修改该条商户的调用时间
     *
     * @param merchantId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "merchantId/{merchantId}", method = RequestMethod.PUT)
    public int updateMerchantId(@PathVariable String merchantId) {
        return service.updateMerchantId(merchantId);
    }

    /**
     * 根据商户id修改该条商户的调用时间
     *
     * @param merchantId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "merchantId/{merchantId}/{payType}", method = RequestMethod.GET)
    public MerchantConfigure findByMerchantNo(@PathVariable String merchantId, @PathVariable String payType) {
        return service.findByMerchantNo(merchantId, payType);
    }

    /**
     * 查询所有的商户交易成功的交易信息
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public Map<String, String> findByCustomer() {
        return service.findByCustomer();
    }

    /**
     * 根据id修改对应的数据信息
     *
     * @param id
     * @param configure
     * @return
     */
    @RequestMapping("/toEdit")
    public String toEdit(Long id, MerchantConfigure configure) {
        //判断时间格式
        if (DateUtils.judgeFormat(configure.getStartTime()) == true && DateUtils.judgeFormat(configure.getEndTime()) == true) {
            //将开始时间以及结束时间进行截取
            configure.setStartTime(DateUtils.dateToOnlyTime(DateUtils.stringToDate(configure.getStartTime())));
            configure.setEndTime(DateUtils.dateToOnlyTime(DateUtils.stringToDate(configure.getEndTime())));
        }
        service.toEdit(id, configure);
        //重定向到指定的数据信息中
        return "redirect:/merchant-configure/all";
    }

    @RequestMapping("/edit")
    public String edit(Model model, Long id) {
        MerchantConfigure configure = service.findById(id);
        model.addAttribute("configure", configure);
        return "/configureEdit";
    }


    @RequestMapping(value = "/toDelete")
    public String toDelete(Long id) {
        service.toDelete(id);
        //重定向到指定的数据信息中
        return "redirect:/merchant-configure/all";
    }


    /**
     * 根据条件查询对应的商户配置信息
     *
     * @param merchantId 商户ID
     * @param compID     公司ID
     * @param payType    交易类型
     * @param status     交易状态
     * @param pageNum
     * @param pageSize
     * @param model
     * @return
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String findByAll(@RequestParam(required = false) String merchantId,
                            @RequestParam(required = false) String compID,
                            @RequestParam(required = false) String payType,
                            @RequestParam(required = false) String status,
                            @RequestParam(defaultValue = "1") Integer pageNum,
                            @RequestParam(defaultValue = "10") Integer pageSize,
                            Model model) {
        //使用pageHelper设置分页
        PageHelper.startPage(pageNum, pageSize);
        //startPage后紧跟的这个查询就是分页查询
        List<MerchantConfigure> configureList = service.findByAll(merchantId, compID, payType, status);
        //使用PageInfo包装查询结果，只需要将pageInfo交给页面就可以
        PageInfo pageInfo = new PageInfo<>(configureList);
        Integer startPage = 1;
        model.addAttribute("pageInfo", pageInfo);
        //获得当前页码8
        model.addAttribute("pageNum", pageInfo.getPageNum());
        //总共多少页
        model.addAttribute("pages", pageInfo.getPages());
        //上一页
        model.addAttribute("prePage", pageInfo.getPrePage());
        //下一页
        model.addAttribute("nextPage", pageInfo.getNextPage());
        //首页
        model.addAttribute("startPage", startPage);
        //尾页
        model.addAttribute("endPage", pageInfo.getPages());
        model.addAttribute("configureList", pageInfo.getList());
        return "/configure";
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public String insert(MerchantConfigure configure) {
        //根据商户ID以及支付类型判断是否存在
        if (service.findByMerchantNo(configure.getMerchantId(), configure.getPayType()) != null) {
            return "商户的支付类型已经存在";
        }
        //将开始时间以及结束时间进行截取
        configure.setStartTime(DateUtils.dateToOnlyTime(DateUtils.stringToDate(configure.getStartTime())));
        configure.setEndTime(DateUtils.dateToOnlyTime(DateUtils.stringToDate(configure.getEndTime())));
        service.insert(configure);
        //重定向到指定的数据信息中
        return "redirect:/merchant-configure/all";
    }

    @RequestMapping(value = "/configure/insert")
    public String insert() {
        return "/configureAdd";
    }
}
