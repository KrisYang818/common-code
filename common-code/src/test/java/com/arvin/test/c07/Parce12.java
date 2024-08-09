package com.arvin.test.c07;

public class Parce12 {

    class Contents {
        private int i = 11;

        public int value() {
            return i;
        }
    }

    class Destination {
        private String label;

        Destination(String whereTo) {
            label = whereTo;
        }

        String readLabel() {
            return label;
        }
    }


    public Destination to(String s) {
        return new Destination(s);
    }

    public Contents cont() {
        return new Contents();
    }

    public void ship(String dest) {
        Contents contents = cont();
        Destination destination = to(dest);
    }

    public static void main(String[] args) {
        Parce12 parce12 = new Parce12();
        parce12.ship("Tanza");


        Parce12 parce121 = new Parce12();
        // 若想在除外部类非static方法内部之外的任何地方生成内部类的一个对象
        Contents cont = parce121.cont();
        System.out.println(cont.value());
        Parce12.Destination destination = parce121.to("Bor");

    }
}
