<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% pageContext.setAttribute("PATH", request.getContextPath());%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>商品</title>
  <link rel="stylesheet" href="${PATH}/source/layui/css/layui.css">
</head>
<body>
      <!-- 上部 -->
     <div class="layui-collapse">
		<div class="layui-colla-item">
			<h2 class="layui-colla-title">商品信息-查詢条件</h2>
			<div class="layui-colla-content layui-show">
				<fieldset class="layui-elem-field layui-field-title"
					style="margin-top: 50px">
					<legend>商品信息-查询条件</legend>
					<form class="layui-form">
						<div class="layui-form-item">
							<label class="layui-form-label">商品编号</label>
							<div class="layui-input-inline">
								<input type="text" id="code" placeholder="请输入" autocomplete="off" class="layui-input">
							</div>
							<label class="layui-form-label">商品名称</label>
							<div class="layui-input-inline">
								<input type="text" id="name" placeholder="请输入" autocomplete="off" class="layui-input">
							</div>
							    <label class="layui-form-label"></label> <span> 
								<input type="button" class="layui-btn layui-icon" lay-filter="formDemo" id="addMenu" value="&#xe608;添加" /> 
								<input type="button" class="layui-btn layui-icon"  id="select" value="&#xe615;查找" />
							</span>
						</div>
					</form>
					<div class="layui-form-item">
						<label class="layui-form-label"></label>
						 <a class="layui-btn" href="${PATH}/pro/excle">导出Excel</a>
						 <label class="layui-form-label"></label>
						 <a class="layui-btn" href="${PATH}/pro/model">生成模板</a>
						 <label class="layui-form-label"></label>
						 <a class="layui-btn" id="test9">导入Excel</a>
					</div>
				</fieldset>
			</div>
		</div>
	</div>
 
 <!-- 表格部分 -->
<table id="demo" lay-filter="test"></table>

<!-- 编辑模态框 -->
<div id="Update" style="display: none;">
<form class="layui-form layui-form-pane" action="${PATH}/pro/updateActive" method="POST" id="updateRights">

  <div class="layui-form-item">
    <label class="layui-form-label">商品编号</label>
    <div class="layui-input-inline">
      <input type="text" readonly="readonly" id="pcode" name="code" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">商品名称</label>
    <div class="layui-input-inline">
      <input type="text" id="pname" name="name" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
    </div>
  </div>
    <div class="layui-form-item">
    <label class="layui-form-label">数量</label>
    <div class="layui-input-inline">
      <input type="text" id="sum" name="sum" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
    </div>
  </div>
    <div class="layui-form-item">
    <label class="layui-form-label">费用</label>
    <div class="layui-input-inline">
      <input type="text" id="cost" name="cost" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <button class="layui-btn">提交</button>
  </div>
</form>
</div>

<!-- 添加模态框 -->
<div id="Add" style="display: none;">
<form class="layui-form layui-form-pane" action="${PATH}/com/insert" method="POST" id="registSubmit">

  <div class="layui-form-item">
    <label class="layui-form-label">商品编号</label>
    <div class="layui-input-inline">
      <input type="text" name="code" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">商品名称</label>
    <div class="layui-input-inline">
      <input type="text" name="name" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
    </div>
  </div>
    <div class="layui-form-item">
    <label class="layui-form-label">数量</label>
    <div class="layui-input-inline">
      <input type="text" name="sum" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
    </div>
  </div>
    <div class="layui-form-item">
    <label class="layui-form-label">费用</label>
    <div class="layui-input-inline">
      <input type="text" name="cost" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <button class="layui-btn">提交</button>
  </div>
</form>
</div>
<script type="text/javascript" src="${PATH}/source/js/jquery-3.4.0.min.js"></script>
<script src="${PATH}/source/layui/layui.js"></script>
<script type="text/javascript">


</script>

<script type="text/html" id="barDemo">
  <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
