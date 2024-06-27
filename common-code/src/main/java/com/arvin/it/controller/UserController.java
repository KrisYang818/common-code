package com.arvin.it.controller;

import com.arvin.it.vo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/demo")
    public Result<String> demo() {
        return new Result<>(200, "success", "hello world");
    }
}
