package com.qdu.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qdu.mapper.ProductMapper;
import com.qdu.model.ProductModel;
import com.qdu.service.ProductService;

@Service
public class ProductServiceImp implements ProductService{

	@Autowired
	ProductMapper productMapper;
	
	@Override
	public int insert(ProductModel product) {
		int a = productMapper.insert(product);
		return a;
	}

	@Override
	public int delete(Object id) {
		int a = productMapper.delete(id);
		return a;
	}

	@Override
	public int deleteModel(ProductModel product) {
		int a = productMapper.deleteModel(product);
		return a;
	}

	@Override
	public int update(ProductModel product) {
		int a = productMapper.update(product);
		return a;
	}

	@Override
	public int updateActive(ProductModel product) {
		int a = productMapper.updateActive(product);
		return a;
	}

	@Override
	public ProductModel selectUQ(Object id) {
		ProductModel a = productMapper.selectUQ(id);
		return a;
	}

	@Override
	public List<ProductModel> selectModel(ProductModel product) {
		
		return productMapper.selectModel(product);
	}

	@Override
	public List<ProductModel> selectModelPage(ProductModel product,int before,int after) {
		List<ProductModel> a = productMapper.selectModelPage(product,before,after);
		return a;
	}

	@Override
	public List<ProductModel> selectAll(ProductModel product) {
		List<ProductModel> a = productMapper.selectAll();
		return a;
	}

	@Override
	public int selectCount() {
		int a = productMapper.selectCount();
		return a;
	}

}
