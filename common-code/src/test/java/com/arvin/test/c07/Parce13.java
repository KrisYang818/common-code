package com.arvin.test.c07;

public class Parce13 {

    private class PContents extends Contents {

        private int i = 11;

        @Override
        public int value() {
            return i;
        }
    }


    protected class PDestination implements Destination {

        private String label;

        PDestination(String whereTo) {
            this.label = whereTo;
        }

        @Override
        public String readLabel() {
            return label;
        }
    }


    public Destination dest(String s) {
        return new PDestination(s);
    }

    public Contents cont() {
        return new PContents();
    }
}



abstract class Contents {
    abstract public int value();
}


interface Destination {
    String readLabel();
}
