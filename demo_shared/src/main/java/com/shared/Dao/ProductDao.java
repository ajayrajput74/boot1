package com.shared.Dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.shared.Dto.ProductDto;
import com.shared.request.ProductRequest;

@Component
public interface ProductDao {

	List<ProductDto> getProduct(Integer id);

	Boolean createProduct(ProductRequest productRequest);

	Boolean deleteProduct(Integer productId);

	Boolean updateProduct(ProductRequest productRequest);

	ProductDto getProductById(Integer id);

}
