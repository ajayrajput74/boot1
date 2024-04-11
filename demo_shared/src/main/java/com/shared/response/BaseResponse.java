package com.shared.response;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import com.shared.view.BaseView;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private String authToken;

	@JsonView(BaseView.class)
	private Integer statusCode;

	@JsonView(BaseView.class)
	private String responseMessage;

	@JsonView(BaseView.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime timestamp;

	private String debugMessage;

	private Integer recordsTotal;
	
	private String scheduleType;

	public String getScheduleType() {
		return scheduleType;
	}

	public void setScheduleType(String scheduleType) {
		this.scheduleType = scheduleType;
	}


	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
	private Date lastUpdatedDate;

	
	private Integer totalNotLiveAssets;
	

	private Boolean isClientLive;
	
	
	public Boolean getIsClientLive() {
		return isClientLive;
	}

	public void setIsClientLive(Boolean isClientLive) {
		this.isClientLive = isClientLive;
	}

	public Integer getTotalNotLiveAssets() {
		return totalNotLiveAssets;
	}

	public void setTotalNotLiveAssets(Integer totalNotLiveAssets) {
		this.totalNotLiveAssets = totalNotLiveAssets;
	}

	public Date getLastUpdatedDate() {
		return new Date();
	}

	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	public Integer getRecordsTotal() {
		return recordsTotal;
	}

	public void setRecordsTotal(Integer recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	
	public String getAuthToken() {
		return authToken;
	}

	
	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}


	public Integer getStatusCode() {
		return statusCode;
	}


	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}


	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getDebugMessage() {
		return debugMessage;
	}

	public void setDebugMessage(String debugMessage) {
		this.debugMessage = debugMessage;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((authToken == null) ? 0 : authToken.hashCode());
		result = prime * result + ((responseMessage == null) ? 0 : responseMessage.hashCode());
		result = prime * result + ((statusCode == null) ? 0 : statusCode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BaseResponse other = (BaseResponse) obj;
		if (authToken == null) {
			if (other.authToken != null)
				return false;
		} else if (!authToken.equals(other.authToken))
			return false;
		if (responseMessage == null) {
			if (other.responseMessage != null)
				return false;
		} else if (!responseMessage.equals(other.responseMessage))
			return false;
		if (statusCode == null) {
			if (other.statusCode != null)
				return false;
		} else if (!statusCode.equals(other.statusCode))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BaseResponse [authToken=" + authToken + ", statusCode=" + statusCode + ", responseMessage="
				+ responseMessage + "]";
	}

}
