//package com.oauth.config;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.BeanIds;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
//
//import com.oauth.DemoOauthServiceApplication;
//import com.shared.Dao.ReleaseAppDAO;
//import com.shared.Dao.UserDAO;
//import com.shared.Dto.UserDTO;
//
//import jakarta.servlet.Filter;
//
//@Configuration
//@EnableWebSecurity
//@EnableMethodSecurity(prePostEnabled = true)
//public class OAuth2SecurityConfiguration {
//
//	private static final Logger logger = LoggerFactory.getLogger(OAuth2SecurityConfiguration.class);
//
//	@Autowired
//	private UserDAO userDAO;
//
//	@Autowired
//	private BCryptPasswordEncoder passwordEncoder;
//
//	@Autowired
//	private HttpServletRequest request;
//
//	@Autowired
//	private ReleaseAppDAO releaseAppDAO;
//
//	@Autowired
//	protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//
//		auth.userDetailsService(new UserDetailsService() {
//
//			@SuppressWarnings("unused")
//			@Override
//			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//				UserDTO user = userDAO.getUserByUserName(username);
//
//				String param = request.getParameter("param");
//				String agentType = request.getParameter("agentType");
//
//				if (param != null && param.equalsIgnoreCase("impersonate")) {
//					user.setPassword(passwordEncoder.encode(request.getParameter("password")));
//				}
//
//				if (null == user) {
//					logger.error("The user with email " + username + " was not found");
//					throw new UsernameNotFoundException("The user with email " + username + " was not found");
//				}
//
//				if (!user.getIsEnabled()) {
//					logger.error("The user is deactivated.");
//
//					throw new UsernameNotFoundException("The user is deactivated.");
//				}
//
//				CustomUserDetails myUserDetails = new CustomUserDetails(user, agentType,
//						DemoOauthServiceApplication.userAgentHashmap);
//				return myUserDetails;
//			}
//		}).passwordEncoder(passwordEncoder);
//	}
//
//	@Bean
//	public AuthenticationManager authenticationManagerBean() throws Exception {
//		return authenticationManagerBean();
//	}
//
//	@Bean
//	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
//
//		http.addFilterBefore((Filter) new AuthenticationFilter(releaseAppDAO), BasicAuthenticationFilter.class).csrf()
//				.disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//				.exceptionHandling()
//				.authenticationEntryPoint(
//						(request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
//				.and().authorizeRequests().requestMatchers("/oauth/token").permitAll()
//				.requestMatchers("/oauth/revokeToken/**", "/oauth/login/**").permitAll()
//				.requestMatchers("/oauth/token/impersonate").permitAll()
//				// .antMatchers(HttpMethod.GET).permitAll()
//				.anyRequest().authenticated();
//
//		return http.build();
//	}
//}
