package com.org.telugucineandtvoutdoorunittechniciansunion.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.org.telugucineandtvoutdoorunittechniciansunion.init.DataAccess;

@Configuration
@PropertySource("classpath:application.properties")
public class AppConfigurations  {
	@Autowired
	DataSource dataSource;
	
	@Autowired
	PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer;
	@Value("${hibernate.dialect}")
	private String dialect;
	@Value("${hibernate.id.new_generator_mappings}")
	private boolean idNewGeneratorMappings;
	@Value("${hibernate.format_sql}")
	private boolean formatSql;
	@Value("${hibernate.hbm2ddl.auto}")
	private String hbm2ddlAuto;
	@Value("${hibernate.show_sql}")
	private String showSql;
	@Value("${entitymanager.packagesToScan}")
	private String packagesToScan;

	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		sessionFactory.setPackagesToScan(new String[] { "com.org.telugucineandtvoutdoorunittechniciansunion.pojo" });
		sessionFactory.setHibernateProperties(hibernateProperties());
		return sessionFactory;
	}
//	/@Bean
	public DataAccess dataAccess() {
		
		return  new DataAccess(sessionFactory());
	}

	private Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", dialect);
		properties.put("hibernate.id.new_generator_mappings", idNewGeneratorMappings);
		properties.put("hibernate.format_sql", formatSql);
		properties.put("hibernate.hbm2ddl.auto", hbm2ddlAuto);
		properties.put("hibernate.show_sql", showSql);
		properties.put("entitymanager.packagesToScan", packagesToScan);

		return properties;
	}



	@Bean
	public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
	@Bean
	public ViewResolver internalResourceViewResolver() {
	    InternalResourceViewResolver bean = new InternalResourceViewResolver();
	    bean.setViewClass(JstlView.class);
	    bean.setPrefix("/WEB-INF/views/");
	    bean.setSuffix(".jsp");
	    return bean;
	}
	


}
