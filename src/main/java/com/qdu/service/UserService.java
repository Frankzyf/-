package com.qdu.service;

import java.util.List;

import com.qdu.model.MenuModel;
import com.qdu.model.UserModel;

public interface UserService {

	//1、添加用户
	void addUser(UserModel UserModel);
    //2.用户登录
	boolean selectUser(UserModel user);
	//3.查询全部用户
	List<UserModel> selectAll(int before, int after, UserModel user);
	List<UserModel> selectAllUser();
	//4.查询数量
	int count();
	//5.删除
	void delecteUser(String userCode);
	//6.更新
	void update(UserModel userModel);
	//7.按编号查询
	UserModel selectByCode(String userCode);

}
