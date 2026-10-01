package com.study.spring_demo;

import java.util.Map;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@SpringBootApplication
@RestController
@MapperScan("com.study.spring_demo.mapper")
public class SpringDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDemoApplication.class, args);
	}

	// @GetMapping 表示：当浏览器用 GET 方式访问 "/hello" 这个路径时，执行下面的方法
	@GetMapping("/hello")
	public String sayHello() {
		return "Hello, Spring Boot! 我的第一个接口成功啦！";
	}

	@GetMapping("/user/{id}")
	public String getId(@PathVariable String id) {
		System.out.println("id: " + id);
		return "Restful GET请求传参，获取到id:" + id;
	}

	@GetMapping("/info")
	public String getNameId(@RequestParam String id, @RequestParam String name) {
		System.out.printf("姓名：%s，年龄：%s%n", name, id);
		return "普通GET请求传参?id=1&name=1，获取到id:" + id + "，name:" + name;
	}

	@PostMapping("setInfo")
	public String postMethodName(@RequestBody Map<String, String> data) {
		System.out.printf(data.toString());
		return "POST请求参数接受";
	}

	@PutMapping("update/{id}")
	public String putMethodName(@PathVariable String id) {
		// TODO: process PUT request
		System.out.printf("ID：%s%n", id);
		return "id=" + id;
	}

	@DeleteMapping("delete/{id}")
	public String deleteMethodName(@PathVariable String id) {
		System.out.printf("ID：%s%n", id);
		return "id=" + id;
	}
}
