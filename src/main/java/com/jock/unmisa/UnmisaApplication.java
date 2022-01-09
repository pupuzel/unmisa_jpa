package com.jock.unmisa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class UnmisaApplication {

	public static void main(String[] args) {
		SpringApplication.run(UnmisaApplication.class, args);
	}

}
