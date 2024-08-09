package com.arvin.test.c07;

public class Test {
    public static void main(String[] args) {
        Test test = new Test();
        test.testParce13();
    }

    public void testParce12() {
        Parce12 parce12 = new Parce12();
        parce12.ship("Tanza");


        Parce12 parce121 = new Parce12();
        // 若想在除外部类非static方法内部之外的任何地方生成内部类的一个对象
        Parce12.Contents cont = parce121.cont();
        System.out.println(cont.value());
        Parce12.Destination destination = parce121.to("Bor");
    }

    public void testParce13() {
        Parce13 parce13 = new Parce13();
        Contents contents = parce13.cont();
        Destination destination = parce13.dest("Tanaz");
        Parce13.PDestination pDestination = new Parce13().new PDestination("xxx");
    }
}
