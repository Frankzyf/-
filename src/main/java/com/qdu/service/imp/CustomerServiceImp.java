package com.qdu.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qdu.mapper.CustomerMapper;
import com.qdu.model.CustomerModel;
import com.qdu.service.CustomerService;

@Service
public class CustomerServiceImp implements CustomerService{
	
	@Autowired
	CustomerMapper customerMapper;
	
	@Override
	public int insert(CustomerModel customer) {
		int a = customerMapper.insert(customer);
		return a;
	}

	@Override
	public int delete(Object id) {
		int a = customerMapper.delete(id);
		return a;
	}

	@Override
	public int deleteModel(CustomerModel customer) {
		int a = customerMapper.deleteModel(customer);
		return a;
	}

	@Override
	public int update(CustomerModel customer) {
		int a = customerMapper.update(customer);
		return a;
	}

	@Override
	public int updateActive(CustomerModel customer) {
		int a = customerMapper.updateActive(customer);
		return a;
	}

	@Override
	public CustomerModel selectUQ(Object id) {
		CustomerModel a = customerMapper.selectUQ(id);
		return a;
	}

	@Override
	public List<CustomerModel> selectModel(CustomerModel customer) {
		
		return customerMapper.selectModel(customer);
	}

	@Override
	public List<CustomerModel> selectModelPage(CustomerModel customer,int before,int after) {
		List<CustomerModel> a = customerMapper.selectModelPage(customer,before,after);
		return a;
	}

	@Override
	public List<CustomerModel> selectAll(CustomerModel customer) {
		List<CustomerModel> a = customerMapper.selectAll();
		return a;
	}

	@Override
	public int selectCount() {
		int a = customerMapper.selectCount();
		return a;
	}


}
