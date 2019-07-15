package com.qdu.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qdu.mapper.RoleMapper;
import com.qdu.model.RoleModel;
import com.qdu.service.RoleService;

@Service
public class RoleServiceImp implements RoleService{

	@Autowired
	RoleMapper roleMapper;
	
	@Override
	public List<RoleModel> selectAll(int before, int after) {
		
		return roleMapper.selectAll(before, after);
	}

	@Override
	public int count() {
	
		return roleMapper.count();
	}

	@Override
	public RoleModel selectByCode(String roleCode) {
		return roleMapper.selectByCode(roleCode);
	}

	@Override
	public void insert(RoleModel roleModel) {
		roleMapper.insert(roleModel);
	}

	@Override
	public void delecteRole(String roleCode) {
		roleMapper.deleteByCode(roleCode);
	}

	@Override
	public void update(RoleModel roleModel) {
		roleMapper.update(roleModel);
	}

}
