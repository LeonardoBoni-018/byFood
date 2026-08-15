package com.byfood.api;

import org.springframework.boot.SpringApplication;

public class TestByfoodApplication {

	public static void main(String[] args) {
		SpringApplication.from(ByfoodApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
