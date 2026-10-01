package com.study.spring_demo;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.study.spring_demo.entity.User;
import com.study.spring_demo.mapper.UserMapper;
import com.study.spring_demo.service.UserService;

import jakarta.annotation.Resource;

@SpringBootTest
class SpringDemoApplicationTests {

	@Autowired
	private UserMapper userMapper;
	@Resource
	private UserService userService;

	@Test
	void contextLoads() {
		System.out.println(("----- selectAll method test ------"));
		List<User> userList = userMapper.selectList(null);
		Assert.isTrue(5 == userList.size(), "");
		userList.forEach(System.out::println);
	}

	@Test
	void contextLoads1() {
		System.out.println(("----- selectAll method test ------"));
		List<User> userList = userService.list();
		userList.forEach(System.out::println);
	}

}