<script>
$(function(){
layui.use('table',function(){
	var table = layui.table;
	  var code = $("#code").val();
	  var name = $("#name").val();

	  table.render({
		    elem: '#demo'
		    ,height: 400
		    ,url: '${PATH}/pro/selectModelPage' //数据接口
		    ,page: true //开启分页
		    ,where:{code: code,name: name}
		    ,cols: [[ //表头
		      {type:'checkbox', fixed: 'left'}
		      ,{field: 'code', title: '商品编号' }
		      ,{field: 'name', title: '商品名称' }
		      ,{field: 'sum', title: '数量' }
		      ,{field: 'cost', title: '费用' }
		      ,{fixed: 'right', title:'操作', toolbar: '#barDemo', width:150}
		    ]]
		  ,page: true//开启分页
		  ,limit:10
		  ,limits:[10,20,30,50]
		  ,id:'testReload'
		  }); 
});
	
});

layui.use(['form','jquery','layer',"table"], function(){
  var table = layui.table;
  var form = layui.form;
  var $ = layui.jquery;
  var layer = layui.layer;

  $("#select").click(function(){
	  var code = $("#code").val();
	  var name = $("#name").val();

	  table.render({
		    elem: '#demo'
		    ,height: 400
		    ,url: '${PATH}/com/selectModelPage' //数据接口
		    ,page: true //开启分页
		    ,where:{code: code,name: name}
		    ,cols: [[ //表头
		      {type:'checkbox', fixed: 'left'}
		      ,{field: 'code', title: '商品编号' }
		      ,{field: 'name', title: '商品名称' }
		      ,{field: 'sum', title: '数量' }
		      ,{field: 'cost', title: '费用' }
		      ,{fixed: 'right', title:'操作', toolbar: '#barDemo', width:150}
		    ]]
		  ,page: true//开启分页
		  ,limit:10
		  ,limits:[10,20,30,50]
		  ,id:'testReload'
		  }); 
	  });
  


//监听工具行
  table.on('tool(test)', function(obj){
      var data = obj.data;
      console.log(obj)
      
      if(obj.event === 'del'){
          layer.confirm('真的删除行么', function(index){
          obj.del();
          layer.close(index);
          $.ajax({
              url:"${PATH}/pro/deleteModel?code="+data.code,
              type:"GET",
              success:function(result){
                   if(result==1){
                       alert("删除成功！");
                       }else{
                       alert("删除失败！")
                           }
                  }           
              });     
        });
          
      } else if(obj.event === 'edit'){
    	layer.open({
    	        title:'编辑',
    	       type:1,
    	       area: ['500px', '300px'],
    	       content:$("#Update")
  	      });
	      $.ajax({
               url:"${PATH}/pro/selectModel?code="+data.code,
               type:"GET",
               success:function(result){
                  $("#pcode").val(result.code);
                  $("#pname").val(result.name);
                  $("#sum").val(result.sum);
                  $("#cost").val(result.cost);
                   }
		      });
      }
    });

  //阻止添加表单提交后跳转页面
  $("#registSubmit").on("submit",function(){
		registPost();
		event.preventDefault();
	});
	function registPost(){
		var data = $("#registSubmit").serialize();
		  $.ajax({
		        url:"${PATH}/pro/insert",
		        type:"POST",
		        data:data,
		        success:function(){
		         alert("添加成功！")
		         layer.close(layer.index);
		         window.location.reload();
		            }
				});
		  }

	 //阻止更新表单提交后跳转页面
	$("#updateRights").on("submit",function(){
		updatePost();
		event.preventDefault();
	});
	function updatePost(){
		var data = $("#updateRights").serialize();
		  $.ajax({
		        url:"${PATH}/pro/updateActive",
		        type:"POST",
		        data:data,
		        success:function(result){
			        if(result==1){
			        	 alert("更改成功！");
				        }
		        
		         layer.close(layer.index);
		         window.location.reload();
		            }
				});
		  }
});



layui.use(['form','jquery','layer'],function(){
	var form = layui.form;
	var $ = layui.jquery;
	var layer = layui.layer;

	//弹出层
	$("#addMenu").on("click",function(){

		layer.open({
               title:'添加菜单',
               type:1,
               area: ['500px', '300px'],
               content:$("#Add")
			});
		})
     });

layui.use('upload', function(){
	  var upload = layui.upload;
	  //执行实例
	  var uploadInst = upload.render({
	    elem: '#test9' //绑定元素
	    ,url: "${PATH}/pro/upload" //上传接口
	    ,accept:'file'
		,exts:'xlsx'
	    ,done: function(res){
	      //上传完毕回调
	    }
	    ,error: function(){
	      //请求异常回调
	    }
	  });
	});
</script>
</body>
</html>