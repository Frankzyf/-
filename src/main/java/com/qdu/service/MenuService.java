package com.qdu.service;

import java.util.List;

import com.qdu.model.MenuModel;
import com.qdu.model.UserModel;

public interface MenuService {

	
	//1.按照用户Code查询菜单
	List<MenuModel> getMenu(String userCode);

	//2.查询全部菜单
	List<MenuModel> selectAll(MenuModel menu,int before, int after);

	//3. 查询全部记录条数
	int count();
	
	//4.按照编号查询
	MenuModel selectByCode(String menuCode);


    //5.插入新的菜单

	void insert(MenuModel menuModel);
    
	//6.删除菜单
	void delecteMenu(String menuCode);

	
	//7. 更新菜单

	void update(MenuModel menuModel);

}
