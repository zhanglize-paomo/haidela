package com.haidela.payment.pay.createmerchant;

import com.haidela.payment.util.MD5;
import com.haidela.payment.util.RSAUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
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
    String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDFf6krDWQUDUzKj+K+WvML2EyZLagKaJ5YTeCoBNhx/WpD1Vh2j/wQ9G3RC/" +
            "tpUcmE7szr/vgdEVHkOfk6mGpeHapS6QE4enJ/CVaTPM573uI8VGWBek9v/E6HaVxRV0Hs8ZsvHAKopqYNDZRKhIrlLUrrkFD2KXJgIiRPQALeMQIDAQAB";
    String key = "adc2fbfb4654ed95b28dfe0a0cb03da6";
    /**
     * 获取到参数对象的签名信息
     *
     * @param request
     * @param response
     * @return
     */
    public  Map<String,String>  getSign(HttpServletRequest request, HttpServletResponse response) {
        Map<String,String> map = new HashMap<>();
        Map<String, String> trnMap = new TreeMap<String, String>();
        trnMap.put("merchantName",request.getParameter("merchantName"));  //商户名称
        trnMap.put("merchantShortName",request.getParameter("merchantShortName"));    //商户简称
        trnMap.put("industrId",request.getParameter("industrId"));     //行业类别（mcc码）
        trnMap.put("province",request.getParameter("province"));      //省份（地区CODE码）
        trnMap.put("city",request.getParameter("city"));   //城市（地区CODE码）
        trnMap.put("county",request.getParameter("county"));   //城市（地区CODE码）
        trnMap.put("tel",request.getParameter("tel"));     //电话号
        trnMap.put("email", RSAUtils.encrypt(request.getParameter("email"), publicKey));  //邮箱   加密
        trnMap.put("customerPhone",RSAUtils.encrypt(request.getParameter("customerPhone"), publicKey));  //法人手机号  加密
        trnMap.put("principal",request.getParameter("principal"));     //负责人
        trnMap.put("principalMobile",RSAUtils.encrypt(request.getParameter("principalMobile"), publicKey));  //负责人手机号  加密
        trnMap.put("idCode",RSAUtils.encrypt(request.getParameter("idCode"), publicKey));  //负责人证件号   加密
        trnMap.put("indentityPhoto",request.getParameter("indentityPhoto"));    //负责人证件照 上传正反面两张，以;分割(前面图片处理中有返回)
        trnMap.put("accountCode",RSAUtils.encrypt(request.getParameter("accountCode"), publicKey));      //银行卡号  加密
        trnMap.put("bankId",request.getParameter("bankId"));       //开户银行 ID (取分支行联行号表.xlsx)
        trnMap.put("accountName",request.getParameter("accountName"));   //开户人
        trnMap.put("contactLine",request.getParameter("contactLine"));    // 联行号 (取分支行联行号表.xlsx)
        trnMap.put("bankName",request.getParameter("bankName"));         //银行名称 (取分支行联行号表.xlsx)
        trnMap.put("bankBranchName",request.getParameter("bankBranchName"));     //开户支行名称 (取分支行联行号表.xlsx)
        trnMap.put("bankProvince",request.getParameter("bankProvince"));      //开户支行所在省 (取分支行联行号表.xlsx 中 省份ID)
        trnMap.put("bankCity",request.getParameter("bankCity"));     //开户支行所在市 (取分支行联行号表.xlsx 中 城市ID)
        trnMap.put("idCard",RSAUtils.encrypt(request.getParameter("idCard"), publicKey));    //持卡人身份证号  加密
        trnMap.put("bankTel",RSAUtils.encrypt(request.getParameter("bankTel"), publicKey));    //持卡人手机号  加密
        trnMap.put("address",request.getParameter("address"));    //法人地址
        trnMap.put("bankAddress",request.getParameter("bankAddress"));  //持卡人地址
        trnMap.put("billRate",request.getParameter("billRate"));     //结算费率（信用卡手续费费率 （微信支付宝小额贷记卡 ） （单笔交易≤1000元）） 例 千分之 3 直接传3
        trnMap.put("licensePhoto",request.getParameter("licensePhoto"));
        trnMap.put("businessLicense",request.getParameter("businessLicense"));
        String str= MD5.getSignMsg(trnMap, key);
        String sign = "";
        try {
             sign = MD5.md5(str).toUpperCase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 请求前对加密数据处理替换
        try {
            map.put("email", URLEncoder.encode(trnMap.get("email"), "utf-8"));       //邮箱   加密
            map.put("customerPhone", URLEncoder.encode(trnMap.get("customerPhone"), "utf-8"));  //法人手机号  加密
            map.put("principalMobile", URLEncoder.encode(trnMap.get("principalMobile"), "utf-8"));   //负责人手机号  加密
            map.put("idCode", URLEncoder.encode(trnMap.get("idCode"), "utf-8"));        //负责人证件号   加密
            map.put("accountCode", URLEncoder.encode(trnMap.get("accountCode"), "utf-8"));  //银行卡号  加密
            map.put("idCard", URLEncoder.encode(trnMap.get("idCard"), "utf-8"));         //持卡人身份证号  加密
            map.put("bankTel", URLEncoder.encode(trnMap.get("bankTel"), "utf-8"));       //持卡人手机号  加密
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        map.put("sign",sign);
        return map;
    }
}
