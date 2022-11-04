package com.teste.idbrasil;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class IdbrasilApplication {

	public static void main(String[] args) {
		SpringApplication.run(IdbrasilApplication.class, args);
	}
}
