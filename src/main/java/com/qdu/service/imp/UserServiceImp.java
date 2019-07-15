package com.qdu.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.qdu.mapper.UserMapper;
import com.qdu.model.MenuModel;
import com.qdu.model.UserModel;
import com.qdu.service.UserService;

@Service
public class UserServiceImp implements UserService{

	@Autowired
	UserMapper userMapper;

	//添加用户
	@Override
	public void addUser(UserModel um) {
		userMapper.addUser(um);
	}

	
    // 用户登录
	@Override
	public boolean selectUser(UserModel user) {
		UserModel ul = userMapper.selectUser(user);
		//System.out.println(ul);
	    if(!StringUtils.isEmpty(ul)){
	    	return true;
	    }else {
			return false;
	    }	
	}
	
	// 查询全部用户
	@Override
	public List<UserModel> selectAll(int before, int after,UserModel user) {
		
		return userMapper.selectAll(user,before,after);
	}
	
	//数量
	@Override
	public int count() {
		
		return userMapper.count();
	}
	
	
	//删除
	@Override
	public void delecteUser(String userCode) {
		
		userMapper.deleteByCode(userCode);
	}
	
	//更新
	@Override
	public void update(UserModel userModel) {
		userMapper.update(userModel);
	}
	
	//按编号查找
	@Override
	public UserModel selectByCode(String userCode) {
		
		return userMapper.selectByCode(userCode);
	}


	//查询全部用户
	@Override
	public List<UserModel> selectAllUser() {
		
		return userMapper.selectAllUser();
	}


}
