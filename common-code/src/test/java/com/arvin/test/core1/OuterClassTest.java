package com.arvin.test.core1;

public class OuterClassTest {

    public static void main(String[] args) {
        /**
         * 1、创建Person的静态的成员内部类的实例
         * 静态的，是随着类的加载而加载，他只与类相关，和对象无关。
         */
        Person.Dog dog = new Person.Dog();


        /**
         * 1、创建Person的非静态的成员内部类的实例
         * 非静态的，与实例相关，所以首先要有外部类的实例，才能创建非静态内部类的实例。
         */

        // Person.Bird bird = new Person.Bird();
        Person.Bird bird = new Person().new Bird();
        bird.show("黄鹂");

        new Object() {
            public void test() {
                System.out.println("尚硅谷");
            }
        }.test();
    }
}



class Person {

    private String name = "张三";

    private int age = 100;

    /**
     * 静态、成员内部类 （类级别的）
     */
    static class Dog {

    }


    /**
     * 非静态、成员内部类（对象级别）
     */
    class Bird {

        private String name = "啄木鸟";

        private int age = 5;

        /**
         * 内部类访问外部类的结构的时候，重点在同名的情况下的如何理解
         * @param name
         */
        public void show(String name) {
            System.out.println("name --->" + name);
            System.out.println("name --->" + this.name);  // this 指的是Bird类的实例对象，所以这里的name是属于成员属性 name
            System.out.println("name --->" + Person.this.name); // 指明了是Person类的this，即Person类的实例对象的成员属性 name

            // 局部找不到，就找成员变量，最后在找外部类实例，直到找到位置，如果还是找不到就报错。
            System.out.println("age --->" + age);
        }

    }


    public void method() {

        /**
         * 方法中定义：
         * 实名、局部内部类
         */
        class InnerClass1 {

        }
    }


    public Person() {
        /**
         * 构造器中定义：
         * 实名、局部内部类
         */
        class InnerClass2 {

        }
    }

    {
        /**
         * 代码块中定义：
         * 实名、局部内部类
         */
        class InnerClass3 {

        }
    }


    public Object test() {
        return new Object() {
            public void test() {
                System.out.println("尚硅谷");
            }
        };
    }

}
