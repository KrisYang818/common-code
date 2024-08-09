package com.arvin.test.core1;

public class OuterClassTest2 {

    public static void main(String[] args) {
        A a = new A() {
            @Override
            public void method() {
                System.out.println("hello");
            }

            @Override
            public void method2() {

            }
        };
        a.method();
    }
}

interface A {
    void method();

    void method2();
}
