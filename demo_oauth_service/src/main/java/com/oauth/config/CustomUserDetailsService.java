//package com.oauth.config;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//import com.shared.Dao.UserDAO;
//import com.shared.Dto.UserDTO;
//
//public class CustomUserDetailsService implements UserDetailsService {
//
//	private static final    Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);
//
//	@Autowired
//	private UserDAO userDAO;
//
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		
//		UserDTO user = userDAO.getUserByUserName(username);
//		
//		logger.debug("User : " + user);
//		if (user == null || !user.getIsEnabled()) {
//			logger.error("User not found");
//			throw new UsernameNotFoundException("Username not found");
//		}
//		return null;
//	}
//}
