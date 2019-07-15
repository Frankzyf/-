package com.qdu.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qdu.mapper.MenuMapper;
import com.qdu.model.MenuModel;
import com.qdu.service.MenuService;

@Service
public class MenuServiceImp implements MenuService{
	
	@Autowired
	MenuMapper menuMapper;

	@Override
	public List<MenuModel> getMenu(String userCode) {
		
		return menuMapper.selecMenu(userCode);
	}

	@Override
	public List<MenuModel> selectAll(MenuModel menu,int before, int after) {
		
		return menuMapper.selectAll(menu,before,after);
	}

	@Override
	public int count() {
		return menuMapper.count();
	}

	@Override
	public MenuModel selectByCode(String menuCode) {
		
		return menuMapper.selectByCode(menuCode);
	}

	@Override
	public void insert(MenuModel menuModel) {
		menuMapper.insert(menuModel);
	}

	@Override
	public void delecteMenu(String menuCode) {
		menuMapper.deleteByCode(menuCode);
	}

	@Override
	public void update(MenuModel menuModel) {
		menuMapper.update(menuModel);
	}

}
