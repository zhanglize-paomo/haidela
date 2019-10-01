package com.haidela.payment;

import com.hfb.mer.sdk.secret.CertUtil;
import com.hfb.merchant.pay.util.ParamUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.TreeMap;

/**
 * 统一交易异步通知
 * 
 * @author HFB
 *
 */
public class UnifyNotifyServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
//	private static final Logger logger = Logger.getLogger(UnifyNotifyServlet.class);

	private static final String TAG = "【统一支付商户系统demo】-{统一交易异步通知}-";

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		try {
			TreeMap<String, String> transMap = new TreeMap<String, String>();
			Enumeration<String> enu = request.getParameterNames();
			String t = null;
			while (enu.hasMoreElements()) {
				t = enu.nextElement();
				transMap.put(t, request.getParameter(t));
			}
//			logger.info(TAG + "返回数据：" + transMap);
			String merchantNo = (String) transMap.get("merchantNo");
			// 获取签名
			String sign = (String) transMap.get("sign");
			sign = sign.replaceAll(" ", "+");
			transMap.remove("sign");
			// 验签
			String transData = ParamUtil.getSignMsg(transMap);
			boolean result = false;
			try {
				CertUtil.getInstance().verify(transData, sign);
				result = true;
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (!result) {
//				logger.info(TAG + "商户编号为:" + merchantNo + "验签失败");
				throw new Exception("商户编号为:" + merchantNo + "验签失败");
			}
//			logger.info(TAG + "商户编号为:" + merchantNo + "验签成功");
			
		} catch (Exception e) {
//			logger.info(TAG + "处理异常", e);
		}
	}
}
