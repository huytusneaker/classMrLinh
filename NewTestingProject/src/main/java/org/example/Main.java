package org.example;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
//
//        // understand about the classpath. where the Java get files from.
//        Class.forName("org.lib.User");
//        System.out.println("Test hello world");
//        //  java -cp "D:\Fsoft\ClassMrLinh\NewTestingProject\target\classes;D:\Fsoft\ClassMrLinh" org.example.Main
//        Class<?> clazz = Class.forName("org.lib.User");
//        System.out.println(clazz.getName());
//
//
//        System.out.println("Start scan class in directory!");
//        String directoryPath = "D:\\Fsoft\\ClassMrLinh\\NewTestingProject\\target\\classes";
//
//        File directory = new File(directoryPath);
//        URL classUrl = directory.toURI().toURL();
//
//        File[] classFiles = directory.listFiles(new FilenameFilter() {
//            @Override
//            public boolean accept(File dir, String name) {
//                return name.endsWith(".class");
//            }
//        });
//        List<Class> listFiles = new ArrayList<>();
//        for (File classFile : classFiles) {
//
//            String className = classFile.getName().replace(".class", "");
//            File[] instanceClass = classFile.listFiles();
////            Class<?> clazzz= Class.forName();
//            System.out.println("className: " + className);
//            System.out.println("clazzName-----: " + clazz.getName());
//        }
//
//
//        List<String> classNames = new ArrayList<>();
//
//        findClasses(new File(directoryPath), "", classNames);
//        List<Class<?>> classes = new ArrayList<>();
//        // In ra các class names để có thể dùng Class.forName
//        for (String className : classNames) {
//            System.out.println("inside recursivead function");
//            System.out.println(className);
//            Class<?> clazzInstance = Class.forName(className);
//            classes.add(clazzInstance);
//        }
//        System.out.println("size of list classes: "+  classes.size());
//    }
//
//    private static void findClasses(File directory, String packageName, List<String> classNames) throws IOException {
//        if (!directory.exists() || !directory.isDirectory()) {
//            return;
//        }
//
//        for (File file : directory.listFiles()) {
//            if (file.isDirectory()) {
//                // Đệ quy vào subdirectory
//                findClasses(file, packageName + file.getName() + ".", classNames);
//            } else if (file.getName().endsWith(".class")) {
//                // Loại bỏ phần mở rộng .class để lấy tên class
//                String className = packageName + file.getName().replace(".class", "");
//                classNames.add(className);
//            }
//        }
//
        System.out.println("Start scan class in .jar!");
//        String jarFilePath = "D:\\Fsoft\\LearnWithMrLinh\\MavenProject\\target\\MavenProject-1.0-SNAPSHOT.jar";
//        JarFile jarFile = new JarFile(jarFilePath);
//        Enumeration<JarEntry> entries = jarFile.entries();
//        while (entries.hasMoreElements()) {
//            JarEntry entry = entries.nextElement();
//            if (entry.getName().endsWith(".class")) {
//                String className = entry.getName().replace('/', '.').replace(".class", "");
//                System.out.println(className);
//            }
//        }
//        jarFile.close();
        Class<?> clazz = Class.forName("newOrg.example.Main");
        System.out.println("End scan class in .jar!");

    }
}