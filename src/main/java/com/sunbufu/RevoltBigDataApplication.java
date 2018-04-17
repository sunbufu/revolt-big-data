package com.sunbufu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;

@SpringBootApplication
public class RevoltBigDataApplication {

	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplateBuilder().additionalMessageConverters(new StringHttpMessageConverter(Charset.forName("UTF-8"))).build();
	}

	public static void main(String[] args) {
		SpringApplication.run(RevoltBigDataApplication.class, args);
	}
}
