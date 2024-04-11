package com.shared.Dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonView;
import com.shared.view.ProductView;

public class ProductDto implements Serializable
{
	private static final long serialVersionUID = 1L;
	
    @JsonView(ProductView.GetProduct.class)
    private Integer productId;
	
    private Integer compAssetId;
    
    private Integer assetId;
    
    @JsonView(ProductView.GetProduct.class)
	private String productName;
	
    @JsonView(ProductView.GetProduct.class)
	private String productDiscription;
	
    @JsonView(ProductView.GetProduct.class)
	private Integer productCost;
	
    @JsonView(ProductView.GetProduct.class)
	private Integer productMargin;

	public Integer getProductId() {
		return productId;
	}


	public void setProductId(Integer productId) {
		this.productId = productId;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public String getProductDiscription() {
		return productDiscription;
	}


	public void setProductDiscription(String productDiscription) {
		this.productDiscription = productDiscription;
	}


	public Integer getProductCost() {
		return productCost;
	}


	public void setProductCost(Integer productCost) {
		this.productCost = productCost;
	}


	public Integer getProductMargin() {
		return productMargin;
	}


	public void setProductMargin(Integer productMargin) {
		this.productMargin = productMargin;
	}

	public Integer getCompAssetId() {
		return compAssetId;
	}


	public void setCompAssetId(Integer compAssetId) {
		this.compAssetId = compAssetId;
	}


	public Integer getAssetId() {
		return assetId;
	}


	public void setAssetId(Integer assetId) {
		this.assetId = assetId;
	}

}
