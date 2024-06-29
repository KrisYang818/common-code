package com.arvin.test;

import com.arvin.it.Main;
import com.arvin.it.domain.User;
import com.arvin.it.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootTest(classes = Main.class)
public class MapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testUserMapper() {
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }

    @Test
    public void testBcryptPasswordEncoder() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("root"));

        System.out.println(passwordEncoder.matches("root", "$2a$10$EJcBFN8Bpy37uNTpHHZXTeJTETLkxWp.EKDt7qSyG.8K.sh5wIAha"));
    }

}
