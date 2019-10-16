package com.haidela.payment.pay.individualcustomer.service;

import com.alibaba.fastjson.JSONObject;
import com.haidela.payment.pay.individualcustomer.domain.IndividualCustomer;
import com.haidela.payment.pay.individualcustomer.mapper.IndividualCustomerMapper;
import com.haidela.payment.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * 商户进件个体商户信息Service
 *
 * @author zhanglize
 * @create 2019/10/4
 */
@Service
public class IndividualCustomerService {

    private IndividualCustomerMapper mapper;
    @Autowired
    public void setMapper(IndividualCustomerMapper mapper) {
        this.mapper = mapper;
    }

    private static final Logger logger = LoggerFactory.getLogger(IndividualCustomerService.class);

    /**
     * 获取到参数对象的签名信息
     *
     * @param request
     * @param response
     * @return
     */
    public Map<String, String> getSign(HttpServletRequest request, HttpServletResponse response) {
        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDFf6krDWQUDUzKj+K+WvML2EyZLagKaJ5YTeCoBNhx/WpD1Vh2j/wQ9G3RC/" +
                "tpUcmE7szr/vgdEVHkOfk6mGpeHapS6QE4enJ/CVaTPM573uI8VGWBek9v/E6HaVxRV0Hs8ZsvHAKopqYNDZRKhIrlLUrrkFD2KXJgIiRPQALeMQIDAQAB";
        String key = "adc2fbfb4654ed95b28dfe0a0cb03da6";
        Map<String, String> map = new HashMap<>();
        Map<String, String> trnMap = new TreeMap<String, String>();
        trnMap.put("merchantName", request.getParameter("merchantName"));  //商户名称
        trnMap.put("merchantShortName", request.getParameter("merchantShortName"));    //商户简称
        trnMap.put("industrId", request.getParameter("industrId"));     //行业类别（mcc码）
        trnMap.put("province", request.getParameter("province"));      //省份（地区CODE码）
        trnMap.put("city", request.getParameter("city"));   //城市（地区CODE码）
        trnMap.put("county", request.getParameter("county"));   //城市（地区CODE码）
//        trnMap.put("tel", RSAUtils.encrypt(request.getParameter("tel"), publicKey));     //电话号
        trnMap.put("email", RSAUtils.encrypt(request.getParameter("email"), publicKey));  //邮箱   加密
        trnMap.put("customerPhone", RSAUtils.encrypt(request.getParameter("customerPhone"), publicKey));  //法人手机号  加密
        trnMap.put("principal", request.getParameter("principal"));     //负责人
        trnMap.put("principalMobile", RSAUtils.encrypt(request.getParameter("principalMobile"), publicKey));  //负责人手机号  加密
        trnMap.put("idCode", RSAUtils.encrypt(request.getParameter("idCode"), publicKey));  //负责人证件号   加密
        trnMap.put("indentityPhoto", request.getParameter("indentityPhoto"));    //负责人证件照 上传正反面两张，以;分割(前面图片处理中有返回)
        trnMap.put("accountCode", RSAUtils.encrypt(request.getParameter("accountCode"), publicKey));      //银行卡号  加密
        trnMap.put("bankId", request.getParameter("bankId"));       //开户银行 ID (取分支行联行号表.xlsx)
        trnMap.put("accountName", request.getParameter("accountName"));   //开户人
        trnMap.put("contactLine", request.getParameter("contactLine"));    // 联行号 (取分支行联行号表.xlsx)
        trnMap.put("bankName", request.getParameter("bankName"));         //银行名称 (取分支行联行号表.xlsx)
        trnMap.put("bankBranchName", request.getParameter("bankBranchName"));     //开户支行名称 (取分支行联行号表.xlsx)
        trnMap.put("bankProvince", request.getParameter("bankProvince"));      //开户支行所在省 (取分支行联行号表.xlsx 中 省份ID)
        trnMap.put("bankCity", request.getParameter("bankCity"));     //开户支行所在市 (取分支行联行号表.xlsx 中 城市ID)
        trnMap.put("idCard", RSAUtils.encrypt(request.getParameter("idCard"), publicKey));    //持卡人身份证号  加密
        trnMap.put("bankTel", RSAUtils.encrypt(request.getParameter("bankTel"), publicKey));    //持卡人手机号  加密
        trnMap.put("address", request.getParameter("address"));    //法人地址
        trnMap.put("bankAddress", request.getParameter("bankAddress"));  //持卡人地址
        trnMap.put("billRate", request.getParameter("billRate"));     //结算费率（信用卡手续费费率 （微信支付宝小额贷记卡 ） （单笔交易≤1000元）） 例 千分之 3 直接传3
        trnMap.put("billRate1", request.getParameter("billRate1"));
        trnMap.put("billRate2", request.getParameter("billRate2"));
        trnMap.put("billRate3", request.getParameter("billRate3"));
        trnMap.put("licensePhoto", request.getParameter("licensePhoto"));
        trnMap.put("businessLicense", request.getParameter("businessLicense"));
        String str = MD5.getSignMsg(trnMap, key);
        String sign = "";
        try {
            sign = MD5.md5(str).toUpperCase();
            // 请求前对加密数据处理替换
            trnMap.put("sign",sign);
            map.put("email", trnMap.get("email"));       //邮箱   加密
            map.put("customerPhone",trnMap.get("customerPhone"));  //法人手机号  加密
            map.put("principalMobile", trnMap.get("principalMobile"));   //负责人手机号  加密
            map.put("idCode", trnMap.get("idCode"));        //负责人证件号   加密
            map.put("accountCode",trnMap.get("accountCode"));  //银行卡号  加密
            map.put("idCard", trnMap.get("idCard"));         //持卡人身份证号  加密
            map.put("bankTel",trnMap.get("bankTel"));       //持卡人手机号  加密
            map.put("sign", sign);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 获取到参数对象的签名信息
     *
     * @param request
     * @param response
     * @return
     */
    public Map<String, String> otherGetSign(HttpServletRequest request, HttpServletResponse response) {
        String imgUrl = "https://sd.96299.com.cn/api/upload/uploadFile";                // 商户进件图片上传请求地址
        String merUrl = "https://sd.96299.com.cn/api/merchant/createMerchant";          // 商户进件请求地址

        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDFf6krDWQUDUzKj+K+WvML2EyZLagKaJ5YTeCoBNhx/WpD1Vh2j/wQ9G3RC/" +
                "tpUcmE7szr/vgdEVHkOfk6mGpeHapS6QE4enJ/CVaTPM573uI8VGWBek9v/E6HaVxRV0Hs8ZsvHAKopqYNDZRKhIrlLUrrkFD2KXJgIiRPQALeMQIDAQAB";
        String key = "adc2fbfb4654ed95b28dfe0a0cb03da6";

        String channelId = "401530011651";
        Map<String, String> map = new HashMap<>();

        // 1、处理图片
        Map<String, String> trnMap = new TreeMap<String, String>();
        trnMap.put("accountType", "2");        //帐户类型 1:企业 ;2:个人或个体 默认2 （有营业执照信息为个体 无营业执照信息为个人）
        trnMap.put("accountTypeT", "3");       //可选标识，当帐户类型为个体时，作为fileImg()图片方法判断使用
//        trnMap = TestImgDemo.fileImg(trnMap, imgUrl, channelId); // 图片处理得到进件请求值返回

        // 2、请求进件处理
        trnMap.put("merchantName", request.getParameter("merchantName"));  //商户名称
        trnMap.put("merchantShortName", request.getParameter("merchantShortName"));    //商户简称
        trnMap.put("industrId", request.getParameter("industrId"));     //行业类别（mcc码）
        trnMap.put("province", request.getParameter("province"));      //省份（地区CODE码）
        trnMap.put("city", request.getParameter("city"));   //城市（地区CODE码）
        trnMap.put("county", request.getParameter("county"));   //城市（地区CODE码）
//        trnMap.put("tel", RSAUtils.encrypt(request.getParameter("tel"), publicKey));     //电话号
        trnMap.put("email", RSAUtils.encrypt(request.getParameter("email"), publicKey));  //邮箱   加密
        trnMap.put("customerPhone", RSAUtils.encrypt(request.getParameter("customerPhone"), publicKey));  //法人手机号  加密
        trnMap.put("principal", request.getParameter("principal"));     //负责人
        trnMap.put("principalMobile", RSAUtils.encrypt(request.getParameter("principalMobile"), publicKey));  //负责人手机号  加密
        trnMap.put("idCode", RSAUtils.encrypt(request.getParameter("idCode"), publicKey));  //负责人证件号   加密
        trnMap.put("indentityPhoto", request.getParameter("indentityPhoto"));    //负责人证件照 上传正反面两张，以;分割(前面图片处理中有返回)
        trnMap.put("accountCode", RSAUtils.encrypt(request.getParameter("accountCode"), publicKey));      //银行卡号  加密
        trnMap.put("bankId", request.getParameter("bankId"));       //开户银行 ID (取分支行联行号表.xlsx)
        trnMap.put("accountName", request.getParameter("accountName"));   //开户人
        trnMap.put("contactLine", request.getParameter("contactLine"));    // 联行号 (取分支行联行号表.xlsx)
        trnMap.put("bankName", request.getParameter("bankName"));         //银行名称 (取分支行联行号表.xlsx)
        trnMap.put("bankBranchName", request.getParameter("bankBranchName"));     //开户支行名称 (取分支行联行号表.xlsx)
        trnMap.put("bankProvince", request.getParameter("bankProvince"));      //开户支行所在省 (取分支行联行号表.xlsx 中 省份ID)
        trnMap.put("bankCity", request.getParameter("bankCity"));     //开户支行所在市 (取分支行联行号表.xlsx 中 城市ID)
        trnMap.put("idCard", RSAUtils.encrypt(request.getParameter("idCard"), publicKey));    //持卡人身份证号  加密
        trnMap.put("bankTel", RSAUtils.encrypt(request.getParameter("bankTel"), publicKey));    //持卡人手机号  加密
        trnMap.put("address", request.getParameter("address"));    //法人地址
        trnMap.put("bankAddress", request.getParameter("bankAddress"));  //持卡人地址
        trnMap.put("billRate", request.getParameter("billRate"));     //结算费率（信用卡手续费费率 （微信支付宝小额贷记卡 ） （单笔交易≤1000元）） 例 千分之 3 直接传3
        trnMap.put("billRate1", "2.8");
        trnMap.put("billRate2", "5.8");
        trnMap.put("billRate3", "5.8");

        trnMap.put("licensePhoto", request.getParameter("licensePhoto"));
        trnMap.put("businessLicense", request.getParameter("businessLicense"));

        String str= MD5.getSignMsg(trnMap, key);
        String sign = "";
        try {
            sign = MD5.md5(str);
            sign = sign.toUpperCase();
            trnMap.put("sign", sign);
            System.out.println("请求上游的参数：" + trnMap);
            String rtnStr = HTTPRequestUtil.formUpload(merUrl, trnMap, channelId);
            //判断上游返回的信息中是否存在个体商户id
            Map<String, Object> stringObjectMap = JsonUtils.jsonToMap(rtnStr);
            logger.info("上游返回结果：" + com.alibaba.fastjson.JSONObject.parse(rtnStr));
            String merchantId = JsonUtils.jsonToMap(stringObjectMap.get("data").toString()).get("merchantId").toString();
            logger.info("商户ID MerchantId：" + merchantId);
            if(!merchantId.equals("") || merchantId != null){
                add(toCustomer(request,merchantId));
            }
        } catch (Exception e) {
            System.out.println("请求上游异常" + e);
        }
        trnMap.put("sign",sign);
        map.put("email", trnMap.get("email"));       //邮箱   加密
        map.put("customerPhone",trnMap.get("customerPhone"));  //法人手机号  加密
        map.put("principalMobile", trnMap.get("principalMobile"));   //负责人手机号  加密
        map.put("idCode", trnMap.get("idCode"));        //负责人证件号   加密
        map.put("accountCode",trnMap.get("accountCode"));  //银行卡号  加密
        map.put("idCard", trnMap.get("idCard"));         //持卡人身份证号  加密
        map.put("bankTel",trnMap.get("bankTel"));       //持卡人手机号  加密
        map.put("sign", sign);
        return map;
    }

    /**
     * 商户进件个体商户信息
     *
     * @param request
     * @param merchantId
     * @return
     */
    private IndividualCustomer toCustomer(HttpServletRequest request, String merchantId) {
        IndividualCustomer customer = new IndividualCustomer();
        customer.setCounty(request.getParameter("county"));
        customer.setTel(request.getParameter("setTel"));
        customer.setProvince( request.getParameter("province"));
        customer.setPrincipalMobile( request.getParameter("principalMobile"));
        customer.setMerchantShortName( request.getParameter("merchantShortName"));
        customer.setMerchantNo(merchantId);
        customer.setPrincipal(request.getParameter("principal"));
        customer.setMerchantName( request.getParameter("merchantName"));
        customer.setIndustrId( request.getParameter("industrId"));
        customer.setIdCode( request.getParameter("idCode"));
        customer.setIdCard( request.getParameter("idCard"));
        customer.setEmail( request.getParameter("email"));
        customer.setCustomerPhone( request.getParameter("customerPhone"));
        customer.setCurrency( request.getParameter("currency"));
        customer.setContactLine( request.getParameter("contactLine"));
        customer.setCity( request.getParameter("city"));
        customer.setBusinessLicense( request.getParameter("businessLicense"));
        customer.setBillRate(request.getParameter("billRate"));
        customer.setBillRate1(request.getParameter("billRate1"));
        customer.setBillRate2(request.getParameter("billRate2"));
        customer.setBillRate3(request.getParameter("billRate3"));
        customer.setBankTel( request.getParameter("bankTel"));
        customer.setBankProvince( request.getParameter("bankProvince"));
        customer.setBankName( request.getParameter("bankName"));
        customer.setBankId( request.getParameter("bankId"));
        customer.setBankCity( request.getParameter("bankCity"));
        customer.setBankBranchName( request.getParameter("bankBranchName"));
        customer.setBankAddress( request.getParameter("bankAddress"));
        customer.setAddress( request.getParameter("address"));
        customer.setAccountName( request.getParameter("accountName"));
        customer.setAccountCode( request.getParameter("accountCode"));
        return customer;
    }


    /**
     * 商户D0开通接口获取签名信息
     *
     * @return
     * @param merchantNo
     */
    public static Map<String,String> openWithDraw(String merchantNo) {
        String key = "adc2fbfb4654ed95b28dfe0a0cb03da6";
        String imgUrl = "https://sd.96299.com.cn/api/upload/uploadFile";                // 商户进件图片上传请求地址
        String merUrl = "https://sd.96299.com.cn/api/payment/openWithDraw";          // 商户DO开通请求接口
        String channelId = "401530011651";
        Map<String, String> map = new HashMap<>();
        // 1、处理图片
        Map<String, String> trnMap = new TreeMap<String, String>();
        // 2、商户D0开通接口
        trnMap.put("DCbillRate", "0");  //储蓄卡费率 例：千3 直接传 3
        trnMap.put("CCbillRate", "0");  //信用卡费率 例：万 1 直接传0.1
        trnMap.put("singleRate", "0");  //	固定金额 单位（分）
        trnMap.put("mch_id",merchantNo);  //商户ID
        String str= MD5.getSignMsg(trnMap, key);
        String sign = "";
        try {
            sign = MD5.md5(str);
            sign = sign.toUpperCase();
            trnMap.put("sign", sign);
            System.out.println("请求上游的参数：" + trnMap);
            logger.info("请求上游的参数：" + trnMap);

            String rtnStr = HTTPRequestUtil.formUpload(merUrl, trnMap, channelId);
            logger.info("上游返回结果：" + JSONObject.parse(rtnStr));
        } catch (Exception e) {
            logger.info("请求上游异常:" + e);
        }
        trnMap.put("sign",sign);
        return trnMap;
    }

    /**
     * 新增商户进件个体商户信息
     *
     * @param customer
     * @return
     */
    public int add(IndividualCustomer customer) {
        customer.setCurrency("RMB");
        customer.setId(new SnowflakeIdUtils().nextId());
        return mapper.add(customer);
    }

    /**
     * 根据个人商户编号查询到对应的个人信息
     *
     * @param merchantNo 个人商户编号
     * @return
     */
    public IndividualCustomer findMerchantNo(String merchantNo) {
        return mapper.findMerchantNo(merchantNo);
    }
}
