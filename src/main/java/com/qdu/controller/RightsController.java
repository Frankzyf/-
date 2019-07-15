package com.qdu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qdu.model.RightsModel;
import com.qdu.service.RightsService;
import com.qdu.utils.Layui;

@Controller
@RequestMapping("/rights")
public class RightsController {
	
	@Autowired
	RightsService rightService;
	
	   //增加
		@RequestMapping("/insert")
		@ResponseBody
		public void insertMenu(RightsModel rightsModel) {
			rightService.insert(rightsModel);
		}
		
		//删除
		@RequestMapping("/deleteRights")
		@ResponseBody
		public void deleteRole(String roleCode,int id) {
			System.out.println("id"+id);
			rightService.delecteRole(roleCode,id);
		}
		
		//修改
		@RequestMapping("/update")
		@ResponseBody
		public void update(RightsModel rightsModel) {
			System.out.println("rightsModel"+rightsModel);
			rightService.update(rightsModel);
		}
		
		//查找
		@RequestMapping(value="/selectRights")
		@ResponseBody
		public Layui selectMenu(@RequestParam("page")int page,@RequestParam("limit")int limit,String roleCode,String menuCode) {
			System.out.println(roleCode+menuCode);
			RightsModel rr = new RightsModel();
			rr.setRoleCode(roleCode);
			rr.setMenuCode(menuCode);
			
			int before = limit * (page-1);
			int after = page * limit;

			List<RightsModel> eilist = rightService.selectAll(rr,before,after);
			int count = rightService.count();

			return Layui.data(count, eilist);
		}
		
		//按照编号查找
		@RequestMapping("/selectByCode")
		@ResponseBody
		public RightsModel selectByCode(String roleCode,String menuCode) {
			RightsModel ri = new RightsModel();
			ri.setRoleCode(roleCode);
			ri.setMenuCode(menuCode);
			RightsModel rm = rightService.selectByCode(ri);
			return rm;
		}
		

}
