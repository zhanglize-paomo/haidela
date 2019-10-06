package com.haidela;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(value = "com.haidela")
@SpringBootApplication
public class HaidelaApplication {

	public static void main(String[] args) {
		SpringApplication.run(HaidelaApplication.class, args);
		System.out.println("项目启动成功");
	}

}
