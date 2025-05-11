package com.arvin.it.controller;

import com.arvin.it.common.log.LogOperation;
import com.arvin.it.common.log.LogTypeEnum;
import com.arvin.it.entity.User;
import com.arvin.it.service.LoginService;
import com.arvin.it.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private LoginService loginService;

    @GetMapping("/demo")
    public Result<String> demo() {
        return new Result<>(200, "success", "hello world");
    }

    @PostMapping("/login")
    @LogOperation(type = LogTypeEnum.SYSTEM, value = "登录系统")
    public Result<Map<String, String>> login(@RequestBody User user) {
        return loginService.login(user);
    }

    /**
     * 推出登录
     * @return
     */
    @PostMapping("/logout")
    public Result<Object> logout() {
        return loginService.logout();
    }
}
