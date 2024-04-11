package com.shared.Dto;

import java.io.Serializable;

public class ReleaseAppDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Boolean isForceUpdate;
	
	private Double version;
	
	private String appUrl;
	
	private String appFeature;

	public Boolean getIsForceUpdate() {
		return isForceUpdate;
	}

	public void setIsForceUpdate(Boolean isForceUpdate) {
		this.isForceUpdate = isForceUpdate;
	}

	public Double getVersion() {
		return version;
	}

	public void setVersion(Double version) {
		this.version = version;
	}

	public String getAppUrl() {
		return appUrl;
	}

	public void setAppUrl(String appUrl) {
		this.appUrl = appUrl;
	}

	public String getAppFeature() {
		return appFeature;
	}

	public void setAppFeature(String appFeature) {
		this.appFeature = appFeature;
	}
	
	
	
	
	

	
	
}
