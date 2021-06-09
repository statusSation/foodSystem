package com.food;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@PropertySource("${config.path}/env.properties")
@MapperScan("com.food.mapper")
@ComponentScan(basePackages = {"com.food"})
@ServletComponentScan
public class App extends SpringBootServletInitializer
{
	public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
