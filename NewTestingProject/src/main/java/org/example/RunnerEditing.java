package org.example;

import java.util.HashMap;
import java.util.Map;

public class RunnerEditing implements Runnable {

    Map<Integer,Integer> mapNumber;

    public RunnerEditing(Map<Integer, Integer> mapNumber) {
        this.mapNumber = mapNumber;
    }
    @Override
    public void run() {

        for(Map.Entry<Integer,Integer> entry : mapNumber.entrySet()) {
        System.out.println("Runner editing");
            mapNumber.put(99,-99);
            mapNumber.put(1000,-1000);
        }
//        mapNumber.entrySet().stream().forEach(entry -> {
//                mapNumber.put(99,-99);
//                mapNumber.put(1000,-1000);
//        });
    }
}
