package com.mybatis.demo.generics.config;

import org.h2.server.web.WebServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration("H2DevConfig")
@Profile("h2")
public class H2DevConfig {
	private static final Logger logger = LoggerFactory.getLogger(H2DevConfig.class);
	@Bean
	public ServletRegistrationBean h2servletRegistration() {

		logger.debug("Called ... {}", this.getClass().getName());
		ServletRegistrationBean registrationBean = new ServletRegistrationBean(
				new WebServlet());
		registrationBean.addUrlMappings("/h2-console/*");
		return registrationBean;
	}
}
