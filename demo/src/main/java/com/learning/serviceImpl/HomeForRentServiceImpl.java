package com.learning.serviceImpl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.learning.service.HomeForRentService;
import com.shared.Dao.HomeForRentDao;

@Service(value = "HomeForRentService")
public class HomeForRentServiceImpl implements HomeForRentService {

	@Autowired
	HomeForRentDao homeForRentDao;

	@Override
	public Boolean getRegisterUserHomeDetail(String address, Integer postalCode, BigDecimal latitude,
			BigDecimal longitude, String city, BigInteger phoneNumber, String landmark, List<MultipartFile> file,
			String state) {
		return homeForRentDao.getRegisterUserHomeDetail(address, postalCode, latitude, longitude, city, phoneNumber,
				landmark, file, state);
	}

}
