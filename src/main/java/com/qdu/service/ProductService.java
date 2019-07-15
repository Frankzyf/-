package com.qdu.service;

import java.util.List;

import com.qdu.model.ProductModel;


public interface ProductService {

	int insert(ProductModel product);

	int delete(Object id);

	int deleteModel(ProductModel product);

	int update(ProductModel product);


	int updateActive(ProductModel product);


	ProductModel selectUQ(Object id);


	List<ProductModel> selectModel(ProductModel product);


	List<ProductModel> selectAll(ProductModel product);

	int selectCount();

	List<ProductModel> selectModelPage(ProductModel product,int before,int after);
}
