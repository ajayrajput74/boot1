//package com.oauth.config;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//
//import javax.crypto.Cipher;
//import javax.crypto.spec.IvParameterSpec;
//import javax.crypto.spec.SecretKeySpec;
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.commons.codec.binary.Base64;
//import org.json.JSONException;
//import org.json.JSONObject;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.http.HttpHeaders;
//import org.springframework.stereotype.Component;
//
//import com.shared.Dao.ReleaseAppDAO;
//import com.shared.Dto.ReleaseAppDTO;
//
//@Component
//public class AuthenticationFilter implements Filter {
//
//	private static final Logger logger = LoggerFactory.getLogger(AuthenticationFilter.class);
//
//	private static final String key = "aesEncryptionKey";
//	private static final String initVector = "encryptionIntVec";
//
//	ReleaseAppDAO releaseAppDAO;
//	
//	
//
//	public AuthenticationFilter(ReleaseAppDAO releaseAppDAO) {
//		super();
//		this.releaseAppDAO = releaseAppDAO;
//	}
//
//
//	@Override
//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//			throws IOException, ServletException {
//		HttpServletRequest req = (HttpServletRequest) request;
//		HttpServletResponse res = (HttpServletResponse) response;
//		logger.info("url  "+req.getRequestURL());
//		Integer STATUSCODE_111 = 111;
//		Integer STATUSCODE_112 = 112;
//		// cG9vamFAMTIz pooja@123
//		/*
//		 * Decoder decoder = java.util.Base64.getDecoder(); byte[] decodedByte =
//		 * decoder.decode(req.getParameter("password")); String decodedString = new
//		 * String(decodedByte); System.out.println(" password dec "+decodedString);
//		 * //req.getServletContext().setInitParameter("password", decodedString);
//		 * req.setAttribute("password", decodedString);
//		 */
//		if (req.getHeader("version") != null) {
//			String version = req.getHeader("version");
//
//			if (version == null) {
//				res.sendError(STATUSCODE_111, "Version: Version was missing.");
//				return;
//			}
//
//			String[] versionArray = version.split(":");
//			try {
//				if (versionArray[0].isEmpty() || versionArray[1].isEmpty()) {
//					res.sendError(STATUSCODE_111, "Version: Version was missing.");
//					return;
//				}
//			} catch (ArrayIndexOutOfBoundsException e) {
//				res.sendError(STATUSCODE_111, "Version: Version was missing.");
//				return;
//			}
//
//			ReleaseAppDTO releaseAppDTO = releaseAppDAO.getActiveReleaseAppDetails(versionArray[0]);
//			
//			if (releaseAppDTO == null) {
//				res.sendError(STATUSCODE_111, "Version: None of the version is live now.");
//				return;
//			}
//
//			if (Double.compare(Double.parseDouble(versionArray[1]), releaseAppDTO.getVersion()) < 0) {
//				if (releaseAppDTO.getIsForceUpdate()) {
//					response.setContentType("application/json");
//					response.setCharacterEncoding("UTF-8");
//					JSONObject jsonObject = new JSONObject();
//					try {
//						jsonObject.put("status", STATUSCODE_112);
//						jsonObject.put("message", "Version: You are using the old version");
//						jsonObject.put("url", releaseAppDTO.getAppUrl());
//						jsonObject.put("feature", releaseAppDTO.getAppFeature());
//					} catch (JSONException e) {
//						logger.info(this.getClass() + e.getMessage());
//					}
//					PrintWriter out = response.getWriter();
//					out.println(jsonObject);
//					out.flush();
//					return;
//					// res.sendError(STATUSCODE_112, "Version: You are using the old version");
//					// return;
//				}
//			}
//		}
//
//		((HttpServletResponse) response).addHeader("Access-Control-Allow-Origin", "*");
//
//		MutableHttpServletRequest mutableRequest = new MutableHttpServletRequest(req);
//	
//		if (req.getRequestURI().contains("/oauth/token")) {
//			mutableRequest.putHeader(HttpHeaders.AUTHORIZATION, "Basic bXktdHJ1c3RlZC1jbGllbnQ6c2VjcmV0");
//		}
//		chain.doFilter(mutableRequest, response);
//	}
//	
//
//	@Override
//	public void destroy() {
//	}
//
//	@Override
//	public void init(FilterConfig arg0) throws ServletException {
//	}
//
//	public static String encrypt(String value) {
//		try {
//			IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
//			SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
//
//			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
//			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
//
//			byte[] encrypted = cipher.doFinal(value.getBytes());
//			return Base64.encodeBase64String(encrypted);
//		} catch (Exception ex) {
//			logger.error(ex.getMessage());
//			// ex.printStackTrace();
//		}
//		return null;
//	}
//
//	public static String decrypt(String encrypted) {
//		try {
//			IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
//			SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
//
//			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
//			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
//			byte[] original = cipher.doFinal(Base64.decodeBase64(encrypted));
//
//			return new String(original);
//		} catch (Exception ex) {
//
//			logger.error(ex.getMessage());
//		}
//
//		return null;
//	}
//
//}
