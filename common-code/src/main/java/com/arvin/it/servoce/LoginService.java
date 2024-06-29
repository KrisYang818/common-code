package com.arvin.it.servoce;

import com.arvin.it.domain.User;
import com.arvin.it.vo.Result;

import java.util.Map;

public interface LoginService {

    Result<Map<String, String>> login(User user);
}
