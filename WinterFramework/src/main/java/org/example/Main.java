package org.example;

import org.example.Annotation.BOApplication;
import org.example.Controller.StudentController;
import org.example.Handler.WinterContainer;
import org.example.Handler.WinterHttpServer;

import java.io.IOException;

@BOApplication
public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello world!");
            WinterApplication.run(Main.class, args);
            Main.class.getClassLoader();
    }
}