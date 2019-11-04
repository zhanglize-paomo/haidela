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
  	<a class="navbar-brand"><strong>统一交易结果查询（<s>ie1-9</s>）</strong></a>
  </div>
</nav>

<div class="container-fluid">
	<div class="row">
		<div class="col-md-6 col-md-offset-3">
			<% if(request.getAttribute("errorMsg") != null){%>
			<div class="alert alert-warning" role="alert"><%=request.getAttribute("errorMsg")%></div>
			<%}%>
			<form role="form" action="unifyQuery.do" method="post"  name="refundForm">
				<div class="form-group">
					<label for="merchantNo">商户号</label>
					<input type="text" class="form-control" name="merchantNo" id="merchantNo" value="<%=Config.getInstance().getMerchantNo() %>" required />
				</div>
				<div class="form-group">
					<label for="tranSerialNum">原交易流水号</label>
					<input type="text" class="form-control" name="tranSerialNum" id="tranSerialNum" required />
				</div>
				<div class="form-group">
					<label for="remark">备注</label>
					<input type="text" class="form-control" name="remark" id="remark" />
				</div>
				<div class="form-group">
					<label for="YUL1">预留字段1</label>
					<input type="text" class="form-control" name="YUL1" id="YUL1">
				</div>
				<button type="submit" class="btn btn-primary">提交</button>
			</form>
		</div>
	</div>
</div>

		
</body>
 
</html>