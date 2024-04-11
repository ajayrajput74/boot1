package com.shared.config;



import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@EnableTransactionManagement  
//@EnableAutoConfiguration(exclude = {HibernateJpaAutoConfiguration.class})  
//@PropertySource({"classpath:application.properties" })
//@Configuration
//public class HibernateConfigration {
//
//	@Autowired
//	Environment environment;
//
//	@Bean
//	public LocalSessionFactoryBean sessionFactory() {
//		final LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
//		sessionFactory.setDataSource(this.dataSource());
//		sessionFactory.setPackagesToScan(new String[] { "com.example.entity" });
//		sessionFactory.setHibernateProperties(this.hibernateProperties());
//		return sessionFactory;
//	}
//
//	@Bean
//	public DataSource dataSource() {
//		final DriverManagerDataSource dataSource = new DriverManagerDataSource();
//		dataSource.setDriverClassName(this.environment.getRequiredProperty("spring.datasource.driverClassName"));
//		dataSource.setUrl(this.environment.getRequiredProperty("spring.datasource.url"));
//		try {
//
//			final String dbUser = this.environment.getRequiredProperty("spring.datasource.username");
//			final String dbPwd = this.environment.getRequiredProperty("spring.datasource.password");
//			dataSource.setUsername(dbUser);
//			dataSource.setPassword(dbPwd);
//		}
//		catch (IllegalStateException e) {
//			e.printStackTrace();
//		}
//		catch (Exception e2) {
//			e2.printStackTrace();
//		}
//		return (DataSource)dataSource;
//	}
//
//	private Properties hibernateProperties() {
//		final Properties properties = new Properties();
//		properties.put("hibernate.dialect", this.environment.getRequiredProperty("hibernate.dialect"));
//		properties.put("hibernate.show_sql", this.environment.getRequiredProperty("hibernate.show_sql"));
//		return properties;
//	}
//
//	@Bean
//	@Autowired
//	public HibernateTransactionManager transactionManager(final SessionFactory s) {
//		final HibernateTransactionManager txManager = new HibernateTransactionManager();
//		txManager.setSessionFactory(s);
//		return txManager;
//	}  
//}

