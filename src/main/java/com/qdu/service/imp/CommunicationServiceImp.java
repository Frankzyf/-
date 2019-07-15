package com.qdu.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qdu.mapper.CommunicationMapper;
import com.qdu.model.CommunicationModel;
import com.qdu.service.CommunicationService;


@Service
public class CommunicationServiceImp implements CommunicationService{

	@Autowired
	CommunicationMapper communicationMapper;
	
	@Override
	public int insert(CommunicationModel com) {
		int a = communicationMapper.insert(com);
		return a;
	}

	@Override
	public int delete(Object id) {
		int a = communicationMapper.delete(id);
		return a;
	}

	@Override
	public int deleteModel(CommunicationModel com) {
		int a = communicationMapper.deleteModel(com);
		return a;
	}

	@Override
	public int update(CommunicationModel com) {
		int a = communicationMapper.update(com);
		return a;
	}

	@Override
	public int updateActive(CommunicationModel com) {
		int a = communicationMapper.updateActive(com);
		return a;
	}

	@Override
	public CommunicationModel selectUQ(Object id) {
		CommunicationModel a = communicationMapper.selectUQ(id);
		return a;
	}

	@Override
	public List<CommunicationModel> selectModel(CommunicationModel com) {
		
		return communicationMapper.selectModel(com);
	}

	@Override
	public List<CommunicationModel> selectModelPage(CommunicationModel com,int before,int after) {
		List<CommunicationModel> a = communicationMapper.selectModelPage(com,before,after);
		return a;
	}

	@Override
	public List<CommunicationModel> selectAll(CommunicationModel com) {
		List<CommunicationModel> a = communicationMapper.selectAll();
		return a;
	}

	@Override
	public int selectCount() {
		int a = communicationMapper.selectCount();
		return a;
	}
}
