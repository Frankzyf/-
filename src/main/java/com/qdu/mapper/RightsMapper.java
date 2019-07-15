package com.qdu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qdu.model.RightsModel;

public interface RightsMapper {

	int count();

	List<RightsModel> selectAll(RightsModel rr, @Param("before")int before,@Param("after") int after);

	RightsModel selectByCode(RightsModel ri);

	void insert(RightsModel rightsModel);

	void deleteByCode(String roleCode, int id);

	void update(RightsModel rightsModel);
}
