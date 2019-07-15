<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% pageContext.setAttribute("PATH", request.getContextPath());%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>词条检索</title>
 <link rel="stylesheet" href="${PATH}/source/layui/css/layui.css">
</head>
<body>
<fieldset class="layui-elem-field" style="margin:20px;padding:20px">
	<legend>词条检索</legend>
	<div class="layui-field-box">
		<form class="layui-form layui-form-pane" method="post" onsubmit="">
			<div class="layui-form-item">
				<label class="layui-form-label">词条</label>
				<div class="layui-input-inline">
					<input type="text" name="item" class="layui-input" lay-verify="required" placeholder="请输入词条" required
						autocomplete="off">
				</div>
				<input type="button" class="layui-btn" value="检索" lay-submit lay-filter="search">
				<input type="reset" class="layui-btn" value="重置">
			</div>
		</form>
	</div>	

</fieldset>
<fieldset class="layui-elem-field" style="margin:20px;padding:20px">
	<legend>词条解释</legend>
	<div class="layui-field-box">
		<div class="layui-form-item">
			<div class="layui-input-blank">
				<div id="text" style="font-size:20px"></div>
			</div>
		</div>
	</div>
</fieldset>
<script type="text/javascript" src="${PATH}/source/js/jquery-3.4.0.min.js"></script>
<script type="text/javascript" src="${PATH}/source/layui/layui.all.js"></script>
<script type="text/javascript" src="${PATH}/source/js/common.js"></script>
<script type="text/javascript">


formSubmit('/user/item','submit(search)','text',function(data){
		var text = '';
		if(data == ''){
			text='暂无该词条';
		}else{
			text=data;
		}
		$("#text").html(text);		
	})
	
</script>
</body>
</html>