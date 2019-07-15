<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% pageContext.setAttribute("PATH", request.getContextPath());%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>菜单</title>
  <link rel="stylesheet" href="${PATH}/source/layui/css/layui.css">
</head>
<body>
      <!-- 上部 -->
     <div class="layui-collapse">
		<div class="layui-colla-item">
			<h2 class="layui-colla-title">权限信息-查詢条件</h2>
			<div class="layui-colla-content layui-show">
				<fieldset class="layui-elem-field layui-field-title"
					style="margin-top: 50px">
					<legend>权限信息-查询条件</legend>
					<form class="layui-form">
						<div class="layui-form-item">
							<label class="layui-form-label">角色编号</label>
							<div class="layui-input-inline">
								<input type="text" id="code" placeholder="请输入角色编号" autocomplete="off" class="layui-input">
							</div>
							<label class="layui-form-label">菜单编号</label>
							<div class="layui-input-inline">
								<input type="text" id="name" placeholder="请输入菜单编号" autocomplete="off" class="layui-input">
							</div>
							    <label class="layui-form-label"></label> <span> 
								<input type="button" class="layui-btn layui-icon" lay-filter="formDemo" id="addMenu" value="&#xe608;添加" /> 
								<input type="button" class="layui-btn layui-icon"  id="select" value="&#xe615;查找" />
							</span>
						</div>
					</form>
					<div class="layui-form-item">
						<label class="layui-form-label"></label> 
						<input type="button" class="layui-btn" value="导出Excel" onclick="exportExcel()" /> 
					</div>
				</fieldset>
			</div>
		</div>
	</div>
 
 <!-- 表格部分 -->
<table id="demo" lay-filter="test"></table>

<!-- 编辑模态框 -->
<div id="Update" style="display: none;">
<form class="layui-form layui-form-pane" action="${PATH}/rights/update" method="POST" id="updateRights">

  <div class="layui-form-item">
    <label class="layui-form-label">角色编号</label>
    <div class="layui-input-inline">
      <input type="text" readonly="readonly" id="roleCodeA" name="roleCode" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">菜单编号</label>
    <div class="layui-input-inline">
      <input type="text" id="menuCodeA" name="menuCode" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
    </div>
  </div>

  <div class="layui-form-item">
    <button class="layui-btn">提交</button>
  </div>
</form>
</div>

<!-- 添加模态框 -->
<div id="Add" style="display: none;">
<form class="layui-form layui-form-pane" action="${PATH}/rights/insert" method="POST" id="registSubmit">

  <div class="layui-form-item">
    <label class="layui-form-label">角色编号</label>
    <div class="layui-input-inline">
      <input type="text" name="roleCode" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">菜单编号</label>
    <div class="layui-input-inline">
      <input type="text" name="menuCode" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
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
	  var roleCode = $("#code").val();
	  var menuCode = $("#name").val();

	  table.render({
		    elem: '#demo'
		    ,height: 400
		    ,url: '${PATH}/rights/selectRights' //数据接口
		    ,page: true //开启分页
		    ,where:{roleCode: roleCode,menuCode: menuCode}
		    ,cols: [[ //表头
		      {type:'checkbox', fixed: 'left'}
		      ,{field: 'roleCode', title: '角色编号' }
		      ,{field: 'menuCode', title: '菜单编号' }
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
	  var roleCode = $("#code").val();
	  var menuCode = $("#name").val();
	  table.render({
		    elem: '#demo'
		    ,height: 400
		    ,url: '${PATH}/rights/selectRights' //数据接口
		    ,page: true //开启分页
		    ,where:{roleCode: roleCode,menuCode: menuCode}
		    ,cols: [[ //表头
		      {type:'checkbox', fixed: 'left'}
		      ,{field: 'roleCode', title: '角色编号' }
		      ,{field: 'menuCode', title: '菜单编号' }
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
              url:"${PATH}/rights/deleteRights?roleCode="+data.roleCode+"&id="+data.id,
              type:"GET",
              success:function(){
                   alert("删除成功！");
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
               url:"${PATH}/rights/selectByCode?roleCode="+data.roleCode+"&menuCode="+data.menuCode,
               type:"GET",
               success:function(result){
                  $("#roleCodeA").val(result.roleCode);
                  $("#menuCodeA").val(result.menuCode);
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
		alert(data);
		  $.ajax({
		        url:"${PATH}/rights/insert",
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
		alert(data);
		  $.ajax({
		        url:"${PATH}/rights/update",
		        type:"POST",
		        data:data,
		        success:function(){
		         alert("更改成功！")
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
		});
     });
</script>
</body>
</html>