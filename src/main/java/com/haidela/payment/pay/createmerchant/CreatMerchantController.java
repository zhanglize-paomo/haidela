package com.haidela.payment.pay.createmerchant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 商户进件控制器
 *
 *
 * @author zhanglize
 * @create 2019/10/4
 */
@RestController("creatMerchantController")
public class CreatMerchantController {

    private CreatMerchantService service;

    @Autowired
    public void setService(CreatMerchantService service) {
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
    public String getSign(HttpServletRequest request, HttpServletResponse response ) {
        return service.getSign(request,response);
    }
}
