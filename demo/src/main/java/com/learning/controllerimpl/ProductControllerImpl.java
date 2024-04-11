package com.learning.controllerimpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.learning.handler.ProductHandler;
import com.shared.request.ProductRequest;
import com.shared.response.BaseResponse;
import com.shared.view.ProductView;

import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@CrossOrigin("*")
@RestController
@RequestMapping("/product")
public class ProductControllerImpl {
	
	private final Logger logger = LoggerFactory.getLogger(ProductControllerImpl.class);

	@Autowired
	ProductHandler productHandler;

	@JsonView(ProductView.GetProduct.class)
	@RequestMapping(value = "/getProduct", method = RequestMethod.GET)
	@ResponseStatus(code = HttpStatus.OK)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = BaseResponse.class),
			@ApiResponse(code = 500, message = "Failed") })
	public BaseResponse getProduct(@RequestParam(value = "productId") Integer productId, HttpServletRequest request,
			HttpServletResponse reponse) {
		logger.info("get Prodcut " + productId);

		return productHandler.getProduct(productId);
	}
	
	
	@JsonView(ProductView.GetProduct.class)
	@RequestMapping(value = "/createProduct", method = RequestMethod.POST)
	@ResponseStatus(code = HttpStatus.CREATED)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = BaseResponse.class),
			@ApiResponse(code = 500, message = "Failed") })
	public BaseResponse createProduct(@RequestBody @ApiParam(value = "productRequest", required = true) ProductRequest productRequest , HttpServletRequest request,
			HttpServletResponse reponse) {
		logger.info("Product Request "+productRequest );
		return productHandler.createProduct(productRequest);
	}
	
	
	@JsonView(ProductView.GetProduct.class)
	@RequestMapping(value = "/deleteProduct", method = RequestMethod.DELETE)
	@ResponseStatus(code = HttpStatus.CREATED)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = BaseResponse.class),
			@ApiResponse(code = 500, message = "Failed") })
	public BaseResponse deleteProduct(@RequestParam(value = "productId") Integer productId, HttpServletRequest request,
			HttpServletResponse reponse) {
		logger.info("Product Request "+productId);
		return productHandler.deleteProduct(productId);
	}
	
	@JsonView(ProductView.GetProduct.class)
	@RequestMapping(value = "/updateProduct", method = RequestMethod.PUT)
	@ResponseStatus(code = HttpStatus.CREATED)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = BaseResponse.class),
			@ApiResponse(code = 500, message = "Failed") })
	public BaseResponse updateProduct(@RequestBody @ApiParam(value = "productRequest", required = true) ProductRequest productRequest , HttpServletRequest request,
			HttpServletResponse reponse) {
		logger.info("Product Request "+productRequest );
		return productHandler.updateProduct(productRequest);
	}
	
	
	@JsonView(ProductView.GetProduct.class)
	@RequestMapping(value = "/getProductById", method = RequestMethod.GET)
	@ResponseStatus(code = HttpStatus.OK)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = BaseResponse.class),
			@ApiResponse(code = 500, message = "Failed") })
	public BaseResponse getProductById(@RequestParam(value = "productId") Integer productId, HttpServletRequest request,
			HttpServletResponse reponse) {
		logger.info("get Prodcut " + productId);

		return productHandler.getProductById(productId);
	}
	
	
	
	
	
	
	

}