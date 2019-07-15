package com.qdu.service;

import java.util.List;

import com.qdu.model.OrderInfoModel;


public interface OrderInfoService {

	int insert(OrderInfoModel order);

	int delete(Object id);

	int deleteModel(OrderInfoModel order);

	int update(OrderInfoModel order);


	int updateActive(OrderInfoModel order);


	OrderInfoModel selectUQ(Object id);


	List<OrderInfoModel> selectModel(OrderInfoModel order);


	List<OrderInfoModel> selectAll(OrderInfoModel order);

	int selectCount();

	List<OrderInfoModel> selectModelPage(OrderInfoModel order,int before,int after);
}

