package com.arvin.test;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

public class MockitoDemo {

    @Test
    public void test1() {
        ArrayList<String> arrayList = Mockito.mock(ArrayList.class);
        Mockito.when(arrayList.get(0)).thenReturn("hello");
        Assertions.assertEquals("hello0", arrayList.get(0), "errorxxx");
    }


    @Test
    public void test2() {
        ArrayList arrayList = Mockito.spy(ArrayList.class);
        //Mockito.when(arrayList.get(0)).thenReturn("hello");
        //Mockito.doReturn("hello").when(arrayList).get(0);
        Assertions.assertEquals("hello", arrayList.get(0), "errorxxx");
    }

    @Test
    public void test3() {
        ArrayList<String> arrayList = Mockito.spy(ArrayList.class);
        Mockito.doNothing().when(arrayList).clear();
        arrayList.add("hello");

        // 执行了clear, 但是什么都没有做
        arrayList.clear();
        Assertions.assertTrue(arrayList.contains("hello"));
    }

    @Test
    public void test4() {
        ArrayList arrayList = Mockito.spy(ArrayList.class);
        Mockito.doNothing().doThrow(new RuntimeException("error")).when(arrayList).clear();
        arrayList.clear();
        Assertions.assertThrows(RuntimeException.class, () -> {
            arrayList.clear();
        });
    }

}
