package com.qdu.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qdu.mapper.OrderInfoMapper;
import com.qdu.model.OrderInfoModel;
import com.qdu.service.OrderInfoService;

@Service
public class OrderInfoServiceImp implements OrderInfoService{

	@Autowired
	OrderInfoMapper orderInfoMapper;
	
	@Override
	public int insert(OrderInfoModel order) {
		int a = orderInfoMapper.insert(order);
		return a;
	}

	@Override
	public int delete(Object id) {
		int a = orderInfoMapper.delete(id);
		return a;
	}

	@Override
	public int deleteModel(OrderInfoModel order) {
		int a = orderInfoMapper.deleteModel(order);
		return a;
	}

	@Override
	public int update(OrderInfoModel order) {
		int a = orderInfoMapper.update(order);
		return a;
	}

	@Override
	public int updateActive(OrderInfoModel order) {
		int a = orderInfoMapper.updateActive(order);
		return a;
	}

	@Override
	public OrderInfoModel selectUQ(Object id) {
		OrderInfoModel a = orderInfoMapper.selectUQ(id);
		return a;
	}

	@Override
	public List<OrderInfoModel> selectModel(OrderInfoModel order) {
		
		return orderInfoMapper.selectModel(order);
	}

	@Override
	public List<OrderInfoModel> selectModelPage(OrderInfoModel order,int before,int after) {
		List<OrderInfoModel> a = orderInfoMapper.selectModelPage(order,before,after);
		return a;
	}

	@Override
	public List<OrderInfoModel> selectAll(OrderInfoModel order) {
		List<OrderInfoModel> a = orderInfoMapper.selectAll();
		return a;
	}

	@Override
	public int selectCount() {
		int a = orderInfoMapper.selectCount();
		return a;
	}


}
