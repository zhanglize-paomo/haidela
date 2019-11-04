package com.haidela.payment.common;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class Config {
	/**
	 * 统一下的请求地址
	 */
	private String channelNo;
	private String version;
	private String merchantNo;
	private String paygateReqUrl; //网关地址paygate_req_url
	private String paygateQueryUrl; //网关查询地址paygate_query_url
	private String notifyUrl; // 通知地址
	private String bindId; // 商户入住ID
	
	
	private static Config config = new Config();
	
	private Config(){
		try {
			PropertiesConfiguration config = new PropertiesConfiguration("serviceconfig.properties");
			this.channelNo = config.getString("channelNo");
			this.version = config.getString("version");
			this.merchantNo = config.getString("merchantNo");
			this.paygateReqUrl = config.getString("paygate_req_url");
			this.paygateQueryUrl = config.getString("paygate_query_url");
			this.notifyUrl = config.getString("notify_url");
			this.bindId = config.getString("bindId");
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
	}
	public String getPaygateQueryUrl() {
		return paygateQueryUrl;
	}
	
	public void setPaygateQueryUrl(String paygateQueryUrl) {
		this.paygateQueryUrl = paygateQueryUrl;
	}

	public static Config getInstance(){
		return config;
	}

	public String getChannelNo() {
		return channelNo;
	}

	public void setChannelNo(String channelNo) {
		this.channelNo = channelNo;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	public static Config getConfig() {
		return config;
	}

	public static void setConfig(Config config) {
		Config.config = config;
	}

	public String getPaygateReqUrl() {
		return paygateReqUrl;
	}

	public void setPaygateReqUrl(String paygateReqUrl) {
		this.paygateReqUrl = paygateReqUrl;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	public String getBindId() {
		return bindId;
	}

	public void setBindId(String bindId) {
		this.bindId = bindId;
	}
	
}
