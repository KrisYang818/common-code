package com.arvin.it.servoce;

import com.arvin.it.entity.User;
import com.arvin.it.vo.Result;

import java.util.Map;

public interface LoginService {

    Result<Map<String, String>> login(User user);

    Result<Object> logout();
}
