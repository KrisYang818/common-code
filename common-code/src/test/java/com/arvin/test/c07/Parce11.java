package com.arvin.test.c07;

public class Parce11 {


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

    public void ship(String dest) {
        Contents c = new Contents();
        Destination d = new Destination(dest);
    }

    public static void main(String[] args) {
        Parce11 p = new Parce11();
        p.ship("tanzania");
    }
}
