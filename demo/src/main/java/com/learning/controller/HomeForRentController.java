package com.learning.controller;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.shared.response.BaseResponse;

public interface HomeForRentController {

	public BaseResponse getRegisterUserHomeDetail(String address, Integer postalCode, BigDecimal latitude,
			BigDecimal longitude, String city, BigInteger phoneNumber, String landmark, List<MultipartFile> file,
			String state);

}
