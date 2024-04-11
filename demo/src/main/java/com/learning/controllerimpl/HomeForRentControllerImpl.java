package com.learning.controllerimpl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.learning.controller.HomeForRentController;
import com.learning.handler.HomeForRentRequestHandler;
import com.shared.response.BaseResponse;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "hfrc")
public class HomeForRentControllerImpl implements HomeForRentController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	HomeForRentRequestHandler homeForRentRequestHandler;

	@Override
	@ResponseStatus(code = HttpStatus.CREATED)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = BaseResponse.class),
			@ApiResponse(code = 500, message = "Failed") })
	@RequestMapping(value = "getRegisterUserHomeDetail", method = RequestMethod.POST)
	public BaseResponse getRegisterUserHomeDetail(@RequestParam(value = "address", required = true) String address,
			@RequestParam(value = "postalCode", required = true) Integer postalCode,
			@RequestParam(value = "latitude", required = true) BigDecimal latitude,
			@RequestParam(value = "longitude", required = true) BigDecimal longitude,
			@RequestParam(value = "city", required = true) String city,
			@RequestParam(value = "phoneNumber") BigInteger phoneNumber,
			@RequestParam(value = "landmark", required = true) String landmark,
			@RequestPart(value = "file", required = true) List<MultipartFile> file,
			@RequestPart(value = "state", required = true) String state) {
		logger.info("address " + address, "postalCode " + postalCode, "latitude " + latitude, "longitude " + longitude,
				"phoneNumber " + phoneNumber, "landmark " + landmark, "file " + file, "city " + city, "state " + state);

		return homeForRentRequestHandler.getRegisterUserHomeDetail(address, postalCode, latitude, longitude, city,
				phoneNumber, landmark, file, state);
	}

}
