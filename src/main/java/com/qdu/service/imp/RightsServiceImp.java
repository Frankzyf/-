package com.qdu.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qdu.mapper.RightsMapper;
import com.qdu.model.RightsModel;
import com.qdu.service.RightsService;

@Service
public class RightsServiceImp implements RightsService{
	
	@Autowired
	RightsMapper rightsMapper;

	@Override
	public List<RightsModel> selectAll(RightsModel rr,int before, int after) {
		
		return rightsMapper.selectAll(rr,before, after);
	}

	@Override
	public int count() {
		
		return rightsMapper.count();
	}

	@Override
	public RightsModel selectByCode(RightsModel ri) {
		return rightsMapper.selectByCode(ri);
	}

	@Override
	public void insert(RightsModel rightsModel) {
		rightsMapper.insert(rightsModel);
	}

	@Override
	public void delecteRole(String roleCode,int id) {
		rightsMapper.deleteByCode(roleCode,id);
	}

	@Override
	public void update(RightsModel rightsModel) {
		rightsMapper.update(rightsModel);
	}

}
