package com.shadou.learn_spring_aop.aop.data;

import org.springframework.stereotype.Service;

@Service
public class DataService1 {

    public int[] retrieveData() {
        return new int[] {10, 20 , 30, 40, 50};
    }
}
