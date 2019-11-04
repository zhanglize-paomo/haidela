<%@ page import="com.haidela.payment.common.Config" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
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
<script>
//根据操作标记判断是否必填项
function changeRequired() {
	var id = $("#operaFlag").val();
	var form=$("merSettledForm")[0];
	var tagElements=$("form").find("input");
	if(id == 3 || id == 2) {	//查询和修改
	    for (var j = 0; j < tagElements.length; j++){ 
	    	$(tagElements[j]).attr("required",false);
	    }  
		$("#userId").attr("required",true);			//入驻id不能为空
	}else {
		$("#operaFlag").attr("required",true);
		$("#userId").attr("required",true);
		$("#merchantName").attr("required",true);
		$("#shortName").attr("required",true);
		$("#merchantAddress").attr("required",true);
		$("#servicePhone").attr("required",true);
		$("#cardNum").attr("required",true);
		$("#userName").attr("required",true);
		$("#certificateType").attr("required",true);
		$("#certificateNum").attr("required",true);
		$("#mobile").attr("required",true);
	}
}
</script>
</head> 
<body> 
<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
  <div class="container">
  	<a class="navbar-brand" href="../index.html"><strong>首页</strong></a>
  	<a class="navbar-brand"><strong>商户入驻（<s>ie1-9</s>）</strong></a>
  </div>
</nav>

<div class="container-fluid">
	<div class="row">
		<div class="col-md-6 col-md-offset-3">
			<% if(request.getAttribute("errorMsg") != null){%>
			<div class="alert alert-warning" role="alert"><%=request.getAttribute("errorMsg")%></div>
			<%}%>
			<form role="form" action="mersettled.do" method="post" id="merSettledForm" name="merSettledForm">
				<div class="form-group">
					<label for="merchantNo">商户号</label>
					<input type="text" class="form-control" name="merchantNo" id="merchantNo" value="<%=Config.getInstance().getMerchantNo() %>" required />
				</div>
				<div class="form-group">
					<label for="operaFlag">操作标记</label>
					<select  class="form-control" id="operaFlag" name="operaFlag">
						<option value="1">入驻</option>
						<option value="2">修改</option>
						<option value="3">查询</option>
					</select>
				</div>
				<div class="form-group">
					<label for="userId">商户入驻ID</label>
					<input type="text" class="form-control" name="userId" id="userId" required />
				</div>
				<div class="form-group">
					<label for="merchantName">商户名称</label>
					<input type="text" class="form-control" name="merchantName" value="测试商户" id="merchantName" required />
				</div>
				<div class="form-group">
					<label for="shortName">商户简称</label>
					<input type="text" class="form-control" name="shortName" value="测试商户" id="shortName" required />
				</div>
				<div class="form-group">
					<label for="merchantAddress">商户地址</label>
					<input type="text" class="form-control" name="merchantAddress" value="北京" id="merchantAddress" required />
				</div>
				<div class="form-group">
					<label for="servicePhone">客服电话</label>
					<input type="text" class="form-control" value="18011078993" name="servicePhone" id="servicePhone" required />
				</div>
				<div class="form-group">
					<label for="cardNum">银行账号</label>
					<input type="text" class="form-control" value="" name="cardNum" id="cardNum" required />
				</div>
				<div class="form-group">
					<label for="userName">账户名称</label>
					<input type="text" class="form-control" value="" name="userName" id="userName" required />
				</div>
				<div class="form-group">
					<label for="certificateType">证件类型</label>
					<select class="form-control" name="certificateType" id="certificateType">
						<option value="ZR01">身份证</option>	
						<option value="ZR02">临时身份证</option>	
						<option value="ZR03">户口簿</option>	
						<option value="ZR04">军官证</option>	
						<option value="ZR05">警官证</option>	
						<option value="ZR06">士兵证</option>	
						<option value="ZR07">文职干部证</option>	
						<option value="ZR08">外国护照</option>	
						<option value="ZR09">香港通行证</option>	
						<option value="ZR10">澳门通行证</option>	
						<option value="ZR11">台湾通行证或有效旅行证件</option>	
						<option value="ZR12">军官退休证</option>	
						<option value="ZR13">中国护照</option>	
						<option value="ZR14">外国人永久居留证</option>	
						<option value="ZR15">军事学员证</option>	
						<option value="ZR16">离休干部荣誉证</option>	
						<option value="ZR17">边民出入境通行证</option>	
						<option value="ZR18">村民委员会证明</option>	
						<option value="ZR19">学生证</option>	
						<option value="ZR20">其它</option>	
						<option value="ZR21">护照</option>	
						<option value="ZR22">香港居民来往内地通行证</option>	
						<option value="ZR23">澳门居民来往内地通行证</option>	
						<option value="ZR24">台湾同胞来往内地通行证</option>	
					</select>
				</div>
				<div class="form-group">
					<label for="certificateNum">证件号码</label>
					<input type="text" class="form-control" name="certificateNum" id="certificateNum" required />
				</div>
				<div class="form-group">
					<label for="mobile">手机号</label>
					<input type="text" class="form-control" value="" name="mobile" id="mobile" required />
				</div>
				<div class="form-group">
					<label for="rate">签约费率</label>
					<input type="text" class="form-control" value="" name="rate" id="rate" />
				</div>
				<div class="form-group">
					<label for="dfrate">D0代付费率</label>
					<input type="text" class="form-control" name="dfrate" id="dfrate" />
				</div>
				<div class="form-group">
					<label for="category">经营类目</label>
					<input type="text" class="form-control" value="" name="category" id="category" />
				</div>
				<div class="form-group">
					<label for="orgCode">组织机构代码</label>
					<input type="text" class="form-control" name="orgCode" id="orgCode" />
				</div>
				<div class="form-group">
					<label for="contactName">联系人名称</label>
					<input type="text" class="form-control" name="contactName" id="contactName" />
				</div>
				<div class="form-group">
					<label for="contactPhone">联系人电话</label>
					<input type="text" class="form-control" name="contactPhone" id="contactPhone" />
				</div>
				<div class="form-group">
					<label for="contactMobile">联系人手机号</label>
					<input type="text" class="form-control" name="contactMobile" id="contactMobile" />
				</div>
				<div class="form-group">
					<label for="contactEmail">联系人邮箱</label>
					<input type="text" class="form-control" name="contactEmail" id="contactEmail" />
				</div>
				<div class="form-group">
					<label for="ext1">扩展字段1</label>
					<input type="text" class="form-control" name="ext1" id="ext1">
				</div>
				<div class="form-group">
					<label for="ext2">扩展字段2</label>
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
				<button type="submit" onclick="changeRequired();" class="btn btn-primary">提交</button>
			</form>
		</div>
	</div>
</div>

		
</body>
 
</html>