package com.teste.idbrasil;

/**
 ____    ___    __  __   _
|  _ \  |__ \  |  \/  | | |
| |_) |    ) | | \  / | | |
|  _ <    / /  | |\/| | | |
| |_) |  / /_  | |  | | | |____
|____/  |____| |_|  |_| |______|
 **/


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
