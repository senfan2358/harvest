package com.example.demo.spi;

import java.util.*;

public class TestCase {
    public static void main(String[] args) {
        ServiceLoader<Search> s = ServiceLoader.load(Search.class);
        Iterator<Search> iterator = s.iterator();
        while (iterator.hasNext()) {
            Search search =  iterator.next();
            search.searchDoc("hello world");
        }
        Set<Integer> set = new LinkedHashSet<>();
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(4);
        set.add(5);
        set.add(213);
        set.add(7);
        set.add(23);
        set.add(13);
        set.add(12);
        int cnt = 0;
        for (Integer integer : set) {
            System.out.println("integer = " + integer);
            if (cnt == 5){
                break;
            }
            cnt++;
        }
        System.out.println("----------------------");
        for (Integer integer : set) {
            System.out.println("integer = " + integer);
        }
    }
}