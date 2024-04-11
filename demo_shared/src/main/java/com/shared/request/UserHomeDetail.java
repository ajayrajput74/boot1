package com.shared.request;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class UserHomeDetail implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer hId;

	private String address;

	private Integer postalCode;

	private BigDecimal latitude;

	private BigDecimal longitude;

	private String city;

	private BigInteger phoneNumber;

	private String landmark;

	private String state;

	private List<MultipartFile> file;

	public Integer gethId() {
		return hId;
	}

	public void sethId(Integer hId) {
		this.hId = hId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(Integer postalCode) {
		this.postalCode = postalCode;
	}

	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getLandmark() {
		return landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public BigInteger getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(BigInteger phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<MultipartFile> getFile() {
		return file;
	}

	public void setFile(List<MultipartFile> file) {
		this.file = file;
	}

	@Override
	public String toString() {
		return "UserHomeDetail [hId=" + hId + ", address=" + address + ", postalCode=" + postalCode + ", latitude="
				+ latitude + ", longitude=" + longitude + ", city=" + city + ", phoneNumber=" + phoneNumber
				+ ", landmark=" + landmark + ", state=" + state + ", file=" + file + "]";
	}
	
	
}
