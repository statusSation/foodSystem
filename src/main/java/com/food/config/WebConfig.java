package com.food.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.food.interceptor.FoodInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Bean(name = "FoodInterceptor")
	public FoodInterceptor FoodInterceptor() {
		return new FoodInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(FoodInterceptor()).addPathPatterns("/api/**", "/api/food/**")
				.excludePathPatterns("/api/login/**", "/webStocket/**", "/api/ww/**", "/api/foodDetail/**");
	}

}
