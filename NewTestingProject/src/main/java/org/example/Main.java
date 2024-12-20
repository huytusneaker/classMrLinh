package org.example;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static Map<Integer,Integer> mapNumber = new HashMap<>();
    public static void initialize() {
        for (int i = 0; i<100;i ++){
            mapNumber.put(i,i);
            if(i == 99){
                System.out.println("Finished");
            }
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {initialize();
      initialize();

      Runnable reading = new RunnerReading(mapNumber);

        Runnable editing = new RunnerEditing(mapNumber);

      Thread threadReading = new Thread(reading);

      Thread threadEditing = new Thread(editing);
      threadReading.start();
      threadEditing.start();
      try {

//          threadReading.join();
          threadEditing.join();
      } catch (InterruptedException e) {
          e.printStackTrace();
      }
    }
}