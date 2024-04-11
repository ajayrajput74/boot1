package com.shared.Dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public interface HomeForRentDao {

	Boolean getRegisterUserHomeDetail(String address, Integer postalCode, BigDecimal latitude, BigDecimal longitude,
			String city, BigInteger phoneNumber, String landmark, List<MultipartFile> file, String state);

}
