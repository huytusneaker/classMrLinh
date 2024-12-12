package org.example.Handler;

import org.example.Annotation.*;
import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class WinterContainer {
    private static WinterContainer instance;
    private Map<Class<?>, Object> container = new HashMap<>();
    private Map<String, Method> routeMappings = new HashMap<>();

    public static WinterContainer getInstance() {
        if (instance == null) {
            instance = new WinterContainer();
        }
        return instance;
    }

    public void scan(String packageName) throws Exception {
        Reflections reflections = new Reflections(packageName);

        // Quét các lớp có các annotation BOComponent, BOController, BOService, BORepository
        Set<Class<?>> boComponents = reflections.getTypesAnnotatedWith(BOComponent.class);
        Set<Class<?>> boControllers = reflections.getTypesAnnotatedWith(BOController.class);
        Set<Class<?>> boServices = reflections.getTypesAnnotatedWith(BOService.class);
        Set<Class<?>> boRepositories = reflections.getTypesAnnotatedWith(BORepository.class);

        // Khởi tạo các lớp được đánh dấu với BOComponent, BOController, BOService, BORepository
        for (Class<?> clazz : boComponents) {
            Object instance = createInstance(clazz);
            container.put(clazz, instance);
            autowire(instance);
        }

        // Quét và khởi tạo các lớp @BOController, @BOService, @BORepository
        for (Class<?> clazz : boControllers) {
            Object instance = createInstance(clazz);
            container.put(clazz, instance);
            autowire(instance);
            handleMethod(clazz);
        }

        for (Class<?> clazz : boServices) {
            Object instance = createInstance(clazz);
            container.put(clazz, instance);
            autowire(instance);
        }

        for (Class<?> clazz : boRepositories) {
            Object instance = createInstance(clazz);
            container.put(clazz, instance);
            autowire(instance);
        }
    }

    private Object createInstance(Class<?> clazz) throws Exception {
        Constructor<?> constructor = clazz.getDeclaredConstructor();
        constructor.setAccessible(true);
        return constructor.newInstance();
    }

    private void autowire(Object instance) throws Exception {
        for (Field field : instance.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(BOAutowired.class)) {
                Object dependency = container.get(field.getType());
                if (dependency != null) {
                    field.setAccessible(true);
                    field.set(instance, dependency);
                }
            }
        }
    }

    private void handleMethod(Class<?> clazz){
        // Quét các phương thức có annotation @BOGetMapping
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(BOGetMapping.class)) {
                String path = method.getAnnotation(BOGetMapping.class).value();
                routeMappings.put(path, method); // Lưu endpoint và method
                System.out.println("Mapped endpoint: " + path + " -> " + method.getName());
            }
        }
    }

    public List<Object> getBeansWithAnnotation(Class<?> annotationClass) {
        return container.entrySet().stream()
                .filter(entry -> entry.getKey().isAnnotationPresent((Class<? extends Annotation>) annotationClass)) // Kiểm tra annotation
                .map(Map.Entry::getValue) // Lấy giá trị (bean)
                .collect(Collectors.toList());
    }

    public <T> T get(Class<T> clazz) {
        return (T) container.get(clazz);
    }

    public Method getRouteHandler(String path) {
        return routeMappings.get(path);
    }

}
