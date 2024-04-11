package com.learning.controller;

import com.shared.response.BaseResponse;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface ProductController {

	BaseResponse getProduct(Integer productId, HttpServletRequest request, HttpServletResponse reponse);

}
