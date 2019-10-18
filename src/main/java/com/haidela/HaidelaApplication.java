package com.haidela;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@ComponentScan(value = "com.haidela")
@SpringBootApplication
@MapperScan(value = "com.haidela")
public class HaidelaApplication {

	public static void main(String[] args) {
		SpringApplication.run(HaidelaApplication.class, args);
		System.out.println("项目启动成功");
	}

}
