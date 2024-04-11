package com.shared.response;

import java.io.Serializable;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import com.shared.view.ProductView;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductResponse extends BaseResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@JsonView(ProductView.GetProduct.class)
	private Object response;
	
    @JsonView(ProductView.GetProduct.class)
    private Collection<?> responseList;

	public Object getResponse() {
		return response;
	}

	public void setResponse(Object response) {
		this.response = response;
	}

	public Collection<?> getResponseList() {
		return responseList;
	}

	public void setResponseList(Collection<?> responseList) {
		this.responseList = responseList;
	}
}

