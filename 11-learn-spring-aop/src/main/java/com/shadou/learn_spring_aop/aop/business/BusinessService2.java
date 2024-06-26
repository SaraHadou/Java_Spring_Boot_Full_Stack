package com.shadou.learn_spring_aop.aop.business;

import com.shadou.learn_spring_aop.aop.annotations.TrackTime;
import com.shadou.learn_spring_aop.aop.data.DataService2;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class BusinessService2 {

    private DataService2 dataService2;

    public BusinessService2(DataService2 dataService2) {
        this.dataService2 = dataService2;
    }

    @TrackTime
    public int calculateMin() {
        int[] data = dataService2.retrieveData();
//        throw new RuntimeException("Something Went Wrong!");
         return Arrays.stream(data).min().orElse(0);
    }

}
