package com.learning.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.shared.Dao.HomeForRentDao;

@Component
public interface HomeForRentService {

	Boolean getRegisterUserHomeDetail(String address, Integer postalCode, BigDecimal latitude, BigDecimal longitude,
			String city, BigInteger phoneNumber, String landmark, List<MultipartFile> file, String state);

}
