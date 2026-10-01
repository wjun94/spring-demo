package com.study.spring_demo.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.util.StringUtils;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.study.spring_demo.common.Result;
import com.study.spring_demo.entity.User;
import com.study.spring_demo.service.UserService;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 用户接口控制器。
 *
 * <p>
 * 统一处理用户的新增、修改、查询和删除请求。
 * </p>
 */
@RestController
@RequestMapping("/user")
public class UserController {

    /** 用户业务服务，负责调用 MyBatis-Plus 完成数据库操作。 */
    @Resource
    private UserService userService;

    /**
     * 新增用户。
     *
     * @param user 请求体中的用户信息
     * @return 保存成功后的用户，或失败信息
     */
    @PostMapping
    public Result<User> save(@RequestBody User user) {
        boolean result = userService.save(user);
        if (result) {
            return Result.success(user);
        } else {
            return Result.failure("保存用户失败");
        }
    }

    /**
     * 根据用户 ID 修改用户信息。
     *
     * @param user 包含 ID 和待修改字段的用户信息
     * @return 操作结果
     */
    @PutMapping
    public Result<Void> update(@RequestBody User user) {
        userService.updateById(user);
        return Result.success();
    }

    /**
     * 根据 ID 查询单个用户。
     *
     * @param id 用户 ID
     * @return 用户信息
     */
    @GetMapping("/{id}")
    public Result<User> getById(@PathVariable Long id) {
        User user = userService.getById(id);
        return Result.success(user);
    }

    /**
     * 查询全部用户。
     *
     * @return 用户列表
     */
    @GetMapping
    public Result<List<User>> list() {
        return Result.success(userService.list());
    }

    @GetMapping("/page")
    public Result<Page<User>> findPage(@RequestParam(defaultValue = "1") int pageNum, @RequestParam int pageSize,
            @RequestParam(required = false) String name) {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.hasText(name)) {
            lambdaQueryWrapper.like(User::getName, name);
        }
        return Result.success(userService.page(new Page<>(pageNum, pageSize), lambdaQueryWrapper));
    }

    /**
     * 根据 ID 删除用户。
     *
     * @param id 用户 ID
     * @return 操作结果
     */
    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        userService.removeById(id);
        return Result.success();
    }
}
