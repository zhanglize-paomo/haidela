<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="com.haidela.payment.common.Config"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head> 
<meta http-equiv="Content-Type"	content="text/html; charset=utf-8" />
<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
<script src="http://cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<script src="http://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<title>合付宝支付-商户demo</title> 
<style type="text/css">
	body { padding-top: 70px; }
</style>

</head> 
<body> 
<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
  <div class="container">
  	<a class="navbar-brand" href="../index.html"><strong>首页</strong></a>
  	<a class="navbar-brand"><strong>统一支付（<s>ie1-9</s>）</strong></a>
  </div>
</nav>

<div class="container-fluid">
	<div class="row">
		<div class="col-md-6 col-md-offset-3">
			<% if(request.getAttribute("errorMsg") != null){%>
			<div class="alert alert-warning" role="alert"><%=request.getAttribute("errorMsg")%></div>
			<%}%>
			<form role="form" action="unifyPay.do" method="post"  name="scanCodeForm">
				<div class="form-group">
					<label for="merchantNo">商户号</label>
					<input type="text" class="form-control" name="merchantNo" id="merchantNo" value="<%=Config.getInstance().getMerchantNo() %>" required />
				</div>
				<div class="form-group">
					<label for="amount">交易金额(<font style="color: red">单位为分请输入整数</font>)</label>
					<input type="text" class="form-control" name="amount" id="amount" value="10" required />
				</div>
				<div class="form-group">
					<%--@declare id="paytype"--%><label for=payType>支付方式</label>
					<select  class="form-control" name="payType">
						<option value="1">微信扫码支付(1)</option>
						<option value="2">支付宝扫码支付(2)</option>
						<option value="3">微信公众号支付(3)</option>
						<option value="4">支付宝服务窗支付(4)</option>
						<option value="7">QQ钱包扫码支付(7)</option>
						<option value="8">微信WAP/H5支付(8)</option>
						<option value="9">支付宝WAP/H5支付(9)</option>
						<option value="10">微信APP支付(10)</option>
						<option value="11">QQ钱包条码支付(11)</option>
						<option value="12">支付宝APP支付(12)</option>
						<option value="13">QQ钱包WAP/H5支付(13)</option>
						<option value="14">QQ钱包公众号支付(14)</option>
						<option value="15">银联钱包支付(15)</option>
						<option value="16">京东扫码支付(16)</option>
						<option value="18">翼支付JS支付(18)</option>
						<option value="19">百度钱包扫码(19)</option>
						<option value="22">一码多付(22)</option>
						<option value="23">刷卡支付(23)</option>
						<option value="24">网关支付(24)</option>
						<option value="25">网关WAP/H5支付(25)</option>
						<option selected="selected" value="17">京东WAP/H5支付(17)</option>

					</select>
				</div>
				<div class="form-group">
					<label for="notifyUrl">结果通知地址</label>
					<input type="text" class="form-control" name="notifyUrl" value="<%=Config.getConfig().getNotifyUrl() %>" id="notifyUrl" required />
				</div>
				<div class="form-group">
					<label for="bindId">入驻ID</label>
					<!--<input type="text" class="form-control" name="bindId" value="<%=Config.getConfig().getBindId() %>" id="bindId" required />-->
					<input type="text" class="form-control" name="bindId" value="" id="bindId" />
				</div>
				<div class="form-group">
					<label for="remark">付款摘要</label>
					<input type="text" class="form-control" name="remark" value="摘要" id="remark" required />
				</div>
				
				<div class="form-group">
					<label for="bizType">业务代码</label>
					<input type="text" class="form-control" value="01" name="bizType" id="bizType" required />
				</div>
				<div class="form-group">
					<label for="goodsName">商品名称</label>
					<input type="text" class="form-control" value="测试商品名称" name="goodsName" id="goodsName" required />
				</div>
				<div class="form-group">
					<label for="goodsInfo">商品信息</label>
					<input type="text" class="form-control" name="goodsInfo" id="goodsInfo" />
				</div>
				<div class="form-group">
					<label for="goodsNum">商品数量</label>
					<input type="text" class="form-control" name="goodsNum" id="goodsNum" />
				</div>
				<div class="form-group">
					<label for="buyerName">买家姓名</label>
					<input type="text" class="form-control" name="buyerName" id="buyerName" value="张三" required />
				</div>
				<div class="form-group">
					<label for="contact">买家联系方式</label>
					<input type="text" class="form-control" name="contact" id="contact" />
				</div>
				<div class="form-group">
					<label for="buyerId">买家ID</label>
					<input type="text" class="form-control" name="buyerId" value="1234567" id="buyerId" required />
				</div>
				<div class="form-group">
					<label for="buyerId">买家ID</label>
					<input type="text" class="form-control" name="buyerId" value="1234567" id="buyerId" required />
				</div>
				<div class="form-group">
					<%--@declare id="bankid"--%><label for="bankId">银行编号(<font style="color: red">支付方式是网关/网关H5时必输</font>)</label>
					<select  class="form-control" name="bankId">
						<option value="">请选择</option>
						<option value="01020000">工商银行</option>
						<option value="01050000">建设银行</option>
						<option value="01030000">农业银行</option>
						<option value="03080000">招商银行</option>
						<option value="03010000">交通银行</option>
						<option value="01040000">中国银行</option>
						<option value="03030000">光大银行</option>
						<option value="03050000">民生银行</option>
						<option value="03020000">中信银行</option>
						<option value="03060000">广发银行</option>
						<option value="03100000">浦发银行</option>
						<option value="04100000">平安银行</option>
						<option value="03040000">华夏银行</option>
						<option value="04083320">宁波银行</option>
						<option value="04012900">上海银行</option>
						<option value="01000000">中国邮储银行</option>
						<option value="04243010">南京银行</option>
						<option value="65012900">上海农商行</option>
						<option value="03170000">渤海银行</option>
						<option value="64296510">成都银行</option>
						<option value="04031000">北京银行</option>
					</select>
				</div>
				
				<div class="form-group">
					<%--@declare id="cardtype"--%><label for="cardType">支付卡种(<font style="color: red">支付方式是网关/网关H5时必输</font>)</label>
						<option value="">请选择</option>
					<select class="form-control" name="cardType" required>
						<option value="01">借记卡</option>
						<option value="02">贷记卡</option>
						<option value="04">混合</option>
					</select>
				</div>
				
				<div class="form-group">
					<label for="valid">订单有效时间</label>
					<input type="text" class="form-control" name="valid" id="valid">
				</div>
				<div class="form-group">
					<label for="referer">支付网址</label>
					<input type="text" class="form-control" name="referer" id="referer">
				</div>
				<div class="form-group">
					<label for="ext1">扩展字段1</label>
					<input type="text" class="form-control" name="ext1" id="ext1">
				</div>
				<div class="form-group">
					<label for="remark">扩展字段2</label>
					<input type="text" class="form-control" name="ext2" id="ext2">
				</div>
				<div class="form-group">
					<label for="YUL1">前台通知地址</label>
					<input type="text" class="form-control" name="YUL1" id="YUL1" value="http://localhost:8080/hfb-merchant-pay-demo-java/returnPage.html">
				</div>
				<div class="form-group">
					<label for="YUL2">预留字段2</label>
					<input type="text" class="form-control" name="YUL2" id="YUL2" value="">
				</div>
				<div class="form-group">
					<label for="YUL3">预留字段3</label>
					<input type="text" class="form-control" name="YUL3" id="YUL3">
				</div>
				<button type="submit" class="btn btn-primary">提交</button>
			</form>
		</div>
	</div>
</div>

		
</body>
 
</html>
