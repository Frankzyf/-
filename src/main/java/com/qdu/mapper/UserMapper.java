package com.qdu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qdu.model.MenuModel;
import com.qdu.model.UserModel;

public interface UserMapper {

	//添加用户
	void addUser(UserModel um);
    //查找用户
	UserModel selectUser(UserModel user);
	//查询所有用户
	List<UserModel> selectAll(UserModel user,@Param("before")int before,@Param("after") int after);
	//数量
	int count();
	//删除
	Object deleteByCode(String userCode);
	//更新
	void update(UserModel userModel);
	//按编号查找
	UserModel selectByCode(String userCode);
	//查询全部
	List<UserModel> selectAllUser();

}
