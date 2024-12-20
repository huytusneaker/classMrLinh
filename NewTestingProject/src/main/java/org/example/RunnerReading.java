package org.example;

import java.util.HashMap;
import java.util.Map;

public class RunnerReading implements Runnable {

    Map<Integer,Integer> mapNumber;

    public RunnerReading(Map<Integer, Integer> mapNumber) {
        this.mapNumber = mapNumber;
    }

    @Override
    public void run() {

//        for (Map.Entry<Integer, Integer> entry : mapNumber.entrySet()) {
//                                try {
//                        Thread.sleep(5);
//                    } catch (InterruptedException e) {
//                        throw new RuntimeException(e);
//                    }
//            System.out.println(entry.getKey() + " : " + entry.getValue());
//        }
//        mapNumber.entrySet()
//                .forEach(entry ->{
//                    try {
//                        if(entry.getValue() >50) {
//                            Thread.sleep(5);
//                        }
//                    } catch (InterruptedException e) {
//                        throw new RuntimeException(e);
//                    }
//                    System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
//                });
        for(Map.Entry<Integer,Integer> entry:mapNumber.entrySet()){
            try {
                if(entry.getValue() >50) {
                    Thread.sleep(5);
                }
                System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
//            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());

        }
    }
}
