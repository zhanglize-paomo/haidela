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
  	<a class="navbar-brand"><strong>条码支付（<s>ie1-9</s>）</strong></a>
  </div>
</nav>

<div class="container-fluid">
	<div class="row">
		<div class="col-md-6 col-md-offset-3">
			<% if(request.getAttribute("errorMsg") != null){%>
			<div class="alert alert-warning" role="alert"><%=request.getAttribute("errorMsg")%></div>
			<%}%>
			<form role="form" action="barcodepay.do" method="post"  name="barCodeForm">
				<div class="form-group">
					<label for="merchantNo">商户号</label>
					<input type="text" class="form-control" name="merchantNo" id="merchantNo" value="<%=Config.getInstance().getMerchantNo() %>" required />
				</div>
				<div class="form-group">
					<label for="amount">交易金额</label>
					<input type="text" class="form-control" name="amount" id="amount" value="10000" required />
				</div>
				<div class="form-group">
					<label for="notifyUrl">结果通知地址</label>
					<input type="text" class="form-control" name="notifyUrl" value="<%=Config.getInstance().getNotifyUrl() %>" id="notifyUrl" required />
				</div>
				<div class="form-group">
					<label for="bindId">入驻ID</label>
					<input type="text" class="form-control" name="bindId" id="bindId" value="<%=Config.getConfig().getBindId() %>" required />
				</div>
				<div class="form-group">
					<%--@declare id="scene"--%><label for="scene">支付场景</label>
					<select  class="form-control" name="scene">
						<option value="1">条码支付</option>
						<option value="2">声波支付</option>
					</select>
				</div>
				<div class="form-group">
					<%--@declare id="paytype"--%><label for="payType">支付方式</label>
					<select  class="form-control" name="payType">
						<option value="5">微信条码支付</option>
						<option value="6">支付宝条码支付</option>
						<option value="11">QQ钱包条码支付</option>
						<option value="16">京东扫码支付</option>
						<option value="20">百度钱包条码支付</option>
						<option value="21">银联条码支付</option>
					</select>
				</div>
				<div class="form-group">
					<label for="authCode">授权码</label>
					<input type="text" class="form-control" name="authCode" id="authCode" required />
				</div>
				<div class="form-group">
					<label for="remark">付款摘要</label>
					<input type="text" class="form-control" value="摘要" name="remark" id="remark" required />
				</div>
				
				<div class="form-group">
					<label for="bizType">业务代码</label>
					<input type="text" class="form-control" value="01" name="bizType" id="bizType" required />
				</div>
				<div class="form-group">
					<label for="goodsName">商品名称</label>
					<input type="text" class="form-control" name="goodsName" id="goodsName" value="测试商品名称" required />
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
					<input type="text" class="form-control" name="buyerName" value="李四" id="buyerName" required />
				</div>
				<div class="form-group">
					<label for="contact">买家联系方式</label>
					<input type="text" class="form-control" name="contact" id="contact" />
				</div>
				<div class="form-group">
					<label for="buyerId">买家ID</label>
					<input type="text" class="form-control" value="2345678" name="buyerId" id="buyerId" required />
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
					<label for="YUL1">预留字段1</label>
					<input type="text" class="form-control" name="YUL1" id="YUL1">
				</div>
				<div class="form-group">
					<label for="YUL2">预留字段2</label>
					<input type="text" class="form-control" name="YUL2" id="YUL2">
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