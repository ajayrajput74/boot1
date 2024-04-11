package com.learning.handler;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.learning.service.HomeForRentService;
import com.learning.util.Constant;
import com.shared.response.BaseResponse;

@Service(value = "HomeForRentRequestHandler")
public class HomeForRentRequestHandler extends BaseRequestHanler {

	@Autowired
	HomeForRentService homeForRentService;

	public BaseResponse getRegisterUserHomeDetail(String address, Integer postalCode, BigDecimal latitude,
			BigDecimal longitude, String city, BigInteger phoneNumber, String landmark, List<MultipartFile> file,
			String state) {

		if (address == null && postalCode == null && latitude == null && longitude == null && city == null
				&& phoneNumber == null && state == null) {

			convertEntityToResponses(Constant.STATUSCODE_100, Constant.INVALID_REQUEST, null, null, null);
		}

		if (file == null && file.size() > 0 && file.isEmpty()) {

			convertEntityToResponses(Constant.STATUSCODE_100, Constant.OPERATIONAL_ERROR, null, null, null);
		}

		Boolean result = homeForRentService.getRegisterUserHomeDetail(address, postalCode, latitude, longitude, city,
				phoneNumber, landmark, file, state);

		if (result) {
			convertEntityToResponses(Constant.STATUSCODE_100, Constant.SUCCESS, null, result, null);
		} else {
			convertEntityToResponses(Constant.STATUSCODE_100, Constant.OPERATIONAL_ERROR, null, null, null);
		}

		return convertEntityToResponses(Constant.STATUSCODE_100, Constant.OPERATIONAL_ERROR, null, null, null);
	}
	
	

}
