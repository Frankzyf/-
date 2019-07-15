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
 <div>
   <button class="layui-btn" id = "addMenu">添加</button>
 </div>
 
 <!-- 表格部分 -->
<table id="demo" lay-filter="test"></table>

<!-- 编辑模态框 -->
<div id="Update" style="display: none;">
<form class="layui-form layui-form-pane" action="${PATH}/role/update" method="POST" id="updateRole">

  <div class="layui-form-item">
    <label class="layui-form-label">角色编号</label>
    <div class="layui-input-inline">
      <input type="text" readonly="readonly" id="roleCode" name="roleCode" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">角色名称</label>
    <div class="layui-input-inline">
      <input type="text" id="roleName" name="roleName" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <button class="layui-btn">提交</button>
  </div>
</form>
</div>

<!-- 添加模态框 -->
<div id="Add" style="display: none;">
<form class="layui-form layui-form-pane" action="${PATH}/role/insert" method="POST" id="registSubmit">

  <div class="layui-form-item">
    <label class="layui-form-label">人员编号</label>
    <div class="layui-input-inline">
      <input type="text" name="roleCode" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">人员名称</label>
    <div class="layui-input-inline">
      <input type="text" name="roleName" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
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
layui.use(['form','jquery','layer',"table"], function(){
  var table = layui.table;
  var form = layui.form;
  var $ = layui.jquery;
  var layer = layui.layer;
  
  //第一个实例
  table.render({
    elem: '#demo'
    ,height: 400
    ,url: '${PATH}/role/selectRole' //数据接口
    ,page: true //开启分页
    ,cols: [[ //表头
      {type:'checkbox', fixed: 'left'}
      ,{field: 'id', title: 'ID',  sort: true}
      ,{field: 'roleCode', title: '角色编号' }
      ,{field: 'roleName', title: '角色名称' }
      ,{fixed: 'right', title:'操作', toolbar: '#barDemo', width:150}
    ]]
  ,page: true//开启分页
  ,limit:10
  ,limits:[10,20,30,50]
  ,id:'testReload'
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
              url:"${PATH}/role/deleteRole?roleCode="+data.roleCode,
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
               url:"${PATH}/role/selectByCode?roleCode="+data.roleCode,
               type:"GET",
               success:function(result){
                  $("#roleCode").val(result.roleCode);
                  $("#roleName").val(result.roleName);
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
		        url:"${PATH}/role/insert",
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
	$("#updateRole").on("submit",function(){
		updatePost();
		event.preventDefault();
	});
	function updatePost(){
		var data = $("#updateRole").serialize();
		  $.ajax({
		        url:"${PATH}/role/update",
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
		})
     });
</script>
</body>
</html>