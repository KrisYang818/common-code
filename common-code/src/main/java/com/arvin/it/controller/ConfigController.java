package com.arvin.it.controller;

import com.arvin.it.vo.ConfigVO;
import com.arvin.it.vo.Result;
import com.arvin.it.vo.validation.Group;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.groups.Default;

@RestController
@RequestMapping(value = "/config")
public class ConfigController {

    @PostMapping(value = "/add")
    public Result<ConfigVO> addConfig(@Validated(value = {Group.Add.class, Default.class}) @RequestBody ConfigVO configVO, BindingResult bindingResult) {
        Result<ConfigVO> result = new Result<>(configVO);
        if (bindingResult.hasErrors()) {
            bindingResult.getFieldErrors().forEach(item -> {
                String field = item.getField();
                String defaultMessage = item.getDefaultMessage();
                result.setCode(400);
                String message = String.format("Field %s is error, %s", field, defaultMessage);
                result.setMsg(message);
                result.setData(null);
            });
        }
        return result;
    }

    @PostMapping(value = "/update")
    public Result<ConfigVO> updateConfig(@Validated(Group.Update.class) @RequestBody ConfigVO configVO, BindingResult bindingResult) {
        Result<ConfigVO> result = new Result<>(configVO);
        if (bindingResult.hasErrors()) {
            bindingResult.getFieldErrors().forEach(item -> {
                result.setCode(400);
                result.setMsg(String.format("Field %s is error, %s", item.getField(), item.getDefaultMessage()));
                result.setData(null);
            });
        }
        return result;
    }

    @PostMapping(value = "/delete")
    public Result<ConfigVO> deleteConfig(@Validated @RequestBody ConfigVO configVO, BindingResult bindingResult) {
        Result<ConfigVO> result = new Result<>(configVO);
        if (bindingResult.hasErrors()) {
            bindingResult.getFieldErrors().forEach(item -> {
                result.setCode(400);
                result.setMsg(String.format("Field %s is error, %s", item.getField(), item.getDefaultMessage()));
                result.setData(null);
            });
        }
        return result;
    }

    @PostMapping(value = "/query")
    public Result<ConfigVO> query() {
        return new Result<>();
    }
}
