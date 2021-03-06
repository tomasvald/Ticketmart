package com.ticketmart.init;

import javax.servlet.Filter;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.ticketmart.config.DataServiceConfig;
import com.ticketmart.config.WebDispatcherConfig;

public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[]{
			DataServiceConfig.class
			};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[]{
			WebDispatcherConfig.class
			};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[]{"/"};
	}
	
	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter cef = new CharacterEncodingFilter();
		cef.setEncoding("UTF-8");
		cef.setForceEncoding(true);
		HiddenHttpMethodFilter hiddenHttpMethodFilter = new HiddenHttpMethodFilter();
		return new Filter[]{hiddenHttpMethodFilter, cef};
	}
	
	

}
