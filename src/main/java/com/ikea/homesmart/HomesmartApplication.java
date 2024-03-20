package com.ikea.homesmart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.context.ApplicationListener;

@SpringBootApplication
public class HomesmartApplication implements ApplicationListener<ApplicationPreparedEvent> {

	public static void main(String[] args) {
		SpringApplication.run(HomesmartApplication.class, args);
	}

	@Override
	public void onApplicationEvent(ApplicationPreparedEvent event) {

	}

}
