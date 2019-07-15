<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	pageContext.setAttribute("PATH", request.getContextPath());
%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="${PATH}/source/layui/css/layui.css" />

<meta charset="UTF-8">
<title>后台管理系统</title>
</head>
<body>
	<div class="layui-layout layui-layout-admin">
		<div class="layui-header">
			<div class="layui-logo">后台管理系统</div>
			<!-- 头部区域（可配合layui已有的水平导航） -->
			<ul class="layui-nav layui-layout-left">
				<li class="layui-nav-item"><a >控制台</a></li>
				<li class="layui-nav-item"><a >用户</a></li>
				<li class="layui-nav-item"><a href="javascript:;">其它功能</a>
					<dl class="layui-nav-child">
						<dd>
							<a onclick="getUrl(this)" dizhi="http://localhost:8081/war/page/chart">商品图片</a>
						</dd>
						<dd>
							<a onclick="getUrl(this)" dizhi="http://localhost:8081/war/user/toBaiKe">爬虫爬爬</a>
						</dd>
					</dl></li>
			</ul>
			<ul class="layui-nav layui-layout-right">
				<li class="layui-nav-item"><a href="javascript:;"> <img
						src="http://t.cn/RCzsdCq" class="layui-nav-img"> 贤心
				</a>
					<dl class="layui-nav-child">
						<dd>
							<a id="sendEmail">发送邮件</a>
						</dd>
						<dd>
							<a href="">安全设置</a>
						</dd>
					</dl></li>
				<li class="layui-nav-item"><a href="${PATH}/page/login">退了</a></li>
			</ul>
		</div>

		<div class="layui-side layui-bg-black">
			<div class="layui-side-scroll">
				<!-- 左侧导航区域（可配合layui已有的垂直导航） -->

				<ul class="layui-nav layui-nav-tree" lay-filter="test">

					<c:forEach items="${MenuModel}" var="list">
						<li class="layui-nav-item layui-nav-itemed"><a class=""
							href="javascript:;"> ${list.menuName}</a>
							<dl class="layui-nav-child">
								<c:forEach items="${list.child}" var="ch">
									<dd>
										<a onclick="getUrl(this)" dizhi="${ch.menuUrl}" id="bbb">${ch.menuName}</a>
									</dd>
								</c:forEach>
							</dl></li>
					</c:forEach>
				</ul>
			</div>
		</div>

		<div class="layui-body">
			<div style="padding: 5px; height: 100%">
				<!-- 内容主体区域 -->
				<iframe id="pannel" src="http://localhost:8081/war/page/chart" width="100%"
					height="100%" frameborder="0"></iframe>
			</div>
		</div>

		<div class="layui-footer">
			<!-- 底部固定区域 -->
			© layui.com - 欢迎使用！
		</div>
	</div>
	
	<!-- 发送邮件模态框 -->
<div id="Email" style="display: none;">
<form class="layui-form layui-form-pane" action="${PATH}/email/send" method="POST" id="registSubmit">

  <div class="layui-form-item">
    <label class="layui-form-label">收件人：</label>
    <div class="layui-input-block">
      <input type="text" name="to" lay-verify="title" autocomplete="off" placeholder="请输入标题" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">主题</label>
    <div class="layui-input-block">
      <input type="text" name="theme" lay-verify="title" autocomplete="off" placeholder="请输入标题" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item layui-form-text">
    <label class="layui-form-label">正文</label>
    <div class="layui-input-block">
      <textarea placeholder="请输入内容" class="layui-textarea" name="content"></textarea>
    </div>
  </div>
  <div class="layui-form-item">
    <button class="layui-btn">提交</button>
  </div>
</form>
</div>

	<script src="${PATH}/source/layui/layui.js" type="text/javascript"></script>
	<script type="text/javascript"
		src="${PATH}/source/js/jquery-3.4.0.min.js"></script>
	<script type="text/javascript">
		layui.use('element', function() {
			var $ = layui.jquery, element = layui.element; //Tab的切换功能，切换事件监听等，需要依赖element模块  
		});
		layui.use(['form','layer'],function(){
			var form = layui.form;
			var layer = layui.layer;

			//弹出层
			$("#sendEmail").on("click",function(){

				layer.open({
		               title:'发送邮件',
		               type:1,
		               area: ['500px', '500px'],
		               content:$("#Email")
					});
				});
			});

		  $("#registSubmit").on("submit",function(){
				registPost();
				event.preventDefault();
			});
			function registPost(){
				var data = $("#registSubmit").serialize();
				  $.ajax({
				        url:"${PATH}/email/send",
				        type:"POST",
				        data:data,
				        success:function(){
				         alert("发送成功！")
				         layer.close(layer.index);
				         window.location.reload();
				            }
						});
				  }
				
	</script>
	<script type="text/javascript">
      function getUrl(url){
          var u = $(url).attr("dizhi");
          $("#pannel").attr("src",u);
          }
	</script>
</body>
</html>