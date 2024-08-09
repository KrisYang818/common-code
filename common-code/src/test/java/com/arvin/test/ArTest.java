package com.arvin.test;

import com.arvin.it.Main;
import com.arvin.it.entity.Dept;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = Main.class)
public class ArTest {

    @Test
    public void testArInsert() {
        Dept dept = new Dept();
        boolean insert = dept.insert();
        System.out.println(insert);
    }

    @Test
    public void testArUpdate() {
        Dept dept = new Dept();
        dept.setId(1);
        dept.setName("研发部门");
        boolean b = dept.updateById();
        System.out.println(b);
    }
}
