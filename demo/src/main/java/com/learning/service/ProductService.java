package com.learning.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.shared.Dto.ProductDto;
import com.shared.request.ProductRequest;

@Component
public interface ProductService {

	List<ProductDto> getProduct(Integer id);

	Boolean createProduct(ProductRequest productRequest);

	Boolean deleteProduct(Integer productId);

	Boolean updateProduct(ProductRequest productRequest);

	ProductDto getProductById(Integer id);

}
