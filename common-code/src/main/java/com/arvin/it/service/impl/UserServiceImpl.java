package com.arvin.it.service.impl;

import com.arvin.it.entity.User;
import com.arvin.it.mapper.UserMapper;
import com.arvin.it.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional
    public int deleteUserById(Long userId) {
        // 删除用户与角色关联

        // 删除用户与岗位表

        // 删除用户信息
        return userMapper.deleteById(userId);
    }

    @Override
    @Transactional
    public int insertUser(User user) {
        // 新增用户信息
        int rows = userMapper.insert(user);
        // 新增用户岗位关联

        // 新增用户与角色管理

        return rows;
    }

    @Override
    public boolean registerUser(User user) {
        return false;
    }

    @Override
    public int updateUser(User user) {
        return 0;
    }
}
