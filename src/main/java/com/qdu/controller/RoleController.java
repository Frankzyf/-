package com.qdu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qdu.model.RoleModel;
import com.qdu.service.RoleService;
import com.qdu.utils.Layui;

@Controller
@RequestMapping("/role")
public class RoleController {
	
	@Autowired
	RoleService roleService;
	
	//增加
	@RequestMapping("/insert")
	@ResponseBody
	public void insertMenu(RoleModel roleCode) {
		roleService.insert(roleCode);
	}
	
	//删除
	@RequestMapping("/deleteRole")
	@ResponseBody
	public void deleteRole(String roleCode) {
		roleService.delecteRole(roleCode);
	}
	
	//修改
	@RequestMapping("/update")
	@ResponseBody
	public void update(RoleModel roleCode) {
		System.out.println("MenuModel"+roleCode);
		roleService.update(roleCode);
	}
	
	//查找
	@RequestMapping(value="/selectRole")
	@ResponseBody
	public Layui selectMenu(@RequestParam("page")int page,@RequestParam("limit")int limit) {
		
		int before = limit * (page-1);
		int after = page * limit;

		List<RoleModel> eilist = roleService.selectAll(before,after);
		int count = roleService.count();

		return Layui.data(count, eilist);
	}
	
	//按照编号查找
	@RequestMapping("/selectByCode")
	@ResponseBody
	public RoleModel selectByCode(String roleCode) {
		
		RoleModel rm = roleService.selectByCode(roleCode);
		return rm;
	}
	

}
