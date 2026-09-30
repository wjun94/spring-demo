package com.study.spring_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDemoApplication.class, args);
	}

	// @GetMapping 表示：当浏览器用 GET 方式访问 "/hello" 这个路径时，执行下面的方法
	@GetMapping("/hello")
	public String sayHello() {
		return "Hello, Spring Boot! 我的第一个接口成功啦！";
	}
}
