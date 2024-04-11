package com.shared.Dto;

import java.io.Serializable;

public class UserDTO  implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private Integer userId;

	private String login;
	
	private String password;
	
	private Boolean isEnabled;

	public Boolean getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	 
	

	
	
	
}
