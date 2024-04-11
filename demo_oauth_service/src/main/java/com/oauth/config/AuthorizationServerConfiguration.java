//package com.oauth.config;
//
//import java.util.Arrays;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
//import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
//import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
//import org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator;
//import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
//import org.springframework.security.oauth2.provider.token.TokenEnhancer;
//import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
//import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
//import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
//
//import com.shared.properties.ClientProperties;
//import com.shared.properties.JwtProperties;
//
//@Configuration
//@EnableResourceServer
//@EnableAuthorizationServer
//public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter  {
//
//	private static final    Logger logger = LoggerFactory.getLogger(AuthorizationServerConfiguration.class);
//
//	@Autowired
//	private ClientProperties clientProperties;
//
//	@Autowired
//	private JwtProperties jwtProperties;
//
//	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//		clients.inMemory().withClient(clientProperties.getClientId()).scopes(clientProperties.getScopes())
//		.autoApprove(true).secret(clientProperties.getSecret())
//		.authorizedGrantTypes(clientProperties.getAuthorizedGrantTypes())
//		.resourceIds(clientProperties.getResourceId())
//		.accessTokenValiditySeconds(clientProperties.getAccessTokenValiditySeconds())
//		.refreshTokenValiditySeconds(clientProperties.getRefreshTokenValiditySeconds());
//	}
//
//	@Override
//	public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
//		
//		try {
//			TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
//			tokenEnhancerChain.setTokenEnhancers(Arrays.asList(tokenEnhancer(), accessTokenConverter()));
//			endpoints.tokenStore(tokenStore()).tokenEnhancer(tokenEnhancerChain)
//			.authenticationManager(authenticationManager);
//			//System.out.println(endpoints.);
//			//.authenticationManager(authenticationManager).exceptionTranslator(webResponseExceptionTranslator());
//			//endpoints.allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);
//		}
//		catch (Exception e) {
//
//			logger.error(e.getMessage());
//			//e.printStackTrace();
//		}
//	}
//
//	@Override
//	public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
//		oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
//	}
//
//	@Autowired
//	@Qualifier("authenticationManagerBean")
//	private AuthenticationManager authenticationManager;
//
//	@Bean
//	public TokenStore tokenStore() {
//		return new JwtTokenStore(accessTokenConverter());
//		//		InMemoryTokenStore ts = new InMemoryTokenStore();
//		//		// Force to generate unique token. Otherwise it generates reusable access token.
//		//		ts.setAuthenticationKeyGenerator(new AuthenticationKeyGenerator() {
//		//			@Override
//		//			public String extractKey(OAuth2Authentication authentication) {
//		//				return UUID.randomUUID().toString();
//		//			}
//		//		});
//		//		
//		//		return ts;
//	}
//
//	@Bean
//	public TokenEnhancer tokenEnhancer() {
//		return new CustomTokenEnhancer();
//	}
//
//	@Bean
//	protected JwtAccessTokenConverter accessTokenConverter() {
//		logger.debug(" AuthorizationServerConfiguration  access token server configuration ");
//		KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(
//				new ClassPathResource(jwtProperties.getJksFileName()), jwtProperties.getPassword().toCharArray());
//		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//		converter.setKeyPair(keyStoreKeyFactory.getKeyPair(jwtProperties.getAlias()));
//		//converter.g
//		return converter;
//	}
//
//	@Bean
//	public WebResponseExceptionTranslator webResponseExceptionTranslator() {
//		return new DefaultWebResponseExceptionTranslator() {
//			@Override
//			public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {
//				ResponseEntity<OAuth2Exception> responseEntity = super.translate(e);
//				OAuth2Exception oAuth2Exception = responseEntity.getBody();
//				HttpHeaders headers = new HttpHeaders();
//				headers.setAll(responseEntity.getHeaders().toSingleValueMap());
//				if (OAuth2Exception.INVALID_TOKEN.equals(responseEntity.getBody().getOAuth2ErrorCode())) {
//					return new ResponseEntity<>(new InvalidTokenException("Invalid Token"), responseEntity.getStatusCode());
//				} else {
//					logger.debug(">>>"+ responseEntity.getStatusCode());
//					logger.debug(">>>"+ responseEntity.getBody());
//					logger.debug(">>>"+ oAuth2Exception.getHttpErrorCode());
//					logger.debug(">>>"+ oAuth2Exception.getOAuth2ErrorCode());
//					logger.debug(">>>"+ oAuth2Exception.getSummary());
//					logger.debug(">>>"+ oAuth2Exception.getStackTrace());
//					return new ResponseEntity<>(oAuth2Exception, headers, responseEntity.getStatusCode());
//				}
//			}
//		};
//	}
//}
