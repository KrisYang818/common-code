package com.arvin.it.servoce.impl;

import com.arvin.it.config.RedisCache;
import com.arvin.it.domain.LoginUser;
import com.arvin.it.domain.User;
import com.arvin.it.servoce.LoginService;
import com.arvin.it.utils.JwtUtil;
import com.arvin.it.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    /**
     * 登录成功处理器：
     * 实际上在UsernamePasswordAuthenticationFilter进行登录认证的时候，如果登录成功了是会调用AuthenticationSuccessHandler的方法
     * 进行认证成功后的处理的，AuthenticationSuccessHandler就是登录成功处理器。
     * 我们也可以自己去自定义成功处理器进行成功后的响应处理。
     * @param user
     * @return
     */
    @Override
    public Result<Map<String, String>> login(User user) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());

        // 1、AuthenticationManager  authenticate进行用户认证
        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        // 2、如果认证没有通过，给出对应提示
        if (Objects.isNull(authentication)) {
            // 没有通过。
            throw new RuntimeException("登录失败");
        }

        // 3、如果认证通过了，使用userId生成一个jwt jwt存入Result返回
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long id = loginUser.getUser().getId();
        String token = JwtUtil.createJWT(String.valueOf(id));
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        // 4、把完整的用户信息存入redis， userId作为key
        redisCache.setCacheObject("login:" + id.toString(), loginUser);
        return new Result<>(200, "success", map);
    }

    @Override
    public Result<Object> logout() {
        // 1、获取SecurityContextHolder中的用户ID
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser user = (LoginUser) authentication.getPrincipal();

        // 2、删除redis中登录用户信息
        String redisKey = "login:" + user.getUser().getId();
        redisCache.deleteObject(redisKey);
        return new Result<>(200, "注销成功", null);
    }
}
