package com.qdu.service;

import java.util.List;

import com.qdu.model.RightsModel;
import com.qdu.model.RoleModel;

public interface RightsService {

	//1.查询全部
	List<RightsModel> selectAll(RightsModel rr, int before, int after);

	//2.查询全部记录条数
	int count();
	
	//3.按照编号查询
	RightsModel selectByCode(RightsModel ri);


    
    //4.插入
	void insert(RightsModel rightsModel);
    
	//5.删除
	void delecteRole(String roleCode, int id);

	
	//6. 更新

	void update(RightsModel rightsModel);
}
