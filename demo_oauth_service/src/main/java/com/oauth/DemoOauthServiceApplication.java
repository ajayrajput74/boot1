//package com.oauth;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.Base64;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.TimeZone;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.commons.lang3.StringUtils;
//import org.json.JSONException;
//import org.json.JSONObject;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
//import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpStatus;
//import org.springframework.scheduling.annotation.EnableAsync;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.security.oauth2.common.OAuth2AccessToken;
//import org.springframework.security.oauth2.common.OAuth2RefreshToken;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.oauth.config.AESEncription;
//import com.oauth.config.AuthenticationFilter;
//import com.shared.Dao.ReleaseAppDAO;
//import com.shared.Dao.UserDAO;
//import com.shared.Dao.UserSessionDAO;
//import com.shared.Dto.UserDTO;
//import com.shared.entity.UserSession;
//import com.shared.response.BaseResponse;
//
//import eu.bitwalker.useragentutils.Browser;
//import eu.bitwalker.useragentutils.UserAgent;
//import jakarta.servlet.Filter;
//import net.sf.uadetector.ReadableUserAgent;
//import net.sf.uadetector.UserAgentStringParser;
//import net.sf.uadetector.VersionNumber;
//import net.sf.uadetector.service.UADetectorServiceFactory; 
//
////@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class, JpaRepositoriesAutoConfiguration.class })
//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, JpaRepositoriesAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
//@Configuration
//@EnableAsync
//@EnableScheduling
//@RestController
//public class DemoOauthServiceApplication {
//
//	private static final Logger logger = LoggerFactory.getLogger(DemoOauthServiceApplication.class);
//	
//	public static Map<String, HashMap<String, String>> userAgentHashmap = new HashMap();
//	
//	public static Map<String, Boolean> impersonateMap = new HashMap();
//	
//	public static boolean impersonateBoolean = false;
//
//	@Autowired
//	private TokenStore tokenStore;
//
//	@Autowired
//	private UserSessionDAO userSessionDAO;
//
//	@Autowired
//	private UserDAO userDAO;
//
//	@Autowired
//	ReleaseAppDAO releaseAppDao;
//	
//	
//	public DemoOauthServiceApplication() {
//	}
//
//	public static boolean isImpersonateBoolean() {
//		return impersonateBoolean;
//	}
//
//	public static void setImpersonateBoolean(boolean impersonateBoolean) {
//		DemoOauthServiceApplication.impersonateBoolean = impersonateBoolean;
//	}
//
//	public static void main(String[] args) {
//		SpringApplication.run(DemoOauthServiceApplication.class, args);
//		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
//		logger.debug("AMCi Oauth Service Started ........ ");
//	}
//	
//	@SuppressWarnings({ "unchecked", "rawtypes" })
//	@Bean
//	public FilterRegistrationBean authenticationFilter() {
//		FilterRegistrationBean registration = new FilterRegistrationBean();
//		registration.setFilter((Filter) new AuthenticationFilter(releaseAppDao));
//		registration.addUrlPatterns(new String[]{"/oauth/token/*"});
//		registration.setOrder(Integer.MIN_VALUE);
//		return registration;
//	}
//
//	public static void resetUserAgentHashMap(String userName) {
//		userAgentHashmap.remove(userName);
//		userAgentHashmap.clear();
//	}
//
//	public static void resetImpersonateHashMap(String key) {
//		impersonateMap.remove(key);
//		impersonateMap.clear();
//	}
//
//	@ResponseStatus(HttpStatus.OK)
//	@RequestMapping(value = {"/oauth/revokeToken"},method = {RequestMethod.DELETE})
//	public BaseResponse logout(HttpServletRequest request, HttpServletResponse response) {
//		BaseResponse baseResponse = new BaseResponse();
//		String authHeaderVal = null;
//		if (request.getHeader("Authorization") != null && request.getHeader("Authorization").trim().length() != 0) {
//			authHeaderVal = request.getHeader("Authorization");
//		} else {
//			authHeaderVal = "bearer " + request.getHeader("sessionToken");
//			logger.debug(authHeaderVal);
//		}
//
//		String accessToken = null;
//		OAuth2AccessToken auth2AccessToken = null;
//		if (authHeaderVal != null) {
//			String[] tokens = authHeaderVal.split(" ");
//			if (tokens != null && tokens.length > 1) {
//				accessToken = authHeaderVal.split(" ")[1];
//				logger.debug("OauthServerApplication logout >>> " + accessToken);
//				auth2AccessToken = this.tokenStore.readAccessToken(accessToken);
//			}
//		}
//
//		if (auth2AccessToken != null) {
//			logger.debug("Access token ------------" + auth2AccessToken.getValue());
//			this.deleteSession(auth2AccessToken.getValue());
//			this.tokenStore.removeAccessToken(auth2AccessToken);
//			OAuth2RefreshToken oAuth2RefreshToken = auth2AccessToken.getRefreshToken();
//			if (oAuth2RefreshToken != null) {
//				this.tokenStore.removeRefreshToken(oAuth2RefreshToken);
//			}
//		}
//
//		JSONObject jsonObject = new JSONObject();
//
//		try {
//			jsonObject.put("statusCode", "100");
//			jsonObject.put("responseMessage", "Logout successfully.");
//		} catch (JSONException var9) {
//			logger.error("OauthServerApplication : logout()" + var9.getMessage());
//		}
//
//		baseResponse.setResponseMessage("Logout successfully.");
//		baseResponse.setStatusCode(HttpStatus.OK.value());
//		return baseResponse;
//	}
//
//	private void deleteSession(String accessToken) {
//		if (accessToken != null) {
//			this.userSessionDAO.updateUserSessionWhileDeleteUserSession(accessToken, (Integer)null);
//		}
//
//	}
//
//	@RequestMapping(value = {"/oauth/login"},method = {RequestMethod.POST})
//	public void login(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		String userName = request.getParameter("username");
//		String grantType = request.getParameter("grant_type");
//		String password = request.getParameter("password");
//		String userType = request.getParameter("user_type");
//		//logger.debug(" user name >> " + userName + " grant type " + grantType);
//		this.userDAO.getUserByUserName(userName);
//		String message = "";
//
//		// String newPassword=new String( Base64.getDecoder().decode(password));
//		String newPassword = null;
//
//		if (password != null) {
//			newPassword = new String(Base64.getMimeDecoder().decode(password));
//		}
//
//		HashMap<String, String> userAgent = new HashMap();
//		UserAgentStringParser parser = UADetectorServiceFactory.getResourceModuleParser();
//		ReadableUserAgent agent = parser.parse(request.getHeader("User-Agent"));
//		UserAgent agent1 = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
//		Browser browser = agent1.getBrowser();
//		VersionNumber browserVersion = agent.getVersionNumber();
//		userAgent.put("browsertype", agent.getType().getName());
//		userAgent.put("browser", agent.getName() + " " + browserVersion.toVersionString());
//		userAgent.put("producer", agent.getProducer());
//		userAgent.put("os", agent.getOperatingSystem().getName());
//		String remoteAddr = null;
//		if (request != null) {
//			remoteAddr = request.getHeader("X-FORWARDED-FOR");
//			if (StringUtils.isEmpty(remoteAddr)) {
//				remoteAddr = request.getRemoteAddr();
//			}
//		}
//
//		userAgent.put("remoteAddr", remoteAddr);
//		userAgent.put("renderingEngin", browser.getRenderingEngine().toString());
//		userAgent.put("deviceType", "Computer");
//		userAgentHashmap.put(userName, userAgent);
//		resetImpersonateHashMap("impersonate");
//		RequestDispatcher dispatcher = null;
//		String param = request.getParameter("param");
//		if (param != null && param.equalsIgnoreCase("impersonate")) {
//			String[] splitedUserIdAndToken = AESEncription.decrypt(request.getParameter("admin_access_token")).split("\\|");
//			Integer adminUserId = Integer.parseInt(splitedUserIdAndToken[0]);
//			Integer clientUserId = Integer.parseInt(splitedUserIdAndToken[1]);
//			UserSession userSession = this.userSessionDAO.getSessionBySessionToken(splitedUserIdAndToken[2]);
//			UserDTO clientUser = this.userDAO.getUserByUserId(clientUserId);
//			UserDTO userDTO = this.userDAO.getUserByUserId(userSession.getContactId());
//			logger.debug("REFERER URL =" + null == request.getHeader("referer") ? "No referer" : request.getHeader("referer"));
//			if (splitedUserIdAndToken[1] != null && userSession != null && userDTO != null && userDTO.getUserId().equals(adminUserId)) {
//				logger.info("IMPERSONATION SUCCESSFUL FOR : admin user  " + adminUserId);
//			} else {
//				JSONObject jsonObject = new JSONObject();
//
//				try {
//					jsonObject.put("statusCode", "109");
//					jsonObject.put("responseMessage", "Impersonate Failed: Invalid Request... ");
//					PrintWriter out = response.getWriter();
//					out.println(jsonObject);
//					out.flush();
//					return;
//				} catch (JSONException var29) {
//					var29.printStackTrace();
//					logger.error("OauthServerApplication : login " + var29.getMessage());
//				}
//			}
//
//			newPassword = "Amci@123";
//			dispatcher = request.getRequestDispatcher("/oauth/token?grant_type=" + grantType + "&param=impersonate" + "&username=" + clientUser.getLogin() + "&password=" + newPassword);
//			// user.setPassword("$2a$10$STNLYdbsa526sHzXK2dOQOxRTW5ygMxDtLCwZffe25FuY.B/8Np9i");
//		} else {
//			dispatcher = request.getRequestDispatcher("/oauth/token?grant_type=" + grantType + "&username=" + userName + "&username=" + userName + "&password=" + newPassword);
//			System.out.println("dispatcher "+dispatcher.toString());
//		}
//		
//		try {
//			dispatcher.forward(request, response);
//		} catch (ServletException var27) {
//			var27.printStackTrace();
//			logger.error("OauthServerApplication :" + var27.getMessage());
//		} catch (IOException var28) {
//			var28.printStackTrace();
//			logger.error("OauthServerApplication :" + var28.getMessage());
//		}
//	}
//}
