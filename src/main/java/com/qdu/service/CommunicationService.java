package com.qdu.service;

import java.util.List;

import com.qdu.model.CommunicationModel;



public interface CommunicationService {
	
	int insert(CommunicationModel com);

	int delete(Object id);

	int deleteModel(CommunicationModel com);

	int update(CommunicationModel com);


	int updateActive(CommunicationModel com);


	CommunicationModel selectUQ(Object id);


	List<CommunicationModel> selectModel(CommunicationModel com);


	List<CommunicationModel> selectAll(CommunicationModel com);

	int selectCount();

	List<CommunicationModel> selectModelPage(CommunicationModel com,int before,int after);
}
