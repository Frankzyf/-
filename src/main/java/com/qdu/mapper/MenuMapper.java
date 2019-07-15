package com.qdu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qdu.model.MenuModel;

public interface MenuMapper {

	int count();
	
	List<MenuModel> selecMenu(@Param("userCode")String userCode);

	List<MenuModel> selectAll(MenuModel menu,@Param("before")int before,@Param("after") int after);

	MenuModel selectByCode(String menuCode);

	void insert(MenuModel menuModel);

	void deleteByCode(String menuCode);

	void update(MenuModel menuModel);

}
