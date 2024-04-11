package com.shared.request;

import java.io.Serializable;

public class ProductRequest implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer productId;

	private String productName;
	
	private String productDiscription;
	
	private Integer productCost;
	
	private Integer productTotal;
	
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

	public Integer getProductTotal() {
		return productTotal;
	}

	public void setProductTotal(Integer productTotal) {
		this.productTotal = productTotal;
	}

	@Override
	public String toString() {
		return "ProductRequest [productName=" + productName + ", productDiscription=" + productDiscription
				+ ", productCost=" + productCost + ", productTotal=" + productTotal + "]";
	}
	
}
