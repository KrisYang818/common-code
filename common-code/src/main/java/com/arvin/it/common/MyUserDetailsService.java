package com.arvin.it.common;

import com.arvin.it.entity.LoginUser;
import com.arvin.it.entity.User;
import com.arvin.it.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 查询用户信息
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName, username);
        User user = userMapper.selectOne(queryWrapper);

        // 如果没有查询到对应用户，就抛出异常
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("用户名不存在");
        }

        // 如果存在，则查询对应的权限信息
        // todo

        // 把数据封装成UserDetails
        return new LoginUser(user);
    }

}
