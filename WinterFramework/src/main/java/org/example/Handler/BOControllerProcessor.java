package org.example.Handler;

import org.example.Annotation.BOComponent;
import org.example.Annotation.BOController;
import org.example.Annotation.BORequestMapping;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
@BOController
public class BOControllerProcessor {

    private Map<String, Method> routeMappings = new HashMap<>();

    public void processControllers(Class<?>... controllerClasses) throws Exception {
        for (Class<?> clazz : controllerClasses) {
            if (clazz.isAnnotationPresent(BOController.class)) {
                for (Method method : clazz.getDeclaredMethods()) {
                    if (method.isAnnotationPresent(BORequestMapping.class)) {
                        String path = method.getAnnotation(BORequestMapping.class).value();
                        routeMappings.put(path, method);
                    }
                }
            }
        }
    }

    public Method getHandlerForPath(String path) {
        return routeMappings.get(path);
    }
}