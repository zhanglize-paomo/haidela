package com.haidela.payment.pay.createmerchant;

import com.haidela.payment.util.HTTPRequestUtil;
import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class TestImgDemo {
	
	/**  请求图片进件处理 
	 * @Title  fileImg
	 * @Description  TODO
	 * @param trnMap
	 * @return
	 */
	public static Map<String, String> fileImg(Map<String, String> trnMap, String imgUrl, String channelId) {
		String licensePhoto = "";
		String mainPhoto = "";
		String accountCodePhoto = "";
		String indentityPhoto = "";
		JSONObject licenseImg = null;
		JSONObject mainImg = null;
		JSONObject accountCodeImg = null;
		JSONObject parseZ = null;
		JSONObject parseF = null;
		JSONObject jsonObject = new JSONObject();
		Map<String, String> trnMapImg = new HashMap<String, String>();
		if ("1".equals(trnMap.get("accountType")) || "3".equals(trnMap.get("accountTypeT"))) {  // 企业和个体户
			// 处理企业图片请求上游上传图片
			trnMapImg.put("img", "http://demo.com/unionPhoto/licensePhoto.jpg");
            String licenseStr = HTTPRequestUtil.formUploadImg(imgUrl, trnMapImg, "", channelId);
            licenseImg = jsonObject.fromObject(licenseStr);
            System.out.println("【商户进件】营业执照图片上传数据响应：" + licenseImg);
            trnMapImg.put("img", "http://demo.com/unionPhoto/mainPhoto.jpg");
            String mainStr = HTTPRequestUtil.formUploadImg(imgUrl, trnMapImg, "", channelId);
            mainImg = jsonObject.fromObject(mainStr);
            System.out.println("【商户进件】门头照图片上传数据响应：" + mainImg);
            trnMapImg.put("img", "http://demo.com/unionPhoto/accountCodePhoto.jpg");
            String accountCodeStr = HTTPRequestUtil.formUploadImg(imgUrl, trnMapImg, "", channelId);
            accountCodeImg = jsonObject.fromObject(accountCodeStr);
            System.out.println("【商户进件】银行卡/开户许可证图片上传数据响应：" + accountCodeImg);     // 个体户时传银行卡正面或者不传，企业传开户许可证图片
            if ("1".equals(licenseImg.get("code") + "") && "1".equals(mainImg.get("code") + "") && "1".equals(accountCodeImg.get("code") + "")) {
            	// 处理上传的营业执照 一张图片
            	String licenseData = licenseImg.get("data") + "";
            	licenseImg = jsonObject.fromObject(licenseData);
            	licensePhoto = licenseImg.get("img_name") + "";
            	trnMap.put("licensePhoto", licensePhoto);
            	
            	// 处理上传的门头照 一张图片
            	String mainData = mainImg.get("data") + "";
            	mainImg = jsonObject.fromObject(mainData);
            	mainPhoto = mainImg.get("img_name") + "";
            	trnMap.put("mainPhoto", mainPhoto);
            	
            	// 处理上传的银行卡/开户许可证照片 一张图片
            	String accountCodeData = accountCodeImg.get("data") + "";
            	accountCodeImg = jsonObject.fromObject(accountCodeData);
            	accountCodePhoto = accountCodeImg.get("img_name") + "";
            	trnMap.put("accountCodePhoto", accountCodePhoto);
            	
            } else {
            	System.out.println("【商户进件】企业图片上传请求失败！");
            }
            
		}
		
		// 处理负责人证件照请求上游上传图片
		trnMapImg.put("img", "http://demo.com/unionPhoto/indentityPhotoImgZ.jpg");
        String imgMsgZ = HTTPRequestUtil.formUploadImg(imgUrl, trnMapImg, "", channelId);
        parseZ = jsonObject.fromObject(imgMsgZ);
        System.out.println("【商户进件】负责人证件照正面图片上传数据响应：" + parseZ);
        trnMapImg.put("img", "http://demo.com/unionPhoto/indentityPhotoImgF.jpg");
        String imgMsgF = HTTPRequestUtil.formUploadImg(imgUrl, trnMapImg, "", channelId);
        parseF = jsonObject.fromObject(imgMsgF);
        System.out.println("【商户进件】负责人证件照背面图片上传数据响应：" + parseF);
        if ("1".equals(parseZ.get("code") + "") && "1".equals(parseF.get("code") + "")) {
        	String dataZ = parseZ.get("data") + "";
        	parseZ = jsonObject.fromObject(dataZ);
        	String img_nameZ = parseZ.get("img_name") + "";
        	String dataF = parseF.get("data") + "";
        	parseF = jsonObject.fromObject(dataF);
        	String img_nameF = parseF.get("img_name") + "";
        	indentityPhoto = img_nameZ + ";" + img_nameF;                     // 负责人证件照 上传正反面两张，以;分割
        	trnMap.put("indentityPhoto", indentityPhoto);
		} else {
			System.out.println("【商户进件】负责人证件照图片上传请求失败！");
		}
        
        if (!"1".equals(trnMap.get("accountType"))) {   // 企业对公结算不上送结算卡正反面
        	// 结算卡正反面上送图片
        	trnMapImg.put("img", "http://demo.com/unionPhoto/bankCardPhotoZ.jpg");
        	imgMsgZ = HTTPRequestUtil.formUploadImg(imgUrl, trnMapImg, "", channelId);
        	parseZ = jsonObject.fromObject(imgMsgZ);
        	System.out.println("【商户进件】结算卡正面图片上传数据响应：" + parseZ);
        	trnMapImg.put("img", "http://demo.com/unionPhoto/bankCardPhotoF.jpg");
        	imgMsgF = HTTPRequestUtil.formUploadImg(imgUrl, trnMapImg, "", channelId);
        	parseF = jsonObject.fromObject(imgMsgF);
        	System.out.println("【商户进件】结算卡反面图片上传数据响应：" + parseF);
        	if ("1".equals(parseZ.get("code") + "") && "1".equals(parseF.get("code") + "")) {
        		String dataZ = parseZ.get("data") + "";
        		parseZ = jsonObject.fromObject(dataZ);
        		String img_nameZ = parseZ.get("img_name") + "";
        		String dataF = parseF.get("data") + "";
        		parseF = jsonObject.fromObject(dataF);
        		String img_nameF = parseF.get("img_name") + "";
        		String otherDoc = img_nameZ + ";" + img_nameF;                     // 其它证件照字段：结算卡上传正反面两张，以;分割
        		trnMap.put("otherDoc", otherDoc);
        	} else {
        		System.out.println("【商户进件】结算卡图片上传请求失败！");
        	}
        }
		
		return trnMap;
	}
	
}
