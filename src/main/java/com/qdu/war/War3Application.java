package com.qdu.war;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages= {"com.qdu.controller,com.qdu.service.imp,com.qdu.service"})
@MapperScan(value="com.qdu.mapper")
public class War3Application {

	public static void main(String[] args) {
		SpringApplication.run(War3Application.class, args);
	}

}
