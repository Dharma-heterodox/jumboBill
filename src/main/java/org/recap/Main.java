package org.recap;

import org.apache.catalina.connector.Connector;
import org.recap.security.SessionFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class Main {

	@Value("${server.protocol}")
	String serverProtocol;

	@Value("${tomcat.maxParameterCount}")
	Integer tomcatMaxParameterCount;

	@Bean
	public EmbeddedServletContainerFactory servletContainerFactory() {
		TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory();
		factory.addConnectorCustomizers(new TomcatConnectorCustomizer() {
			@Override
			public void customize(Connector connector) {
				connector.setMaxParameterCount(tomcatMaxParameterCount);
			}
		});
		return factory;
	}

	@Bean
	public FilterRegistrationBean getFileterRegisteredBean(){
		FilterRegistrationBean filterRegistrationBean= new FilterRegistrationBean();
		Set<String> urlPatterns=new HashSet<String>();
		urlPatterns.add("/search");
		urlPatterns.add("/request");
		urlPatterns.add("/collection");
		urlPatterns.add("/reports");
		urlPatterns.add("/roles");
		urlPatterns.add("/userRoles");
		filterRegistrationBean.setUrlPatterns(urlPatterns);
		filterRegistrationBean.setFilter(new SessionFilter());
		return filterRegistrationBean;
	}

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}
}
