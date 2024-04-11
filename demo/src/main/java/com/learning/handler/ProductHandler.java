package com.learning.handler;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.service.ProductService;
import com.learning.util.Constant;
import com.shared.Dto.ProductDto;
import com.shared.request.ProductRequest;
import com.shared.response.BaseResponse;

@Service("productHandler")
public class ProductHandler extends BaseRequestHanler {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	ProductService productService;

	public BaseResponse getProduct(Integer productId) {

		logger.info(" ProductHandler Product Id " + productId);
		Integer Id = null;

		if (productId == null || productId.intValue() < 1) {

			return convertProductEntityToResponses(Constant.STATUSCODE_103, Constant.INVALID_REQUEST, null, null, null);
		} else {
			Id = productId;
		}

		List<ProductDto> productList = productService.getProduct(Id);

		if (productList != null && productList.size() > 0) {
			return convertProductEntityToResponses(Constant.STATUSCODE_103, Constant.SUCCESS, null, null, productList);
		}
		return convertProductEntityToResponses(Constant.STATUSCODE_103, Constant.OPERATIONAL_ERROR, null, null, null);
	}

	public BaseResponse createProduct(ProductRequest productRequest) {

		logger.info(" ProductHandler Product Request " + productRequest);

		if (productRequest == null) {

			return convertProductEntityToResponses(Constant.STATUSCODE_103, Constant.INVALID_REQUEST, null, null, null);
		}

		Boolean productList = productService.createProduct(productRequest);

		if (productList) {
			return convertProductEntityToResponses(Constant.STATUSCODE_103, Constant.SUCCESS, null, null, null);
		}
		return convertProductEntityToResponses(Constant.STATUSCODE_103, Constant.OPERATIONAL_ERROR, null, null, null);
	}

	public BaseResponse updateProduct(ProductRequest productRequest) {
		logger.info(" ProductHandler productRequest " + productRequest);
		Integer Id = null;

		if (productRequest == null ) {

			return convertProductEntityToResponses(Constant.STATUSCODE_103, Constant.INVALID_REQUEST, null, null, null);
		}

		Boolean productList = productService.updateProduct(productRequest);
		
		if (productList) {
			return convertProductEntityToResponses(Constant.STATUSCODE_103, Constant.SUCCESS, null, null, null);
		}
		return convertProductEntityToResponses(Constant.STATUSCODE_103, Constant.OPERATIONAL_ERROR, null, null, null);
	}

	public BaseResponse deleteProduct(Integer productId) {
		logger.info(" ProductHandler Product Id " + productId);
		Integer Id = null;

		if (productId == null || productId.intValue() < 1) {

			return convertProductEntityToResponses(Constant.STATUSCODE_103, Constant.INVALID_REQUEST, null, null, null);
		} else {
			Id = productId;
		}

		Boolean productList = productService.deleteProduct(productId);

		if (productList) {
			return convertProductEntityToResponses(Constant.STATUSCODE_103, Constant.SUCCESS, null, null, null);
		}
		return convertProductEntityToResponses(Constant.STATUSCODE_103, Constant.OPERATIONAL_ERROR, null, null, null);
	}

	@SuppressWarnings("unused")
	public BaseResponse getProductById(Integer productId) {
		
		logger.info(" ProductHandler Product Id " + productId);
		Integer Id = null;

		if (productId == null || productId.intValue() < 1) {

			return convertProductEntityToResponses(Constant.STATUSCODE_103, Constant.INVALID_REQUEST, null, null, null);
		} else {
			Id = productId;
		}

		ProductDto productList = productService.getProductById(Id);
		
		System.out.println("productList "+productList.getProductName());

		if (productList != null ) {
			return convertProductEntityToResponses(Constant.STATUSCODE_103, Constant.SUCCESS, null, productList, null);
		}
		return convertProductEntityToResponses(Constant.STATUSCODE_103, Constant.OPERATIONAL_ERROR, null, null, null);
		
	}
}
