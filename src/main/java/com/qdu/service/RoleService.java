package com.qdu.service;

import java.util.List;

import com.qdu.model.RoleModel;

public interface RoleService {

	
	//1.查询全部
	List<RoleModel> selectAll(int before, int after);

	//2.查询全部记录条数
	int count();
	
	//3.按照编号查询
	RoleModel selectByCode(String roleCode);


    //4.插入

	void insert(RoleModel roleModel);
    
	//5.删除
	void delecteRole(String roleCode);

	
	//6. 更新

	void update(RoleModel roleModel);
}
