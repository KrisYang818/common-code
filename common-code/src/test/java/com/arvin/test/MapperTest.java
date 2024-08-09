package com.arvin.test;

import com.arvin.it.Main;
import com.arvin.it.entity.User;
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
    public void add() {
        User user = new User();
        user.setUserName("zhangsan");

        int result = userMapper.insert(user);

        System.out.println(result);
        if (result == 1) {
            System.out.println(user.getId()); // 新增成功，user对象的主键值会进行同步处理。
        }
    }

    @Test
    public void update() {
        User user = new User();
        user.setId(2L);
        user.setUserName("lisi");
        // 会更新非null的属性值
        int result = userMapper.updateById(user);
        System.out.println(result);
    }

    @Test
    public void delete() {
        int i = userMapper.deleteById(2);
        System.out.println(i);
    }

    @Test
    public void testBcryptPasswordEncoder() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("root"));

        System.out.println(passwordEncoder.matches("root", "$2a$10$EJcBFN8Bpy37uNTpHHZXTeJTETLkxWp.EKDt7qSyG.8K.sh5wIAha"));
    }

}
