//package com.oauth.config;
//
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
//import org.springframework.security.oauth2.common.OAuth2AccessToken;
//import org.springframework.security.oauth2.provider.OAuth2Authentication;
//import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
//
//import com.oauth.DemoOauthServiceApplication;
//import com.shared.Dao.UserSessionDAO;
//import com.shared.entity.UserSession;
//import com.shared.utility.EnumUtils.Claims;
//import com.shared.utility.Utils;
//
//public class CustomTokenEnhancer extends JwtAccessTokenConverter {
//	
//	private static final Logger logger = LoggerFactory.getLogger(CustomTokenEnhancer.class);
//	
//	@Autowired
//	private JwtAccessTokenConverter jwtAccessTokenConverter;
//	
//	@Autowired
//	private UserSessionDAO userSessionDao;
//	
//	@Override
//	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
//		
//		try {
//			CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
//			Map<String, Object> additionalInfo = new HashMap<>();
//			additionalInfo.put("status_code", 200);
//			additionalInfo.put("user_id" ,customUserDetails.getUser().getUserId());
//			additionalInfo.put("message","Login Succesfully!");
//			additionalInfo.put(Claims.USERNAME.value, customUserDetails.getUsername().trim());
//			UserSession session = userSessionDao.getUserSessionByUserAndType(customUserDetails.getUser().getUserId(),
//					customUserDetails.getAgentType());
//			((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
//			OAuth2AccessToken tempToken = jwtAccessTokenConverter.enhance(accessToken, authentication);
//			if (session == null) {
//				createUserSession(customUserDetails, tempToken);
//			} else {
//				updateSessionRefreshToken(session, tempToken);
//			}
//		} catch (Exception e) {
//			logger.error("Exception:- " + e.getMessage());
//		}
//		return accessToken;
//	}
//	
//	private void createUserSession(CustomUserDetails customUserDetails,OAuth2AccessToken tempToken) {
//		try {
//			
//		UserSession session = new UserSession();
//		Date date = new Date();
//		session.setSessionToken(tempToken.getValue());
//		session.setContactId(customUserDetails.getUser().getUserId());
//		session.setCreateTime(Utils.currentDateConverter(date));
//		session.setUpdateTime(Utils.currentDateConverter(date));
//		session.setAgentType(customUserDetails.getAgentType());
//		session.setIsDeleted(false);
//		session.setAgent("web");
//		Integer id=userSessionDao.save(session);
//		logger.debug("id      "+id);
//		DemoOauthServiceApplication.resetUserAgentHashMap(customUserDetails.getUser().getLogin());
//		}catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	private void updateSessionRefreshToken(UserSession sessionOld,OAuth2AccessToken tempToken) {
//		sessionOld.setSessionToken(tempToken.getValue().trim());
//		sessionOld.setIsDeleted(false);
//		sessionOld.setUpdateTime(Utils.currentDateConverter(new Date()));
//		userSessionDao.update(sessionOld);
//	}
//}
