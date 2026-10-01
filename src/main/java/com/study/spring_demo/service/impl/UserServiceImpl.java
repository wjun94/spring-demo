package com.study.spring_demo.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.spring.service.impl.ServiceImpl;
import com.study.spring_demo.entity.User;
import com.study.spring_demo.mapper.UserMapper;
import com.study.spring_demo.service.UserService;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
