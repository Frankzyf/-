package com.qdu.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qdu.model.MenuModel;
import com.qdu.service.MenuService;
import com.qdu.utils.Layui;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("/menu")
public class MenuController {
	
	@Autowired
	MenuService menuService;
	
	/**
	 * 插入
	 * @param menuModel
	 */
	@RequestMapping("/insert")
	@ResponseBody
	public void insertMenu(MenuModel menuModel) {
		menuService.insert(menuModel);
	}
	
	/**
	 * 删除
	 * @param menuCode
	 */
	@RequestMapping("/deleteMenu")
	@ResponseBody
	public void deletMenu(String menuCode) {
		menuService.delecteMenu(menuCode);
	}
	
	/**
	 * 更新
	 * @param menuModel
	 */
	@RequestMapping("/update")
	@ResponseBody
	public void update(MenuModel menuModel) {
		System.out.println("MenuModel"+menuModel);
		menuService.update(menuModel);
	}
	
	@ResponseBody
	@RequestMapping(value="/selectMenu")
	public Layui selectMenu(@RequestParam("page")int page,@RequestParam("limit")int limit,String menuCode,String menuName) {
		MenuModel menu = new MenuModel();
		menu.setMenuCode(menuCode);
		menu.setMenuName(menuName);
		int before = limit * (page-1);
		int after = page * limit;
		//System.out.println("before:"+before+"after"+after);
		//带参数从数据库里查询出来放到eilist集合里
		List<MenuModel> eilist = menuService.selectAll(menu,before,after);
		int count = menuService.count();
		//将集合变为json格式
//		JSONArray json  = JSONArray.fromObject(eilist);
//		String js = json.toString();
		 
          
		return Layui.data(count, eilist);
	}
    
	/**
	 * 按照code查找菜单
	 * @param menuCode
	 * @return
	 */
	@RequestMapping("/selectByCode")
	@ResponseBody
	public MenuModel selectByCode(String menuCode) {
		
		MenuModel mu = menuService.selectByCode(menuCode);
		return mu;
	}

}
