package com.shared.properties;

public class JwtProperties {
	
	
	private String jksFileName;

	private String password;
    
	private String alias;

	public String getJksFileName() {
		return jksFileName;
	}

	public void setJksFileName(String jksFileName) {
		this.jksFileName = jksFileName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}
	
}
