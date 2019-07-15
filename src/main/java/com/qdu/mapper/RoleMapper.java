package com.qdu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qdu.model.RoleModel;

public interface RoleMapper {

	int count();

	List<RoleModel> selectAll(@Param("before")int before,@Param("after") int after);

	RoleModel selectByCode(String roleCode);

	void insert(RoleModel roleModel);

	void deleteByCode(String roleCode);

	void update(RoleModel roleModel);
}
