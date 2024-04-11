//package com.oauth.config;
//
//import java.util.Collection;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Set;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import com.shared.Dto.UserDTO;
//
//public class CustomUserDetails implements UserDetails {
//
//	private static final long serialVersionUID = 1L;
//
//	private Set<GrantedAuthority> authorities;
//
//	private UserDTO userDTO;
//
//	private Map<String, HashMap<String, String>> userAgent;
//	
//	private String agentType;
//
//	public String getAgentType() {
//		return agentType;
//	}
//
//	public void setAgentType(String agentType) {
//		this.agentType = agentType;
//	}
//
//	public CustomUserDetails(UserDTO userDTO, String agentType, Map<String, HashMap<String, String>> userAgentHashmap) {
//		this.userDTO = userDTO;
//		this.agentType = agentType;
//		this.userAgent = userAgentHashmap;
//	}
//
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		return authorities;
//	}
//
//	@Override
//	public String getPassword() {
//		if (userDTO != null) {
//			return userDTO.getPassword();
//		}
//		return null;
//	}
//
//	public void setAuthorities(Set<GrantedAuthority> authorities) {
//		this.authorities = authorities;
//	}
//
//	@Override
//	public String getUsername() {
//		if (userDTO != null) {
//			return userDTO.getLogin();
//		}
//		return null;
//	}
//
//	@Override
//	public boolean isAccountNonExpired() {
//		return true;
//	}
//
//	@Override
//	public boolean isAccountNonLocked() {
//		return true;
//	}
//
//	@Override
//	public boolean isCredentialsNonExpired() {
//		return true;
//	}
//
//	public UserDTO getUser() {
//		return userDTO;
//	}
//
//	public void setUser(UserDTO user) {
//		this.userDTO = user;
//	}
//
//	@Override
//	public boolean isEnabled() {
//
//		return this.userDTO.getIsEnabled();
//	}
//
//	public Map<String, HashMap<String, String>> getUserAgent() {
//		return userAgent;
//	}
//
//	public void setUserAgent(Map<String, HashMap<String, String>> userAgent) {
//		this.userAgent = userAgent;
//	}
//
//}
