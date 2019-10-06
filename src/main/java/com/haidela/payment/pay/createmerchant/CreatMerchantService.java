package com.haidela.payment.pay.createmerchant;

import com.haidela.payment.util.MD5;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.TreeMap;

/**
 * 商户进件Service
 *
 * @author zhanglize
 * @create 2019/10/4
 */
@Service
public class CreatMerchantService {


    /**
     * 获取到参数对象的签名信息
     *
     * @param request
     * @param response
     * @return
     */
    public String getSign(HttpServletRequest request, HttpServletResponse response) {
        String key = "adc2fbfb4654ed95b28dfe0a0cb03da6";
        Map<String, String> trnMap = new TreeMap<String, String>();
        trnMap.put("merchantName",request.getParameter("merchantName"));  //商户名称
        trnMap.put("merchantShortName",request.getParameter("merchantShortName"));    //商户简称
        trnMap.put("industrId",request.getParameter("industrId"));     //行业类别（mcc码）
        trnMap.put("province",request.getParameter("province"));      //省份（地区CODE码）
        trnMap.put("city",request.getParameter("city"));   //城市（地区CODE码）
        trnMap.put("county",request.getParameter("county"));   //城市（地区CODE码）
        trnMap.put("tel",request.getParameter("tel"));     //电话号
        trnMap.put("email",request.getParameter("email"));  //邮箱   加密
        trnMap.put("customerPhone",request.getParameter("customerPhone"));  //法人手机号  加密
        trnMap.put("principal",request.getParameter("principal"));     //负责人
        trnMap.put("principalMobile",request.getParameter("principalMobile"));  //负责人手机号  加密
        trnMap.put("idCode",request.getParameter("idCode"));  //负责人证件号   加密
        trnMap.put("indentityPhoto",request.getParameter("indentityPhoto"));    //负责人证件照 上传正反面两张，以;分割(前面图片处理中有返回)
        trnMap.put("accountCode",request.getParameter("accountCode"));      //银行卡号  加密
        trnMap.put("bankId",request.getParameter("bankId"));       //开户银行 ID (取分支行联行号表.xlsx)
        trnMap.put("accountName",request.getParameter("accountName"));   //开户人
        trnMap.put("contactLine",request.getParameter("contactLine"));    // 联行号 (取分支行联行号表.xlsx)
        trnMap.put("bankName",request.getParameter("bankName"));         //银行名称 (取分支行联行号表.xlsx)
        trnMap.put("bankBranchName",request.getParameter("bankBranchName"));     //开户支行名称 (取分支行联行号表.xlsx)
        trnMap.put("bankProvince",request.getParameter("bankProvince"));      //开户支行所在省 (取分支行联行号表.xlsx 中 省份ID)
        trnMap.put("bankCity",request.getParameter("bankCity"));     //开户支行所在市 (取分支行联行号表.xlsx 中 城市ID)
        trnMap.put("idCard",request.getParameter("idCard"));    //持卡人身份证号  加密
        trnMap.put("bankTel",request.getParameter("bankTel"));    //持卡人手机号  加密
        trnMap.put("address",request.getParameter("address"));    //法人地址
        trnMap.put("bankAddress",request.getParameter("bankAddress"));  //持卡人地址
        trnMap.put("billRate",request.getParameter("billRate"));     //结算费率（信用卡手续费费率 （微信支付宝小额贷记卡 ） （单笔交易≤1000元）） 例 千分之 3 直接传3
        trnMap.put("licensePhoto",request.getParameter("licensePhoto"));
        trnMap.put("businessLicense",request.getParameter("businessLicense"));
        String str ="";
        try {
            str = MD5.md5(trnMap.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }
}
