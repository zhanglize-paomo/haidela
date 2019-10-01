package com.haidela.payment.util;

import com.hfb.mer.sdk.secret.CertUtil;
import com.hfb.merchant.pay.util.ParamUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Map;
import java.util.TreeMap;

public class ResponseUtil {
	
	/**
	 * 解析request param到map中
	 * @param request
	 * @return
	 */
	public static TreeMap<String, String> getParamMap(HttpServletRequest request){
		TreeMap<String, String> transMap = new TreeMap<String, String>();
		Enumeration<String> enu = request.getParameterNames();
		while(enu.hasMoreElements()) {
			String name = (String)enu.nextElement();
			String value = request.getParameter(name);
			transMap.put(name, value);
		}
		return transMap;
	}
	
	/**
	 * 解析返回数据
	 * @param response
	 * @return
	 */
	public static Map<String,String> parseResponse(String response){
		//解析返回信息到map中
		Map<String,String> transMap = null;
		try {
			transMap = ParamUtil.getParamsMap(response, "utf-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//获取签名
		String sign = (String) transMap.get("sign");
		sign = sign.replaceAll(" ", "+");
		transMap.remove("sign");
		//验签
		String transData = ParamUtil.getSignMsg(transMap);
		boolean result = false;
		try {
			CertUtil.getInstance().verify(transData, sign);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(!result){
			transMap.clear();
			transMap.put("tranData", transData);
			transMap.put("sign", sign);
			transMap.put("msg", "验签失败");
		}
		return transMap;
	}

}
