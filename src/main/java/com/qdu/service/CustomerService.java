package com.qdu.service;

import java.util.List;

import com.qdu.model.CustomerModel;

public interface CustomerService {

	int insert(CustomerModel customer);

	int delete(Object id);

	int deleteModel(CustomerModel customer);

	int update(CustomerModel customer);


	int updateActive(CustomerModel customer);


	CustomerModel selectUQ(Object id);


	List<CustomerModel> selectModel(CustomerModel customer);


	List<CustomerModel> selectAll(CustomerModel customer);

	int selectCount();

	List<CustomerModel> selectModelPage(CustomerModel customer,int before,int after);
}
