package com.learning.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.service.ProductService;
import com.shared.Dao.ProductDao;
import com.shared.Dto.ProductDto;
import com.shared.request.ProductRequest;

@Service(value = "ProductService")
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductDao productDao;
	
	@Override
	public List<ProductDto> getProduct(Integer id) {
		return productDao.getProduct(id);
	}

	@Override
	public Boolean createProduct(ProductRequest productRequest) {
		return productDao.createProduct(productRequest);
	}

	@Override
	public Boolean deleteProduct(Integer productId) {
		return productDao.deleteProduct(productId);
	}

	@Override
	public Boolean updateProduct(ProductRequest productRequest) {
		return productDao.updateProduct(productRequest);
	}

	@Override
	public ProductDto getProductById(Integer id) {
		return productDao.getProductById(id);
	}

}
