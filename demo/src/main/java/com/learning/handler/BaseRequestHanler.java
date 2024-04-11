package com.learning.handler;

import java.util.Collection;

import com.shared.response.BaseResponse;
import com.shared.response.ProductResponse;

public class BaseRequestHanler {

	protected String failureMessage = "FAILURE";

	protected String successMessage = "SUCCESS";

	public String getFailureMessage() {
		return failureMessage;
	}

	public void setFailureMessage(String failureMessage) {
		this.failureMessage = failureMessage;
	}

	public BaseResponse convertProductEntityToResponses(Integer statusCode, String responseMessage, String requestMapping,
			Object object, Collection<?> list) {
		ProductResponse productResponse = new ProductResponse();
		if (object != null) {
			productResponse.setResponse(object);
			productResponse.setResponseMessage(responseMessage);
			productResponse.setStatusCode(statusCode);
		} else if (list != null) {
			productResponse.setResponseList(list);
			productResponse.setResponseMessage(responseMessage);
			productResponse.setStatusCode(statusCode);
		} else {
			productResponse.setStatusCode(statusCode);
			productResponse.setResponseMessage(responseMessage);
		}
		return productResponse;
	}
	
	public BaseResponse convertEntityToResponses(Integer statusCode, String responseMessage, String requestMapping,
			Object object, Collection<?> list) {
		ProductResponse productResponse = new ProductResponse();
		if (object != null) {
			productResponse.setResponse(object);
			productResponse.setResponseMessage(responseMessage);
			productResponse.setStatusCode(statusCode);
		} else if (list != null) {
			productResponse.setResponseList(list);
			productResponse.setResponseMessage(responseMessage);
			productResponse.setStatusCode(statusCode);
		} else {
			productResponse.setStatusCode(statusCode);
			productResponse.setResponseMessage(responseMessage);
		}
		return productResponse;
	}
}
